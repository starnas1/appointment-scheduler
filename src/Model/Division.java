package Model;

import static DAO.firstLevelDivDAO.getAllDivisions;

/**
 * The type Division. This is a class that contains the methods and variables related to the Division type.
 */
public class Division {

    private int divId;
    private String divName;
    private int countryId;

    /**
     * Instantiates a new Division.
     *
     * @param divId     the div id
     * @param divName   the div name
     * @param countryId the country id
     */
    public Division(int divId, String divName, int countryId) {
        this.divId = divId;
        this.divName = divName;
        this.countryId = countryId;
    }

    /**
     * Gets div id.
     *
     * @return the div id
     */
    public int getDivId() {
        return divId;
    }

    /**
     * Sets div id.
     *
     * @param divId the div id
     */
    public void setDivId(int divId) {
        this.divId = divId;
    }

    /**
     * Gets div name.
     *
     * @return the div name
     */
    public String getDivName() {
        return divName;
    }

    /**
     * Sets div name.
     *
     * @param divName the div name
     */
    public void setDivName(String divName) {
        this.divName = divName;
    }

    /**
     * Gets country id.
     *
     * @return the country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets country id.
     *
     * @param countryId the country id
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets division from div name.
     *
     * @param divName the div name
     * @return the div from div name
     */
    public static Division getDivFromDivName(String divName) {
        for (Division division : getAllDivisions()) {
            if (divName.equals(division.getDivName())) {
                return division;
            }
        }
        return null;
    }

    public String toString() { return this.divName; }
}
