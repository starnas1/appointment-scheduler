package DAO;

import Model.Country;
import Utilities.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Countries dao. This class contains methods used to access the countries table in the database.
 */
public class countriesDAO {

    /**
     * The constant getConnection. This is the connection to the database.
     */
    public static Connection getConnection = databaseConnection.getConnection();

    /**
     * Gets all countries. This method returns an observable list of all countries in the database.
     *
     * @return the all countries
     */
    public static ObservableList<Country> getAllCountries() {

        ObservableList<Country> allCountriesObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                int countryId = resSet.getInt("Country_ID");
                String countryName = resSet.getString("Country");

                allCountriesObservableList.add(new Country(countryId, countryName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCountriesObservableList;
    }

    /**
     * Gets country id. This method returns the country ID of a country using the country name.
     *
     * @param countryName the country name
     * @return the country id
     */
    public static int getCountryId(String countryName) {

        try {
            String sql = "SELECT Country_ID FROM countries WHERE Country = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setString(1, countryName);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getInt("Country_ID");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Gets country name. This method returns the country name of a country using the country ID.
     *
     * @param countryId the country id
     * @return the country name
     */
    public static String getCountryName(int countryId) {

        try {
            String sql = "SELECT Country FROM countries WHERE Country_ID = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setInt(1, countryId);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                return resSet.getString("Country");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}