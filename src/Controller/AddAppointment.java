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

import static DAO.appointmentsDAO.*;
import static DAO.contactsDAO.getAllContacts;
import static DAO.customersDAO.getAllCustomers;
import static DAO.usersDAO.getAllUsers;
import static Model.Appointment.*;

/**
 * This class is the controller for the  Add Appointment screen.
 */
public class AddAppointment implements Initializable {

    @FXML
    private TextField addApptApptId;

    @FXML
    private ComboBox<Contact> addApptContactCombo;

    @FXML
    private ComboBox<Customer> addApptCustomerIdCombo;

    @FXML
    private TextField addApptDesc;

    @FXML
    private DatePicker addApptEndDatePicker;

    @FXML
    private ComboBox<LocalTime> addApptEndTimeCombo;

    @FXML
    private TextField addApptLocation;

    @FXML
    private DatePicker addApptStartDatePicker;

    @FXML
    private ComboBox<LocalTime> addApptStartTimeCombo;

    @FXML
    private TextField addApptTitle;

    @FXML
    private TextField addApptType;

    @FXML
    private ComboBox<User> addApptUserIdCombo;
    /**
     * The Stage.
     */
    Stage stage;
    /**
     * The Scene.
     */
    Parent scene;

    /**
     * On action save appt. This method saves the new appointment to the database. It also checks for complete fields
     * and performs error checking for overlapping appointments.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionSaveAppt(ActionEvent actionEvent) throws IOException {


        if (addApptApptId.getText().isEmpty() || addApptContactCombo.getValue() == null ||
                addApptCustomerIdCombo.getValue() == null || addApptDesc.getText().isEmpty()
                || addApptEndDatePicker.getValue() == null || addApptEndTimeCombo.getValue() == null
                || addApptLocation.getText().isEmpty() || addApptStartDatePicker.getValue() == null
                || addApptStartTimeCombo.getValue() == null || addApptTitle.getText().isEmpty()
                || addApptType.getText().isEmpty() || addApptUserIdCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Complete all fields to save appointment.");
            alert.showAndWait();

        } else {

            int apptId = Integer.parseInt(addApptApptId.getText());
            String apptTitle = addApptTitle.getText();
            String apptDescription = addApptDesc.getText();
            String apptLocation = addApptLocation.getText();
            String apptType = addApptType.getText();
            LocalDateTime startDateAndTime = LocalDateTime.of(addApptStartDatePicker.getValue(), addApptStartTimeCombo.getValue());
            LocalDateTime endDateAndTime = LocalDateTime.of(addApptEndDatePicker.getValue(), addApptEndTimeCombo.getValue());
            int customerId = addApptCustomerIdCombo.getValue().getCustId();
            int userId = addApptUserIdCombo.getValue().getUserId();
            int contactId = addApptContactCombo.getValue().getContactId();

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
                addAppointment(appointment);

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MainScreen.fxml")));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }

    /**
     * On action display main. This method exits the Add Appointment form and returns to the Main Screen.
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addApptApptId.setText(String.valueOf(getNewApptId()));
        addApptContactCombo.setItems(getAllContacts());
        addApptUserIdCombo.setItems(getAllUsers());
        addApptCustomerIdCombo.setItems(getAllCustomers());

        addApptStartTimeCombo.setItems(getAllTimes());
        addApptEndTimeCombo.setItems(getAllTimes());

    }
}
