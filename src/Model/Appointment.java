package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static DAO.appointmentsDAO.getAllAppointments;

/**
 * The type Appointment. This class holds methods and variables related to the appointment class.
 */
public class Appointment {

    private int apptId;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptType;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private int customerId;
    private int userId;
    private int contactId;


    /**
     * Instantiates a new Appointment.
     *
     * @param apptId           the appt id
     * @param apptTitle        the appt title
     * @param apptDescription  the appt description
     * @param apptLocation     the appt location
     * @param apptType         the appt type
     * @param startDateAndTime the start date and time
     * @param endDateAndTime   the end date and time
     * @param customerId       the customer id
     * @param userId           the user id
     * @param contactId        the contact id
     */
    public Appointment(
            int apptId,
            String apptTitle,
            String apptDescription,
            String apptLocation,
            String apptType,
            LocalDateTime startDateAndTime,
            LocalDateTime endDateAndTime,
            int customerId,
            int userId,
            int contactId
    ) {
        this.apptId = apptId;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Gets appt id.
     *
     * @return the appt id
     */
    public int getApptId() {

        return apptId;
    }

    /**
     * Gets appt title.
     *
     * @return the appt title
     */
    public String getApptTitle() {

        return apptTitle;
    }

    /**
     * Gets appt description.
     *
     * @return the appt description
     */
    public String getApptDescription() {

        return apptDescription;
    }

    /**
     * Gets appt location.
     *
     * @return the appt location
     */
    public String getApptLocation() {
        return apptLocation;

    }

    /**
     * Gets appt type.
     *
     * @return the appt type
     */
    public String getApptType() {
        return apptType;
    }

    /**
     * Gets start date and time.
     *
     * @return the start date and time
     */
    public LocalDateTime getStartDateAndTime() {
        return startDateAndTime;
    }

    /**
     * Gets end date and time.
     *
     * @return the end date and time
     */
    public LocalDateTime getEndDateAndTime() {
        return endDateAndTime;
    }

    /**
     * Gets customer id of the appointment.
     *
     * @return the appt customer id
     */
    public int getApptCustomerId() {
        return customerId;
    }

    /**
     * Gets user id of the appointment.
     *
     * @return the appt user id
     */
    public int getApptUserId() {
        return userId;
    }

    /**
     * Gets contact id of the appointment.
     *
     * @return the appt contact id
     */
    public int getApptContactId() {
        return contactId;
    }

    /**
     * Sets appt id.
     *
     * @param apptId the appt id
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     * Sets appt title.
     *
     * @param apptTitle the appt title
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /**
     * Sets appt description.
     *
     * @param apptDescription the appt description
     */
    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    /**
     * Sets appt location.
     *
     * @param apptLocation the appt location
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    /**
     * Sets appt type.
     *
     * @param apptType the appt type
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    /**
     * Sets start date and time.
     *
     * @param startDateAndTime the start date and time
     */
    public void setStartDateAndTime(LocalDateTime startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    /**
     * Sets end date and time.
     *
     * @param endDateAndTime the end date and time
     */
    public void setEndDateAndTime(LocalDateTime endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }

    /**
     * Sets appt customer id.
     *
     * @param customerId the customer id
     */
    public void setApptCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Sets appt user id.
     *
     * @param userId the user id
     */
    public void setApptUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets appt contact id.
     *
     * @param contactId the contact id
     */
    public void setApptContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Is within business hours boolean. This checks if a given appointment time is within business hours.
     *
     * @param time the time
     * @return the boolean
     */
    public static boolean isWithinBusinessHours(LocalDateTime time) {

        LocalTime businessStartTime = LocalTime.of(8, 0);
        LocalTime businessEndTime = LocalTime.of(22, 0);
        ZonedDateTime localTime = ZonedDateTime.of(time, ZoneId.systemDefault());
        ZonedDateTime convertLocalToEST = ZonedDateTime.ofInstant(localTime.toInstant(), ZoneId.of("America/New_York"));

        return (((!convertLocalToEST.toLocalTime().isAfter(businessStartTime)) &&
                ((!convertLocalToEST.toLocalTime().equals(businessStartTime)))) ||
                ((!convertLocalToEST.toLocalTime().isBefore(businessEndTime)) &&
                        (!convertLocalToEST.toLocalTime().equals(businessEndTime))));
    }

    /**
     * Is overlapping appt boolean. This checks if a given appointment overlaps with another appointment.
     *
     * @param startDateAndTime the start date and time
     * @param endDateAndTime   the end date and time
     * @param customerId       the customer id
     * @param apptId           the appt id
     * @return the boolean
     */
    public static boolean isOverlappingAppt(LocalDateTime startDateAndTime, LocalDateTime endDateAndTime, int customerId, int apptId) {

        ObservableList<Appointment> allAppointments;
        allAppointments = getAllAppointments();

        for (Appointment appointment : allAppointments) {
            if (appointment.getApptCustomerId() == customerId && appointment.getApptId() != apptId) {
                if (((startDateAndTime.isAfter(appointment.getStartDateAndTime()) && startDateAndTime.isBefore(appointment.getEndDateAndTime())) ||
                        (endDateAndTime.isAfter(appointment.getStartDateAndTime()) && endDateAndTime.isBefore(appointment.getEndDateAndTime())) ||
                        (startDateAndTime.isBefore(appointment.getStartDateAndTime()) && endDateAndTime.isAfter(appointment.getEndDateAndTime())) ||
                        (startDateAndTime.isEqual(appointment.getStartDateAndTime()) || endDateAndTime.isEqual(appointment.getEndDateAndTime())))) {
                    return true;
                }
            }
        }
        return false;

    }



    /* Function not needed - original requirements were unclear
    public static boolean isWithinBusinessDays(LocalDateTime time) {

        ZonedDateTime localTime = ZonedDateTime.of(time, ZoneId.systemDefault());
        ZonedDateTime convertLocalToEST = ZonedDateTime.ofInstant(localTime.toInstant(), ZoneId.of("America/New_York"));

        return (((convertLocalToEST.toLocalDate().getDayOfWeek().getValue() >= 1) &&
                (convertLocalToEST.toLocalDate().getDayOfWeek().getValue() <= 5)));
    }
    */

    /**
     * Gets all times. This creates a list of all times in 15 minute increments.
     *
     * @return the all times
     */
    public static ObservableList<LocalTime> getAllTimes() {
        ObservableList<LocalTime> allTimes = FXCollections.observableArrayList();
        for (int i = 0; i <= 23; i++) {
            allTimes.add(LocalTime.of(i, 0));
            allTimes.add(LocalTime.of(i, 15));
            allTimes.add(LocalTime.of(i, 30));
            allTimes.add(LocalTime.of(i, 45));
        }
        return allTimes;
    }





}
