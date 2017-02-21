/**
 * Created by Tyler on 2/15/2017.
 */
public class Post {

    /**
     * author
     */
    private User author;
    /**
     * last updater
     */
    private User lastUpdater;
    /**
     * text
     */
    private String text;

    /**
     * Post constructor
     */
    public Post() {
        this.author = null;
        this.lastUpdater = null;
        this.text = null;
    }

    /**
     * Post constructor with author and text
     * @param author Person who wrote the post
     * @param text Text of post
     */
    public Post(User author, String text) {
        this.author = author;
        this.lastUpdater = author;
        this.text = text;
    }

    /**
     * Updates Post
     * @param user new updater
     * @param text post text
     */
    public void update(User user, String text) {
        if (this.author == null)
            author = user;
        this.lastUpdater = user;
        this.text = text;
    }

    /**
     * gets author
     * @return author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * gets last updater
     * @return the user who last updated this post
     */
    public User getLastAuthor() {
        return lastUpdater;
    }

    /**
     * gets text of post
     * @return text of post
     */
    public String getText() {
        return text;
    }

}
