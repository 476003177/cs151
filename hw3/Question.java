/**
 * Created by Tyler on 2/15/2017.
 */

/**
 * Question class holds answers and followup discussions and wether a question is good or not and number of views of a question
 */
public class Question extends Post {

    /**
     * Answer of Instructor
     */
    private Answer instructorAnswer;
    /**
     * Answer of Student
     */
    private Answer studentAnswer;

    /**
     * Quality of question
     */
    private boolean good;

    /**
     * Number of views question has
     */
    private int views;

    /**
     * followups container
     */
    private PiazzaPostCollection<FollowupDiscussion> followups;

    /**
     * Question constructor
     */
    public Question() {

        this.instructorAnswer = new Answer();
        this.studentAnswer = new Answer();

        this.good = false;

        this.views = 0;

        this.followups = new PiazzaPostCollection<>();

    }

    /**
     * Add method
     * @param followupDiscussion followupDiscussion
     * @return id
     */
    public int add(FollowupDiscussion followupDiscussion) {
        return followups.add(followupDiscussion);
    }

    /**
     * getFollowup
     * @param followupID id
     * @return discussion
     */
    public FollowupDiscussion getFollowup(int followupID) {
        return followups.get(followupID);
    }

    /**
     * Count
     * @return count
     */
    public int getFollowupCount() {
        return followups.size();
    }

    /**
     * views
     * @param user u
     * @return views
     */
    public int view(User user) {
        return ++views;
    }

    /**
     * isGood
     * @return if the question is good or not
     */
    public boolean isGood() {
        return good;
    }

    /**
     * Marks question good
     */
    public void setGood() {
        good = true;
    }

    /**
     * Get's instructorAnswer
     * @return instructor's answer
     */
    public Answer getInstructorAnswer() {
        return instructorAnswer;
    }

    /**
     * Get's studentAnswer
     * @return student's answer
     */
    public Answer getStudentAnswer() {
        return studentAnswer;
    }

}
