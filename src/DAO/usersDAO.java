package DAO;

import Model.User;
import Utilities.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


/**
 * The type Users dao. This class contains methods used to interact with the users table in the database.
 */
public class usersDAO {

    /**
     * The constant currentUsername.
     */
    public static String currentUsername;
    /**
     * The constant getConnection. This is the connection to the database.
     */
    public static Connection getConnection = databaseConnection.getConnection();

    /**
     * Gets all users. This method returns a list of all users in the database.
     *
     * @return the all users
     */
    public static ObservableList<User> getAllUsers() {

        ObservableList<User> allUsersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                int userId = resSet.getInt("User_ID");
                String username = resSet.getString("User_Name");
                String password = resSet.getString("Password");


                allUsersObservableList.add(new User(userId, username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsersObservableList;
    }

    /**
     * Gets user id. This method returns the user ID of a user based on a username.
     *
     * @param username the username
     * @return the user id
     */
    public static int getUserId(String username) {

        try {
            String sql = "SELECT User_ID FROM users WHERE User_Name = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setString(1, username);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getInt("User_ID");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Gets username. This method returns the username of a user based on the user ID.
     *
     * @param userId the user id
     * @return the username
     */
    public static String getUsername(int userId) {

        try {
            String sql = "SELECT User_Name FROM users WHERE User_ID = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setInt(1, userId);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getString("User_Name");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    /**
     * Gets password. This method returns the password of a user based on the username.
     *
     * @param username the username
     * @return the password
     */
    public static String getPassword(String username) {

        try {
            String sql = "SELECT Password FROM users WHERE User_Name = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setString(1, username);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getString("Password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Check login boolean. This method checks if the username and password entered by the user match a username and
     * password in the database to verify access for logging in.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public static boolean checkLogin(String username, String password) {
        if (Objects.equals(getPassword(username), password)) {
            currentUsername = username;
            return true;
        }
        else {
            return false;
        }
    }

}
