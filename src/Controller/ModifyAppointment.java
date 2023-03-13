package Controller;

import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

import static DAO.appointmentsDAO.modifyAppointment;
import static DAO.contactsDAO.getAllContacts;
import static DAO.customersDAO.getAllCustomers;
import static DAO.usersDAO.getAllUsers;
import static Model.Appointment.*;
import static Model.Contact.getContFromContId;
import static Model.Customer.getCustFromCustId;
import static Model.User.getUserFromUserId;

/**
 * This class is the controller for the Modify Appointment Form.
 */
public class ModifyAppointment implements Initializable {

    @FXML
    private TextField modApptApptId;

    @FXML
    private ComboBox<Contact> modApptContactCombo;

    @FXML
    private ComboBox<Customer> modApptCustomerIdCombo;

    @FXML
    private TextField modApptDesc;

    @FXML
    private DatePicker modApptEndDatePicker;

    @FXML
    private ComboBox<LocalTime> modApptEndTimeCombo;

    @FXML
    private TextField modApptLocation;

    @FXML
    private DatePicker modApptStartDatePicker;

    @FXML
    private ComboBox<LocalTime> modApptStartTimeCombo;

    @FXML
    private TextField modApptTitle;

    @FXML
    private TextField modApptType;

    @FXML
    private ComboBox<User> modApptUserIdCombo;
    /**
     * The Stage.
     */
    Stage stage;
    /**
     * The Scene.
     */
    Parent scene;

    /**
     * On action save mod appt. This method saves the modified appointment to the database. It also checks for empty
     * fields and displays an error if they are found. It performs checks to verify that the scheduled modified
     * appointment does not overlap with any other appointments.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionSaveModAppt(ActionEvent actionEvent) throws IOException {

        if (modApptApptId.getText().isEmpty() || modApptContactCombo.getValue() == null ||
                modApptCustomerIdCombo.getValue() == null || modApptDesc.getText().isEmpty()
                || modApptEndDatePicker.getValue() == null || modApptEndTimeCombo.getValue() == null
                || modApptLocation.getText().isEmpty() || modApptStartDatePicker.getValue() == null
                || modApptStartTimeCombo.getValue() == null || modApptTitle.getText().isEmpty()
                || modApptType.getText().isEmpty() || modApptUserIdCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Complete all fields to save appointment.");
            alert.showAndWait();
        } else {
            int apptId = Integer.parseInt(modApptApptId.getText());
            String apptTitle = modApptTitle.getText();
            String apptDescription = modApptDesc.getText();
            String apptLocation = modApptLocation.getText();
            String apptType = modApptType.getText();
            LocalDateTime startDateAndTime = LocalDateTime.of(modApptStartDatePicker.getValue(), modApptStartTimeCombo.getValue());
            LocalDateTime endDateAndTime = LocalDateTime.of(modApptEndDatePicker.getValue(), modApptEndTimeCombo.getValue());
            int customerId = modApptCustomerIdCombo.getValue().getCustId();
            int userId = modApptUserIdCombo.getValue().getUserId();
            int contactId = modApptContactCombo.getValue().getContactId();

            if ((startDateAndTime.isAfter(endDateAndTime)) || (startDateAndTime.isEqual(endDateAndTime))) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Start date and time must be before end date and time.");
                alert.showAndWait();
            } else if (isWithinBusinessHours(startDateAndTime)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment start must be within business hours of " +
                        "08:00 to 22:00 EST.");
                alert.showAndWait();
            } else if (isWithinBusinessHours(endDateAndTime)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment start must be within business hours of " +
                        "08:00 to 22:00 EST.");
                alert.showAndWait();
            } else if (isOverlappingAppt(startDateAndTime, endDateAndTime, customerId, apptId)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment overlaps with existing appointment");
                alert.showAndWait();

            } else {

                Appointment appointment = new Appointment(apptId, apptTitle, apptDescription, apptLocation, apptType,
                        startDateAndTime, endDateAndTime, customerId, userId, contactId);
                modifyAppointment(appointment);

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MainScreen.fxml")));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        }

    }


    /**
     * On action display main. This method exits the modify appointment form and returns to the main screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionDisplayMain(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainScreen.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Gets selected appt. This method gets the selected appointment from the main screen and populates the Modify
     * Appointment screen with the appointment's data.
     *
     * @param appointment the appointment
     */
    public void getSelectedAppt(Appointment appointment) {

        modApptApptId.setText(String.valueOf(appointment.getApptId()));
        modApptTitle.setText(appointment.getApptTitle());
        modApptDesc.setText(appointment.getApptDescription());
        modApptLocation.setText(appointment.getApptLocation());
        modApptType.setText(appointment.getApptType());


        modApptContactCombo.setValue(getContFromContId(appointment.getApptContactId()));
        modApptUserIdCombo.setValue(getUserFromUserId(appointment.getApptUserId()));
        modApptCustomerIdCombo.setValue(getCustFromCustId(appointment.getApptCustomerId()));

        modApptStartDatePicker.setValue(appointment.getStartDateAndTime().toLocalDate());
        modApptStartTimeCombo.setValue(appointment.getStartDateAndTime().toLocalTime());
        modApptEndDatePicker.setValue(appointment.getEndDateAndTime().toLocalDate());
        modApptEndTimeCombo.setValue(appointment.getEndDateAndTime().toLocalTime());




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modApptContactCombo.setItems(getAllContacts());
        modApptUserIdCombo.setItems(getAllUsers());
        modApptCustomerIdCombo.setItems(getAllCustomers());

        modApptStartTimeCombo.setItems(getAllTimes());
        modApptEndTimeCombo.setItems(getAllTimes());

    }
}
