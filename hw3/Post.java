/**
 * Created by Tyler on 2/15/2017.
 */
public class Post {

    private User author;
    private User lastUpdater;
    private String text;

    public Post() {
        this.author = null;
        this.lastUpdater = null;
        this.text = null;
    }

    public Post(User author, String text) {
        this.author = author;
        this.lastUpdater = author;
        this.text = text;
    }

    public void update(User user, String text) {
        if (this.author == null)
            author = user;
        this.lastUpdater = user;
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public User getLastAuthor() {
        return lastUpdater;
    }

    public String getText() {
        return text;
    }

}
