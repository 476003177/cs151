import java.util.ArrayList;

/**
 * Created by Tyler on 2/15/2017.
 */
public class User {
    /**
     * Stores type of user
     */
    public enum Type {INSTRUCTOR, STUDENT}

    /**
     * Username of user
     */
    private String username;
    /**
     * For authentication reasons
     */
    private String password;
    /**
     * User or instructor
     */
    private Type role;

    /**
     * User construct
     * @param username sets username
     * @param password sets password
     * @param role sets role
     */
    public User(String username, String password, Type role) {
        this.username = username;
        this.password = password;

        this.role = role;
    }

    /**
     * Authenticates
     * @param username login username
     * @param password login password
     * @return true if info is correct
     */
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Instructor or student
     * @return true if is instructor
     */
    public boolean isInstructor() {
        return role.equals(Type.INSTRUCTOR);
    }

    /**
     * Converts toString
     * @return username of user
     */
    @Override
    public String toString() {
        return username;
    }

}
