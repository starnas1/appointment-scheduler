package DAO;

import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Utilities.databaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

import static DAO.usersDAO.currentUsername;

/**
 * The type Appointments dao. This class contains methods used to access the appointments table in the database.
 */
public class appointmentsDAO {

    /**
     * The constant getConnection. This is the connection to the database.
     */
    public static Connection getConnection = databaseConnection.getConnection();

    /**
     * Gets all appointments. This method gets all appointments from the database.
     *
     * @return the all appointments
     */
    public static ObservableList<Appointment> getAllAppointments() {

        ObservableList<Appointment> allApptsObservableList = FXCollections.observableArrayList();


        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                int apptId = resSet.getInt("Appointment_ID");
                String apptTitle = resSet.getString("Title");
                String apptDescription = resSet.getString("Description");
                String apptLocation = resSet.getString("Location");
                String apptType = resSet.getString("Type");
                LocalDateTime startDateAndTime = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateAndTime = resSet.getTimestamp("End").toLocalDateTime();
                int customerId = resSet.getInt("Customer_ID");
                int userId = resSet.getInt("User_ID");
                int contactId = resSet.getInt("Contact_ID");

                allApptsObservableList.add(new Appointment(apptId, apptTitle, apptDescription, apptLocation, apptType,
                        startDateAndTime, endDateAndTime, customerId, userId, contactId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allApptsObservableList;
    }

    /**
     * Add appointment. This method adds a new appointment to the database.
     *
     * @param appointment the appointment
     */
    public static void addAppointment(Appointment appointment) {
        try {
            String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, now(), ?, now(), ?, ?, ?, ?)";
            PreparedStatement prepState = getConnection.prepareStatement(sql);

            prepState.setInt(1, appointment.getApptId());
            prepState.setString(2, appointment.getApptTitle());
            prepState.setString(3, appointment.getApptDescription());
            prepState.setString(4, appointment.getApptLocation());
            prepState.setString(5, appointment.getApptType());
            prepState.setTimestamp(6, Timestamp.valueOf(appointment.getStartDateAndTime()));
            prepState.setTimestamp(7, Timestamp.valueOf(appointment.getEndDateAndTime()));
            prepState.setString(8, currentUsername.toString());
            prepState.setString(9, currentUsername.toString());
            prepState.setInt(10, appointment.getApptCustomerId());
            prepState.setInt(11, appointment.getApptUserId());
            prepState.setInt(12, appointment.getApptContactId());


            prepState.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Modify appointment. This method modifies an existing appointment in the database.
     *
     * @param appointment the appointment
     */
    public static void modifyAppointment(Appointment appointment) {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, " +
                    "End = ?, Customer_ID = ?, Last_Update = now(), Last_Updated_By = ?, User_ID = ?, Contact_ID = ? " +
                    "WHERE Appointment_ID = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);

            prepState.setString(1, appointment.getApptTitle());
            prepState.setString(2, appointment.getApptDescription());
            prepState.setString(3, appointment.getApptLocation());
            prepState.setString(4, appointment.getApptType());
            prepState.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateAndTime()));
            prepState.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateAndTime()));
            prepState.setInt(7, appointment.getApptCustomerId());
            prepState.setString(8, currentUsername.toString());
            prepState.setInt(9, appointment.getApptUserId());
            prepState.setInt(10, appointment.getApptContactId());
            prepState.setInt(11, appointment.getApptId());

            prepState.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete appointment. This method deletes an existing appointment from the database.
     *
     * @param apptId the appt id
     * @throws SQLException the sql exception
     */
    public static void deleteAppointment(int apptId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

        PreparedStatement prepState = getConnection.prepareStatement(sql);
        prepState.setInt(1, apptId);
        prepState.executeUpdate();
    }

    /**
     * Gets upcoming appts. This method gets all appointments within the next 15 minutes from the database.
     *
     * @param now the now
     * @return the upcoming appts
     */
    public static ObservableList<Appointment> getUpcomingAppts(LocalDateTime now) {

        LocalDateTime nowPlus15Minutes = now.plusMinutes(15);
        ObservableList<Appointment> allAppointments = getAllAppointments();
        ObservableList<Appointment> apptsWithin15Minutes = FXCollections.observableArrayList();

        for (Appointment appointment : allAppointments) {

            if (appointment.getStartDateAndTime().isAfter(now) &&
                    appointment.getStartDateAndTime().isBefore(nowPlus15Minutes)) {
                apptsWithin15Minutes.add(appointment);
            }
        }
        return apptsWithin15Minutes;

    }

