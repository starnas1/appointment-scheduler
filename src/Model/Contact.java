package Model;

import static DAO.contactsDAO.getAllContacts;

/**
 * The type Contact. This class contains the methods and variables related to the Contact type
 */
public class Contact {

    /**
     * The Contact id.
     */
    public int contactId;
    /**
     * The Contact name.
     */
    public String contactName;
    /**
     * The Contact email.
     */
    public String contactEmail;

    /**
     * Instantiates a new Contact.
     *
     * @param contactId    the contact id
     * @param contactName  the contact name
     * @param contactEmail the contact email
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }


    /**
     * Gets contact id.
     *
     * @return the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Gets contact name.
     *
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Gets contact email.
     *
     * @return the contact email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets contact id.
     *
     * @param contactId the contact id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Sets contact name.
     *
     * @param contactName the contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Sets contact email.
     *
     * @param contactEmail the contact email
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Gets a contact from cont id.
     *
     * @param contId the cont id
     * @return the cont from cont id
     */
    public static Contact getContFromContId(int contId) {
        for (Contact contact : getAllContacts()) {
            if (contId == contact.getContactId()) {
                return contact;
            }
        }
        return null;
    }

    public String toString() { return this.contactName; }

}
