/**
 * Created by Tyler on 2/15/2017.
 */
public class Answer extends Post {

    private boolean good;

    public Answer() {
        this.good = false;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood() {
        good = true;
    }

}
