import java.util.TreeMap;

/**
 * Created by Tyler on 2/15/2017.
 */
public class Question extends Post {

    private Answer instructorAnswer;
    private Answer studentAnswer;

    private boolean good;

    private int views;

    private TreeMap<Integer, FollowupDiscussion> followups;

    public Question() {

        this.instructorAnswer = null;
        this.studentAnswer = null;

        this.good = false;

        this.views = 0;

        this.followups = new TreeMap<>();
    }

    public int view(User u) {
        return ++views;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood() {
        good = true;
    }

    public Answer getInstructorAnswer() {
        return instructorAnswer;
    }

    public Answer getStudentAnswer() {
        return studentAnswer;
    }

    public FollowupDiscussion getFollowup(int followupid) {
        return followups.get(followupid);
    }

    public int getFollowupCount() {
        return followups.size();
    }

}
