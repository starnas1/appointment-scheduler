package Model;

import static DAO.countriesDAO.getAllCountries;

/**
 * The type Country. This is a class that contains the methods and variables related to the Country type
 */
public class Country {

    private int countryId;
    private String countryName;

    /**
     * Instantiates a new Country.
     *
     * @param countryId   the country id
     * @param countryName the country name
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
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
     * Gets country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets country name.
     *
     * @param countryName the country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Gets country from country name.
     *
     * @param countryName the country name
     * @return the country from country name
     */
    public static Country getCountryFromCountryName(String countryName) {
        for (Country country : getAllCountries()) {
            if (countryName.equals(country.getCountryName())) {
                return country;
            }
        }
        return null;
    }

    public String toString() { return this.countryName; }

}