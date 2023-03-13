package Model;

import static DAO.usersDAO.getAllUsers;

/**
 * The type User. This is the class that contains the methods and variables related to the User type.
 */
public class User {

    private int userId;
    private String username;
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param userId   the user id
     * @param username the username
     * @param password the password
     */
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user from user id.
     *
     * @param userId the user id
     * @return the user from user id
     */
    public static User getUserFromUserId(int userId) {
        for (User user : getAllUsers()) {
            if (userId == user.getUserId()) {
                return user;
            }
        }
        return null;
    }

    public String toString() { return this.username; }
}
