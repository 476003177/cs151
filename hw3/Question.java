import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Tyler on 2/15/2017.
 */
public class Question extends Post {

    private Answer instructorAnswer;
    private Answer studentAnswer;

    private boolean good;

    private int views;

    private PiazzaPostCollection<FollowupDiscussion> followups;

    public Question() {

        this.instructorAnswer = new Answer();
        this.studentAnswer = new Answer();

        this.good = false;

        this.views = 0;

        this.followups = new PiazzaPostCollection<>();

    }

    public int add(FollowupDiscussion followupDiscussion) {
        return followups.add(followupDiscussion);
    }

    public FollowupDiscussion getFollowup(int followupID) {
        return followups.get(followupID);
    }

    public int getFollowupCount() {
        return followups.size();
    }

    public int view(User user) {
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

}
