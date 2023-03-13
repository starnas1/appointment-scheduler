package DAO;

import Model.Customer;
import Utilities.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.firstLevelDivDAO.getDivisionId;
import static DAO.usersDAO.currentUsername;

/**
 * The type Customers dao. This class is used to access the customers table in the database.
 */
public class customersDAO {

    /**
     * The constant getConnection. This is the connection to the database.
     */
    public static Connection getConnection = databaseConnection.getConnection();

    /**
     * Gets all customers. This method is used to get a list of all customers from the database.
     *
     * @return the all customers
     */
    public static ObservableList<Customer> getAllCustomers() {

        ObservableList<Customer> allCustomersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, " +
                    "customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division, " +
                    "countries.Country FROM ((customers INNER JOIN first_level_divisions ON " +
                    "customers.Division_ID = first_level_divisions.Division_ID) INNER JOIN countries ON " +
                    "countries.Country_ID = first_level_divisions.Country_ID)";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                int custId = resSet.getInt("Customer_ID");
                String custName = resSet.getString("Customer_Name");
                String custAddress = resSet.getString("Address");
                String custPostalCode = resSet.getString("Postal_Code");
                String custPhone = resSet.getString("Phone");
                String custDivision = resSet.getString("Division");
                String custCountry = resSet.getString("Country");

                allCustomersObservableList.add(new Customer(custId, custName, custAddress, custPostalCode, custPhone,
                        custDivision, custCountry));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allCustomersObservableList;
    }

    /**
     * Add customer. This method is used to add a new customer to the database.
     *
     * @param customer the customer
     */
    public static void addCustomer(Customer customer) {
        try {
            String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, " +
                    "now(), ?, now(), ?, ?)";
            PreparedStatement prepState = getConnection.prepareStatement(sql);

            prepState.setInt(1, customer.getCustId());
            prepState.setString(2, customer.getCustName());
            prepState.setString(3, customer.getCustAddress());
            prepState.setString(4, customer.getCustPostalCode());
            prepState.setString(5, customer.getCustPhone());
            prepState.setString(6, currentUsername.toString());
            prepState.setString(7, currentUsername.toString());
            prepState.setInt(8, getDivisionId(customer.getCustDivision()));

            prepState.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Modify customer. This method is used to modify a customer in the database.
     *
     * @param customer the customer
     */
    public static void modifyCustomer(Customer customer) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                    "Last_Update = now(), Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);

            prepState.setString(1, customer.getCustName());
            prepState.setString(2, customer.getCustAddress());
            prepState.setString(3, customer.getCustPostalCode());
            prepState.setString(4, customer.getCustPhone());
            prepState.setString(5, currentUsername.toString());
            prepState.setInt(6, getDivisionId(customer.getCustDivision()));
            prepState.setInt(7, customer.getCustId());

            prepState.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Delete customer. This method is used to delete a customer from the database.
     *
     * @param custId the cust id
     * @throws SQLException the sql exception
     */
    public static void deleteCustomer(int custId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";

        PreparedStatement prepState = getConnection.prepareStatement(sql);
        prepState.setInt(1, custId);
        prepState.executeUpdate();
    }

    /**
     * Gets new cust id. This method is used to get the next available customer ID.
     *
     * @return the new cust id
     */
    public static int getNewCustId() {
        int newCustId = 0;
        try {
            String sql = "SELECT MAX(Customer_ID) FROM customers";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                newCustId = resSet.getInt("MAX(Customer_ID)") + 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newCustId;
    }
}