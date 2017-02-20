/**
 * Created by Tyler on 2/15/2017.
 */
public class Post {

    private User lastAuthor;
    private String text;

    public Post() {
        this.lastAuthor = null;
        this.text = null;
    }

    public void update(User user, String text) {
        this.lastAuthor = user;
        this.text = text;
    }

    public User getLastAuthor() {
        return lastAuthor;
    }

    public String getText() {
        return text;
    }

}
