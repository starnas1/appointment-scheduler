package DAO;

import Model.Contact;
import Utilities.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Contacts dao. This class contains methods to access the contacts table in the database.
 */
public class contactsDAO {

    /**
     * The constant getConnection. This is the connection to the database.
     */
    public static Connection getConnection = databaseConnection.getConnection();

    /**
     * Gets all contacts. This method returns all contacts in the database.
     *
     * @return the all contacts
     */
    public static ObservableList<Contact> getAllContacts() {

        ObservableList<Contact> allContactsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {

                int contactId = resSet.getInt("Contact_ID");
                String contactName = resSet.getString("Contact_Name");
                String contactEmail = resSet.getString("Email");

                allContactsObservableList.add(new Contact(contactId, contactName, contactEmail));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allContactsObservableList;

    }

    /**
     * Gets contact id. This method returns the contact id of a contact using a contact name.
     *
     * @param contactName the contact name
     * @return the contact id
     */
    public static int getContactId(String contactName) {

        try {
            String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setString(1, contactName);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getInt("Contact_ID");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Gets contact name. This method returns the contact name of a contact using the contact ID.
     *
     * @param contactId the contact id
     * @return the contact name
     */
    public static String getContactName(int contactId) {

        try {
            String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setInt(1, contactId);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getString("Contact_Name");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}

