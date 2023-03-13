package Controller;

import Model.Appointment;
import Model.Contact;
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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import static DAO.appointmentsDAO.*;
import static DAO.contactsDAO.getAllContacts;
import static DAO.customersDAO.getAllCustomers;

/**
 * This class contains the methods used in the Report screen.
 */
public class Report implements Initializable {

    @FXML
    private TableView<Appointment> reportsApptContTableView;
    @FXML
    private TableView<Appointment> reportsCustApptTableView;
    @FXML
    private TableColumn<Appointment, String> reportsCustTypeCol;

    @FXML
    private Label reportsAprNumApptsLabel;

    @FXML
    private Label reportsAugNumApptsLabel;

    @FXML
    private TableColumn<Appointment, Integer> reportsContApptIdCol;

    @FXML
    private TableColumn<Appointment, Integer> reportsContCustCol;

    @FXML
    private TableColumn<Appointment, String> reportsContDescCol;

    @FXML
    private TableColumn<Appointment, String> reportsContTitleCol;

    @FXML
    private TableColumn<Appointment, String> reportsContTypeCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsContactEndCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsContactStartCol;

    @FXML
    private TableColumn<Appointment, Integer> reportsCustApptIdCol;

    @FXML
    private TableColumn<Appointment, Integer> reportsCustContCol;

    @FXML
    private TableColumn<Appointment, String> reportsCustDescCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsCustEndCol;

    @FXML
    private TableColumn<Appointment, String> reportsCustLocCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> reportsCustStartCol;

    @FXML
    private TableColumn<Appointment, String> reportsCustTitleCol;

    @FXML
    private Label reportsDecNumApptsLabel;

    @FXML
    private Label reportsFebNumApptsLabel;

    @FXML
    private Label reportsJanNumApptsLabel;

    @FXML
    private Label reportsJulNumApptsLabel;

    @FXML
    private Label reportsJuneNumApptsLabel;

    @FXML
    private Label reportsMarNumApptsLabel;

    @FXML
    private Label reportsMayNumApptsLabel;

    @FXML
    private Label reportsNovNumApptsLabel;

    @FXML
    private Label reportsOctNumApptsLabel;

    @FXML
    private ComboBox<String> reportsSelectApptTypeCombo;

    @FXML
    private ComboBox<Contact> reportsSelectContactCombo;

    @FXML
    private ComboBox<Customer> reportsSelectCustomerCombo;

    @FXML
    private Label reportsSepNumApptsLabel;

    /**
     * The Stage.
     */
    Stage stage;
    /**
     * The Scene.
     */
    Parent scene;


    /**
     * On action display main. This method exits the Report screen and returns to the Main screen.
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
     * On action type selected. This method displays the number of appointments by month for the selected appointment
     * type.
     *
     * @param ignoredActionEvent the ignored action event
     */
    public void onActionTypeSelected(ActionEvent ignoredActionEvent) {

        if (reportsSelectApptTypeCombo.getSelectionModel().getSelectedItem() != null) {

            String selectedApptType = reportsSelectApptTypeCombo.getSelectionModel().getSelectedItem();

            reportsJanNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 1)));
            reportsFebNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 2)));
            reportsMarNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 3)));
            reportsAprNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 4)));
            reportsMayNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 5)));
            reportsJuneNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 6)));
            reportsJulNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 7)));
            reportsAugNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 8)));
            reportsSepNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 9)));
            reportsOctNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 10)));
            reportsNovNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 11)));
            reportsDecNumApptsLabel.setText(String.valueOf(getApptCountByTypeByMonth(selectedApptType, 12)));

    }
    }

    /**
     * On action select contact. This method displays the scheduled appointments for the selected contact.
     *
     * @param ignoredActionEvent the ignored action event
     */
    public void onActionSelectContact(ActionEvent ignoredActionEvent) {

        if (reportsSelectContactCombo.getSelectionModel().getSelectedItem() != null) {

            Contact selectedContact = reportsSelectContactCombo.getSelectionModel().getSelectedItem();
            reportsApptContTableView.setItems(getApptsByContact(selectedContact));

        }


    }

    /**
     * On action select customer. This method displays the scheduled appointments for the selected customer.
     *
     * @param ignoredActionEvent the ignored action event
     */
    public void onActionSelectCustomer(ActionEvent ignoredActionEvent) {

        if (reportsSelectCustomerCombo.getSelectionModel().getSelectedItem() != null) {

            Customer selectedCustomer = reportsSelectCustomerCombo.getSelectionModel().getSelectedItem();
            reportsCustApptTableView.setItems(getApptsByCustomer(selectedCustomer));

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reportsSelectApptTypeCombo.setItems(getAllApptTypes());
        reportsSelectContactCombo.setItems(getAllContacts());
        reportsSelectCustomerCombo.setItems(getAllCustomers());

        reportsContApptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        reportsContDescCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        reportsContTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        reportsContactStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        reportsContactEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        reportsContCustCol.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));
        reportsContTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));

        reportsCustApptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        reportsCustTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        reportsCustTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        reportsCustDescCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        reportsCustLocCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        reportsCustContCol.setCellValueFactory(new PropertyValueFactory<>("apptContactId"));
        reportsCustStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        reportsCustEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));


    }
}