    /**
     * Gets next week appts. This method gets all appointments within the next week from the database.
     *
     * @param now the now
     * @return the next week appts
     */
    public static ObservableList<Appointment> getNextWeekAppts(LocalDateTime now) {

        LocalDateTime nextWeek = LocalDateTime.now().plusWeeks(1);
        ObservableList<Appointment> allAppointments = getAllAppointments();
        ObservableList<Appointment> nextWeekAppts = FXCollections.observableArrayList();

        for (Appointment appointment : allAppointments) {
            if ((appointment.getStartDateAndTime().isBefore(nextWeek)) &&
                    (appointment.getStartDateAndTime().isAfter(now))) {
                nextWeekAppts.add(appointment);
            }
        }
        return nextWeekAppts;

    }

    /**
     * Gets next month appts. This method gets all appointments within the next month from the database.
     *
     * @param now the now
     * @return the next month appts
     */
    public static ObservableList<Appointment> getNextMonthAppts(LocalDateTime now) {

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        ObservableList<Appointment> allAppointments = getAllAppointments();
        ObservableList<Appointment> nextMonthAppts = FXCollections.observableArrayList();

        for (Appointment appointment : allAppointments) {
            if ((appointment.getStartDateAndTime().isBefore(nextMonth)) &&
                    (appointment.getStartDateAndTime().isAfter(now))) {
                nextMonthAppts.add(appointment);
            }
        }
        return nextMonthAppts;

    }

    /**
     * Gets new appt id. This method gets the next available appointment ID from the database.
     *
     * @return the new appt id
     */
    public static int getNewApptId() {
        int newApptId = 0;
        try {
            String sql = "SELECT MAX(Appointment_ID) FROM appointments";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                newApptId = resSet.getInt("MAX(Appointment_ID)") + 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newApptId;
    }

    /**
     * Gets all appt types. This method gets all appointment types from the database.
     *
     * @return the all appt types
     */
    public static ObservableList<String> getAllApptTypes() {
        ObservableList<String> allApptTypes = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Type FROM appointments";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                allApptTypes.add(resSet.getString("Type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allApptTypes;

    }

    /**
     * Gets appt count by type by month. This method gets the number of appointments by type by month from the database.
     *
     * @param apptType the appt type
     * @param month    the month
     * @return the appt count by type by month
     */
    public static int getApptCountByTypeByMonth(String apptType, int month) {

        int apptCount = 0;
        try {
            String sql = "SELECT COUNT(*) FROM appointments WHERE Type = ? AND MONTH(Start) = ?";
            PreparedStatement prepState = getConnection.prepareStatement(sql);
            prepState.setString(1, apptType);
            prepState.setInt(2, month);
            ResultSet resSet = prepState.executeQuery();

            while (resSet.next()) {
                apptCount = resSet.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return apptCount;

    }

    /**
     * Gets appts by contact. This method gets all appointments for a specific contact from the database.
     *
     * @param selectedContact the selected contact
     * @return the appts by contact
     */
    public static ObservableList<Appointment> getApptsByContact(Contact selectedContact) {

        ObservableList<Appointment> allApptsByContact = FXCollections.observableArrayList();

        for (Appointment appointment : getAllAppointments()) {
            if (appointment.getApptContactId() == selectedContact.getContactId()) {
                allApptsByContact.add(appointment);
            }
        } return allApptsByContact;
    }

    /**
     * Gets appts by customer. This method gets all appointments for a specific customer from the database. It uses a
     * lambda expression to simplify the code used to filter all appointments by customer ID.
     *
     * @param selectedCustomer the selected customer
     * @return the appts by customer
     */
    public static ObservableList<Appointment> getApptsByCustomer(Customer selectedCustomer) {

        ObservableList<Appointment> allAppts = getAllAppointments();
        return allAppts.filtered(appointment ->
                appointment.getApptCustomerId() == selectedCustomer.getCustId());
    }
}



