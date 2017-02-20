/**
 * Created by Tyler on 2/15/2017.
 */
public class User {
    public enum Type {INSTRUCTOR, STUDENT}

    private String username;
    private String password;
    private Type role;

    public User(String username, String password, Type role) {
        this.username = username;
        this.password = password;

        this.role = role;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean isInstructor() {
        return role.equals(Type.INSTRUCTOR);
    }

    public String toString() {
        return username;
    }

}
