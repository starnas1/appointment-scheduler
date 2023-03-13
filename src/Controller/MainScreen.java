package Controller;

import Model.Appointment;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static DAO.appointmentsDAO.*;
import static DAO.customersDAO.deleteCustomer;
import static DAO.customersDAO.getAllCustomers;

/**
 * This class is the controller for the Main Screen.
 */
public class MainScreen implements Initializable {
    /**
     * The Stage.
     */
    Stage stage;
    /**
     * The Scene.
     */
    Parent scene;

    @FXML
    private TableColumn<Appointment, String> mainApptContCol1;

    @FXML
    private TableColumn<Appointment, Integer> mainApptCustIdCol1;

    @FXML
    private TableColumn<Appointment, String> mainApptDescCol1;

    @FXML
    private TableColumn<Appointment, String> mainApptEndCol1;

    @FXML
    private TableColumn<Appointment, Integer> mainApptIdCol1;

    @FXML
    private TableColumn<Appointment, String> mainApptLocationCol1;

    @FXML
    private TableColumn<Appointment, String> mainApptStartCol1;

    @FXML
    private TableColumn<Appointment, String> mainApptTitleCol1;

    @FXML
    private TableColumn<Appointment, String> mainApptTypeCol1;

    @FXML
    private TableColumn<Appointment, Integer> mainApptUserIdCol1;

    @FXML
    private TableColumn<Appointment, String> mainApptContCol2;
    @FXML
    private TableColumn<Appointment, Integer> mainApptCustIdCol2;

    @FXML
    private TableColumn<Appointment, String> mainApptDescCol2;

    @FXML
    private TableColumn<Appointment, String> mainApptEndCol2;

    @FXML
    private TableColumn<Appointment, Integer> mainApptIdCol2;

    @FXML
    private TableColumn<Appointment, String> mainApptLocationCol2;

    @FXML
    private TableColumn<Appointment, String> mainApptStartCol2;

    @FXML
    private TableColumn<Appointment, String> mainApptTitleCol2;

    @FXML
    private TableColumn<Appointment, String> mainApptTypeCol2;

    @FXML
    private TableColumn<Appointment, Integer> mainApptUserIdCol2;
    @FXML
    private TableColumn<Appointment, String> mainApptContCol3;

    @FXML
    private TableColumn<Appointment, Integer> mainApptCustIdCol3;

    @FXML
    private TableColumn<Appointment, String> mainApptDescCol3;

    @FXML
    private TableColumn<Appointment, String> mainApptEndCol3;

    @FXML
    private TableColumn<Appointment, Integer> mainApptIdCol3;

    @FXML
    private TableColumn<Appointment, String> mainApptLocationCol3;

    @FXML
    private TableColumn<Appointment, String> mainApptStartCol3;

    @FXML
    private TableColumn<Appointment, String> mainApptTitleCol3;

    @FXML
    private TableColumn<Appointment, String> mainApptTypeCol3;

    @FXML
    private TableColumn<Appointment, Integer> mainApptUserIdCol3;

    @FXML
    private TableColumn<Customer, String> mainCustAddressCol;

    @FXML
    private TableColumn<Customer, String> mainCustCountryCol;

    @FXML
    private TableColumn<Customer, String> mainCustDivisionCol;

    @FXML
    private TableColumn<Customer, Integer> mainCustIDCol;

    @FXML
    private TableColumn<Customer, String> mainCustNameCol;

    @FXML
    private TableColumn<Customer, String> mainCustPhoneCol;

    @FXML
    private TableColumn<Customer, String> mainCustPostalCol;

    @FXML
    private TableView<Appointment> mainApptsTableView1;
    @FXML
    private TableView<Appointment> mainApptsTableView2;
    @FXML
    private TableView<Appointment> mainApptsTableView3;

    @FXML
    private TableView<Customer> mainCustTableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainApptsTableView1.setItems(getAllAppointments());

