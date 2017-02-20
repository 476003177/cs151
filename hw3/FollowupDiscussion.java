/**
 * Created by Tyler on 2/15/2017.
 */
public class FollowupDiscussion extends Post {

    private String author;

    private boolean resolved;

    private PiazzaPostCollection<Post> replys;

    public FollowupDiscussion() {
        author = "";

        resolved = false;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void resolve() {
        this.resolved = true;
    }

    public int addReply(Post reply) {
        return replys.add(reply);
    }

    public Post getReply(int followupReplyID) {
        return replys.get(followupReplyID);
    }

    public int getReplyCount() {
        return replys.size();
    }

}
