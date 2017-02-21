/**
 * Created by Tyler on 2/15/2017.
 */
public class Answer extends Post {

    /**
     * Answer quality
     */
    private boolean good;

    /**
     * Constructor for answer
     */
    public Answer() {
        this.good = false;
    }

    /**
     * is it good
     * @return true if answer is good
     */
    public boolean isGood() {
        return good;
    }

    /**
     * Marks answer as good
     */
    public void setGood() {
        good = true;
    }

}