        mainApptIdCol1.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        mainApptTitleCol1.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        mainApptDescCol1.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        mainApptLocationCol1.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        mainApptContCol1.setCellValueFactory(new PropertyValueFactory<>("apptContactId"));
        mainApptTypeCol1.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        mainApptStartCol1.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        mainApptEndCol1.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        mainApptCustIdCol1.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));
        mainApptUserIdCol1.setCellValueFactory(new PropertyValueFactory<>("apptUserId"));

        mainApptsTableView2.setItems(getNextWeekAppts(LocalDateTime.now()));

        mainApptIdCol2.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        mainApptTitleCol2.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        mainApptDescCol2.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        mainApptLocationCol2.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        mainApptContCol2.setCellValueFactory(new PropertyValueFactory<>("apptContactId"));
        mainApptTypeCol2.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        mainApptStartCol2.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        mainApptEndCol2.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        mainApptCustIdCol2.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));
        mainApptUserIdCol2.setCellValueFactory(new PropertyValueFactory<>("apptUserId"));

        mainApptsTableView3.setItems(getNextMonthAppts(LocalDateTime.now()));

        mainApptIdCol3.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        mainApptTitleCol3.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        mainApptDescCol3.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        mainApptLocationCol3.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        mainApptContCol3.setCellValueFactory(new PropertyValueFactory<>("apptContactId"));
        mainApptTypeCol3.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        mainApptStartCol3.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        mainApptEndCol3.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        mainApptCustIdCol3.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));
        mainApptUserIdCol3.setCellValueFactory(new PropertyValueFactory<>("apptUserId"));

        mainCustTableView.setItems(getAllCustomers());

        mainCustIDCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        mainCustNameCol.setCellValueFactory(new PropertyValueFactory<>("custName"));
        mainCustAddressCol.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        mainCustDivisionCol.setCellValueFactory(new PropertyValueFactory<>("custDivision"));
        mainCustCountryCol.setCellValueFactory(new PropertyValueFactory<>("custCountry"));
        mainCustPostalCol.setCellValueFactory(new PropertyValueFactory<>("custPostalCode"));
        mainCustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("custPhone"));

    }

    /**
     * On action add cust. This method displays the Add Customer screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionAddCust(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddCustomerScreen.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action mod cust. This method displays the Modify Customer screen. It also passes the selected customer to
     * the Modify Customer screen so the information can be populated. It also checks to ensure a customer is selected
     * in the TableView for modification and displays and error if one is not.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionModCust(ActionEvent actionEvent) throws IOException {

        if (mainCustTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the customer you wish to modify.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyCustomerScreen.fxml"));
            loader.load();

            ModifyCustomer ModCustCont = loader.getController();
            ModCustCont.getSelectedCustomer(mainCustTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * On action delete cust. This method deletes the selected customer from the database. It also checks to ensure a
     * customer is selected for deletion and displays an error if one is not. It also deletes all appointments
     * associated with the customer being deleted. It displays a confirmation screen before deleting the customer
     * and a screen afterward confirming the deletion.
     *
     * @param ignoredActionEvent the ignored action event
     * @throws SQLException the sql exception
     */
    public void onActionDeleteCust(ActionEvent ignoredActionEvent) throws SQLException {

        int custIdToDelete;
        String custNameToDelete;

        if (mainCustTableView.getSelectionModel().getSelectedItem() != null) {

            custIdToDelete = mainCustTableView.getSelectionModel().getSelectedItem().getCustId();
            custNameToDelete = mainCustTableView.getSelectionModel().getSelectedItem().getCustName();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this customer and all " +
                    "appointments associated with them? \n" +
                    "Customer ID: " + custIdToDelete + "\n" +
                    "Customer Name: " + custNameToDelete);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                for (Appointment appointment : getAllAppointments()) {
                    if (appointment.getApptCustomerId() == custIdToDelete) {
                        deleteAppointment(appointment.getApptId());
                    }
                }

                deleteCustomer(custIdToDelete);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "The following customer has been " +
                        "deleted. \n" +
                        "Customer ID: " + custIdToDelete + "\n" +
                        "Customer Name: " + custNameToDelete);
                alert2.showAndWait();

                mainCustTableView.setItems(getAllCustomers());
                mainApptsTableView1.setItems(getAllAppointments());
                mainApptsTableView2.setItems(getNextWeekAppts(LocalDateTime.now()));
                mainApptsTableView3.setItems(getNextMonthAppts(LocalDateTime.now()));
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the appointment you wish to delete.");
            alert.showAndWait();
        }
    }


    /**
     * On action add appt. This method displays the Add Appointment screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionAddAppt(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddAppointmentScreen.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action mod appt. This method displays the Modify Appointment screen. It also passes the selected appointment
     * so that the information can be populated to the form. It checks to ensure an appointment to be modified is
     * selected in the TableView and displays a warning if one is not.
     *
     * @param actionEvent the action event
     */
    public void onActionModAppt(ActionEvent actionEvent) {

        if (mainApptsTableView1.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/ModifyAppointmentScreen.fxml"));
                loader.load();

                ModifyAppointment ModApptCont = loader.getController();
                ModApptCont.getSelectedAppt(mainApptsTableView1.getSelectionModel().getSelectedItem());

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (mainApptsTableView2.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/ModifyAppointmentScreen.fxml"));
                loader.load();

                ModifyAppointment ModApptCont = loader.getController();
                ModApptCont.getSelectedAppt(mainApptsTableView2.getSelectionModel().getSelectedItem());

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (mainApptsTableView3.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/ModifyAppointmentScreen.fxml"));
                loader.load();

                ModifyAppointment ModApptCont = loader.getController();
                ModApptCont.getSelectedAppt(mainApptsTableView3.getSelectionModel().getSelectedItem());

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the appointment you wish to modify.");
            alert.showAndWait();
        }
    }

    /**
     * On action delete appt. This method deletes the selected appointment from the database. It also checks to ensure
     * an appointment is selected for deletion in the TableView and displays a warning if not. It also displays a
     * confirmation warning requiring a user to confirm deletion of the selected appointment and a screen confirming
     * once the appointment is deleted.
     *
     * @param ignoredActionEvent the ignored action event
     * @throws SQLException the sql exception
     */
    public void onActionDeleteAppt(ActionEvent ignoredActionEvent) throws SQLException {

        int apptToDeleteId;
        String apptToDeleteType;

        if (mainApptsTableView1.getSelectionModel().getSelectedItem() != null) {

            apptToDeleteId = mainApptsTableView1.getSelectionModel().getSelectedItem().getApptId();
            apptToDeleteType = mainApptsTableView1.getSelectionModel().getSelectedItem().getApptType();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this appointment? \n" +
                    "Appointment ID: " + apptToDeleteId + "\n" +
                    "Appointment Type: " + apptToDeleteType);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteAppointment(apptToDeleteId);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "The following appointment has been " +
                        "deleted. \n" +
                        "Appointment ID: " + apptToDeleteId + "\n" +
                        "Appointment Type: " + apptToDeleteType);
                alert2.showAndWait();
                mainApptsTableView1.setItems(getAllAppointments());
                mainApptsTableView2.setItems(getNextWeekAppts(LocalDateTime.now()));
                mainApptsTableView3.setItems(getNextMonthAppts(LocalDateTime.now()));
            }

        } else if (mainApptsTableView2.getSelectionModel().getSelectedItem() != null) {

            apptToDeleteId = mainApptsTableView2.getSelectionModel().getSelectedItem().getApptId();
            apptToDeleteType = mainApptsTableView2.getSelectionModel().getSelectedItem().getApptType();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this appointment? \n" +
                    "Appointment ID: " + apptToDeleteId + "\n" +
                    "Appointment Type: " + apptToDeleteType);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteAppointment(apptToDeleteId);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "The following appointment has been " +
                        "deleted. \n" +
                        "Appointment ID: " + apptToDeleteId + "\n" +
                        "Appointment Type: " + apptToDeleteType);
                alert2.showAndWait();
                mainApptsTableView1.setItems(getAllAppointments());
                mainApptsTableView2.setItems(getNextWeekAppts(LocalDateTime.now()));
                mainApptsTableView3.setItems(getNextMonthAppts(LocalDateTime.now()));
            }



        } else if (mainApptsTableView3.getSelectionModel().getSelectedItem() != null) {

            apptToDeleteId = mainApptsTableView3.getSelectionModel().getSelectedItem().getApptId();
            apptToDeleteType = mainApptsTableView3.getSelectionModel().getSelectedItem().getApptType();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this appointment? \n" +
                        "Appointment ID: " + apptToDeleteId + "\n" +
                        "Appointment Type: " + apptToDeleteType);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    deleteAppointment(apptToDeleteId);
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "The following appointment has been " +
                            "deleted. \n" +
                            "Appointment ID: " + apptToDeleteId + "\n" +
                            "Appointment Type: " + apptToDeleteType);
                    alert2.showAndWait();
                    mainApptsTableView1.setItems(getAllAppointments());
                    mainApptsTableView2.setItems(getNextWeekAppts(LocalDateTime.now()));
                    mainApptsTableView3.setItems(getNextMonthAppts(LocalDateTime.now()));
                }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the appointment you wish to delete.");
            alert.showAndWait();
        }

    }

    /**
     * On action reports. This method displays the reports screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionReports(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ReportScreen.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * On action logout. This method logs the user out and displays the login screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionLogout(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScreen.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * On action exit. This method exits the application.
     *
     * @param ignoredActionEvent the ignored action event
     */
    public void onActionExit(ActionEvent ignoredActionEvent) {
        System.exit(0);
    }

}
