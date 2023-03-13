package Model;

import static DAO.customersDAO.getAllCustomers;

/**
 * The type Customer. This is the class that contains the methods and variables related to the
 */
public class Customer {

    private int custId;
    private String custName;
    private String custAddress;
    private String custPostalCode;
    private String custPhone;
    private String custDivision;
    private String custCountry;


    /**
     * Instantiates a new Customer.
     *
     * @param custId         the cust id
     * @param custName       the cust name
     * @param custAddress    the cust address
     * @param custPostalCode the cust postal code
     * @param custPhone      the cust phone
     * @param custDivision   the cust division
     * @param custCountry    the cust country
     */
    public Customer(int custId, String custName, String custAddress, String custPostalCode, String custPhone, String custDivision, String custCountry) {
        this.custId = custId;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custPostalCode = custPostalCode;
        this.custPhone = custPhone;
        this.custDivision = custDivision;
        this.custCountry = custCountry;
    }

    /**
     * Gets cust id.
     *
     * @return the cust id
     */
    public int getCustId() {
        return custId;
    }

    /**
     * Sets cust id.
     *
     * @param custId the cust id
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * Gets cust name.
     *
     * @return the cust name
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets cust name.
     *
     * @param custName the cust name
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * Gets cust address.
     *
     * @return the cust address
     */
    public String getCustAddress() {
        return custAddress;
    }

    /**
     * Sets cust address.
     *
     * @param custAddress the cust address
     */
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    /**
     * Gets cust postal code.
     *
     * @return the cust postal code
     */
    public String getCustPostalCode() {
        return custPostalCode;
    }

    /**
     * Sets cust postal code.
     *
     * @param custPostalCode the cust postal code
     */
    public void setCustPostalCode(String custPostalCode) {
        this.custPostalCode = custPostalCode;
    }

    /**
     * Gets cust phone.
     *
     * @return the cust phone
     */
    public String getCustPhone() {
        return custPhone;
    }

    /**
     * Sets cust phone.
     *
     * @param custPhone the cust phone
     */
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    /**
     * Gets cust division.
     *
     * @return the cust division
     */
    public String getCustDivision() {
        return custDivision;
    }

    /**
     * Sets cust division.
     *
     * @param custDivision the cust division
     */
    public void setCustDivision(String custDivision) {
        this.custDivision = custDivision;
    }

    /**
     * Gets cust country.
     *
     * @return the cust country
     */
    public String getCustCountry() {
        return custCountry;
    }

    /**
     * Sets cust country.
     *
     * @param custCountry the cust country
     */
    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }

    /**
     * Gets customer from cust id.
     *
     * @param custId the cust id
     * @return the cust from cust id
     */
    public static Customer getCustFromCustId(int custId) {
        for (Customer customer : getAllCustomers()) {
            if (custId == customer.getCustId()) {
                return customer;
            }
        }
        return null;
    }

    public String toString() { return this.custName; }
}
