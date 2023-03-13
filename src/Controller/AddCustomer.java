package Controller;

import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static DAO.countriesDAO.getAllCountries;
import static DAO.customersDAO.addCustomer;
import static DAO.customersDAO.getNewCustId;
import static DAO.firstLevelDivDAO.getDivsByCountryId;

/**
 * This class is the controller for the Add Customer screen.
 */
public class AddCustomer implements Initializable {

    @FXML
    private TextField addCustAddress;

    @FXML
    private ComboBox<Country> addCustCountryCombo;

    @FXML
    private TextField addCustCustID;

    @FXML
    private TextField addCustCustName;

    @FXML
    private ComboBox<Division> addCustDivisionCombo;

    @FXML
    private TextField addCustPhone;

    @FXML
    private TextField addCustPostalCode;
    /**
     * The Stage.
     */
    Stage stage;
    /**
     * The Scene.
     */
    Parent scene;

    /**
     * On action save cust. This method saves a new customer to the database. It also checks to verify that all fields
     * are completed before saving.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionSaveCust(ActionEvent actionEvent) throws IOException {



        if (addCustCustName.getText().isEmpty() || addCustAddress.getText().isEmpty() ||
                addCustPostalCode.getText().isEmpty() || addCustPhone.getText().isEmpty() ||
                addCustDivisionCombo.getValue() == null ||
                addCustCountryCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Complete all fields to save customer.");
            alert.showAndWait();
        } else {

            int custId = Integer.parseInt(addCustCustID.getText());
            String custName = addCustCustName.getText();
            String custAddress = addCustAddress.getText();
            String custPostalCode = addCustPostalCode.getText();
            String custPhone = addCustPhone.getText();
            String custDivision = addCustDivisionCombo.getValue().getDivName();
            String custCountry = addCustCountryCombo.getValue().getCountryName();

            Customer customer = new Customer(custId, custName, custAddress, custPostalCode, custPhone, custDivision, custCountry);
            addCustomer(customer);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MainScreen.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * On action display main. This method exits the Add Customer screen and returns to the Main Screen.
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

        addCustCountryCombo.setItems(getAllCountries());
        addCustCustID.setText(String.valueOf(getNewCustId()));

    }

    /**
     * On action select country. This method populates the Division combo box with the divisions associated with
     * the selected country.
     *
     * @param ignoredActionEvent the ignored action event
     */
    public void onActionSelectCountry(ActionEvent ignoredActionEvent) {

        int countryId = addCustCountryCombo.getSelectionModel().getSelectedItem().getCountryId();
        addCustDivisionCombo.setItems(getDivsByCountryId(countryId));

    }
}
