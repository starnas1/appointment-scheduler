package DAO;

import Model.Division;
import Utilities.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type First level div dao.
 */
public class firstLevelDivDAO {
    /**
     * The constant getConnection. This is the connection to the database.
     */
    public static Connection getConnection = databaseConnection.getConnection();

    /**
     * Gets all divisions. This method returns all divisions in the database.
     *
     * @return the all divisions
     */
    public static ObservableList<Division> getAllDivisions() {

        ObservableList<Division> allDivisionsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                int divId = resSet.getInt("Division_ID");
                String divName = resSet.getString("Division");
                int countryId = resSet.getInt("Country_ID");

                allDivisionsObservableList.add(new Division(divId, divName, countryId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allDivisionsObservableList;
    }

    /**
     * Gets division id. This method returns the division ID of a division using the division name.
     *
     * @param divName the div name
     * @return the division id
     */
    public static int getDivisionId(String divName) {

        try {
            String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setString(1, divName);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getInt("Division_ID");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Gets division name. This method returns the division name of a division using a division ID.
     *
     * @param divId the div id
     * @return the division name
     */
    public static String getDivisionName(int divId) {

        try {
            String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setInt(1, divId);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getString("Division");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    /**
     * Gets divs by country id. This method returns all divisions in a country using a country ID.
     *
     * @param countryId the country id
     * @return the divs by country id
     */
    public static ObservableList<Division> getDivsByCountryId(int countryId) {

        ObservableList<Division> divsByCountry = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setInt(1, countryId);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                int divId = resSet.getInt("Division_ID");
                String divName = resSet.getString("Division");
                int countryId2 = resSet.getInt("Country_ID");

                divsByCountry.add(new Division(divId, divName, countryId2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return divsByCountry;
    }
}
