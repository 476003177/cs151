import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simulation of a backend for the Piazza discussion software.
 */
public class PiazzaSimulation
{
   private List<User> users = new ArrayList<>();
   private List<Question> questions = new ArrayList<>();
   private User currentUser;

   /**
    * Starts the simulation
    * @param args ignored
    */
   public static void main(String[] args)
   {
      PiazzaSimulation simulation = new PiazzaSimulation();
      simulation.users.add(new User("horstmann", "secret", User.Type.INSTRUCTOR));
      simulation.run(new Scanner(System.in));
   }

   /**
    * Sign up a student.
    * @param request the incoming request
    * @return the response
    */
   public Response signup(Request request)
   {
      String username = request.get("username");
      String password = request.get("password");
      users.add(new User(username, password, User.Type.STUDENT));
      return new Response();
   }

   /**
    * Log in a user.
    * @param request the incoming request
    * @return the response
    */
   public Response login(Request request)
   {
      String username = request.get("username");
      String password = request.get("password");
      for (User u : users)
      {
         if (u.authenticate(username, password)) currentUser = u;
      }
      if (currentUser == null)
         return new Response("Authentication failed");
      else
         return new Response();
   }

   /**
    * Add a new question.
    * @param request the incoming request
    * @return the response
    */
   private Response newquestion(Request request)
   {
      Response response;
      int id = questions.size();
      Question q = new Question();
      q.update(currentUser, request.get("text"));
      questions.add(q);
      response = new Response("id", id);
      return response;
   }
   
   /**
    * Edit an existing question.
    * @param request the incoming request
    * @return the response
    */
   private Response editquestion(Request request)
   {      
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         questions.get(id).update(currentUser, request.get("text"));
         response = new Response();
      }
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Mark a question, student answer, or instructor answer as good.
    * @param request the incoming request
    * @return the response
    */
   private Response good(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         response = new Response();
         Question q = questions.get(id);
         String type = request.get("type");
         if (type.equals("question")) q.setGood();
         else if (type.equals("instructoranswer")) q.getInstructorAnswer().setGood();
         else if (type.equals("studentanswer")) q.getStudentAnswer().setGood();
         else response = new Response("Illegal type " + type); 
      }
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Get information about an existing question.
    * @param request the incoming request
    * @return the response
    */
   private Response getquestion(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(id);
         
         response = new Response("text", q.getText(), 
               "good", q.isGood(),
               "lastauthor", q.getLastAuthor(),
               "views", q.view(currentUser));
      }
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Answer a question.
    * @param request the incoming request
    * @return the response
    */
   private Response answer(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(request.getInt("id"));
         if (currentUser.isInstructor())
         {
            q.getInstructorAnswer().update(currentUser, request.get("text"));
         }
         else
         {
            q.getStudentAnswer().update(currentUser, request.get("text"));
         }               
         response = new Response();
      }
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Get information about an answer.
    * @param request the incoming request
    * @return the response
    */
   private Response getanswer(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(request.getInt("id"));
         Answer answer = null;
         String type = request.get("type"); 
         if (type.equals("instructor"))         
            answer = q.getInstructorAnswer();                     
         else if (type.equals("student"))
            answer = q.getStudentAnswer();
         if (answer == null)
            response = new Response("Invalid type " + type);
         else
            response = new Response("text", answer.getText(), 
               "good", answer.isGood(),
               "lastauthor", answer.getLastAuthor());
      }
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Start a followup discussion.
    * @param request the incoming request
    * @return the response
    */
   private Response followup(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(id);
         FollowupDiscussion followup = new FollowupDiscussion();
         followup.update(currentUser, request.get("text"));
         int followupid = q.add(followup);
         response = new Response("followupid", followupid); 
      }            
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Mark a followup discussion as resolved.
    * @param request the incoming request
    * @return the response
    */
   private Response resolve(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && id < q.getFollowupCount())
         {
            q.getFollowup(followupid).resolve();
            response = new Response();
         }
         else
            response = new Response("Invalid followup ID " + followupid);                           
      }            
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Get information about a followup discussion.
    * @param request the incoming request
    * @return the response
    */
   private Response getfollowup(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && followupid < q.getFollowupCount())
         {
            FollowupDiscussion followup = q.getFollowup(followupid);
            response = new Response("text", followup.getText(), 
                  "author", followup.getAuthor(),
                  "resolved", followup.isResolved());            
         }
         else
            response = new Response("Invalid followup ID " + id + " " + followupid);
      }            
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Add a reply in a followup discussion.
    * @param request the incoming request
    * @return the response
    */
   private Response followupreply(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && id < q.getFollowupCount())
         {
            Post p = new Post(currentUser, request.get("text"));
            int followupreplyid = q.getFollowup(followupid).addReply(p);
            response = new Response("followupreplyid", followupreplyid);
         }
         else
            response = new Response("Invalid followup ID " + id + " " + followupid);                           
      }
      else
         response = new Response("Invalid ID " + id);
      return response;
   }

   /**
    * Get information about a reply in a followup discussion.
    * @param request the incoming request
    * @return the response
    */   
   private Response getfollowupreply(Request request)
   {
      Response response;
      int id = request.getInt("id");
      if (0 <= id && id < questions.size())
      {
         Question q = questions.get(id);
         int followupid = request.getInt("followupid");
         if (0 <= followupid && id < q.getFollowupCount())
         {
            FollowupDiscussion followup = q.getFollowup(followupid);
            int followupreplyid = request.getInt("followupreplyid");
            if (0 <= followupreplyid && followupreplyid < followup.getReplyCount()) {               
               Post p = followup.getReply(followupreplyid);
               response = new Response("text", p.getText(), "author", p.getAuthor());
            }
            else 
               response = new Response("Invalid followup reply ID " + id + " " + followupid + " " + followupreplyid);
         }
         else
            response = new Response("Invalid followup ID " + id + " " + followupid);                           
      }
      else
         response = new Response("Invalid ID " + id);
      return response;
   }
   
   /**
    * Runs the simulation.
    * @param in the scanner from which to read input
    */
   public void run(Scanner in)
   {
      while (in.hasNextLine())
      {
         Request request = Request.read(in);
         Response response;
         try
         {
            if (request.getCommand().equals("signup"))
            {
               response = signup(request); 
            }
            else if (request.getCommand().equals("login"))
            {
               response = login(request);
            }
            else if (request.getCommand().equals("newquestion"))
            {
               response = newquestion(request);
            }
            else if (request.getCommand().equals("editquestion"))
            {
               response = editquestion(request);
            }
            else if (request.getCommand().equals("getquestion"))
            {
               response = getquestion(request);            
            }
            else if (request.getCommand().equals("answer"))
            {
               response = answer(request);            
            }
            else if (request.getCommand().equals("good"))
            {
               response = good(request);
            }
            else if (request.getCommand().equals("getanswer"))
            {
               response = getanswer(request);            
            }
            else if (request.getCommand().equals("followup"))
            {
               response = followup(request);            
            }
            else if (request.getCommand().equals("resolve"))
            {
               response = resolve(request);                        
            }
            else if (request.getCommand().equals("getfollowup"))
            {
               response = getfollowup(request);
            }
            else if (request.getCommand().equals("followupreply"))
            {
               response = followupreply(request);                        
            }
            else if (request.getCommand().equals("getfollowupreply"))
            {
               response = getfollowupreply(request);                                    
            }
            else
            {
               response = new Response("Unknown command: " + request.getCommand());
            }
         }
         catch (Exception ex)
         {
            response = new Response(ex.getMessage());
         }
         System.out.println(response);
      }
   }
}
