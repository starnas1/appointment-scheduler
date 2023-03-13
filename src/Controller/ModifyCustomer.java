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
import static DAO.customersDAO.modifyCustomer;
import static DAO.firstLevelDivDAO.getAllDivisions;
import static Model.Country.getCountryFromCountryName;
import static Model.Division.getDivFromDivName;

/**
 * This class is the controller for the Modify Customer Form.
 */
public class ModifyCustomer implements Initializable {

    @FXML
    private TextField modCustAddress;

    @FXML
    private ComboBox<Country> modCustCountryCombo;

    @FXML
    private TextField modCustCustID;

    @FXML
    private TextField modCustCustName;

    @FXML
    private ComboBox<Division> modCustDivisionCombo;

    @FXML
    private TextField modCustPhone;

    @FXML
    private TextField modCustPostalCode;
    /**
     * The Stage.
     */
    Stage stage;
    /**
     * The Scene.
     */
    Parent scene;

    /**
     * On action save mod cust. This method saves the modified customer to the database. It also checks for empty
     * fields and displays an error message if any are found.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionSaveModCust(ActionEvent actionEvent) throws IOException {

        if (modCustCustName.getText().isEmpty() || modCustAddress.getText().isEmpty() ||
                modCustPostalCode.getText().isEmpty() || modCustPhone.getText().isEmpty() ||
                modCustDivisionCombo.getValue() == null ||
                modCustCountryCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Complete all fields to save customer.");
            alert.showAndWait();
        } else {

            int custId = Integer.parseInt(modCustCustID.getText());
            String custName = modCustCustName.getText();
            String custAddress = modCustAddress.getText();
            String custPostalCode = modCustPostalCode.getText();
            String custPhone = modCustPhone.getText();
            String custDivision = modCustDivisionCombo.getValue().getDivName();
            String custCountry = modCustCountryCombo.getValue().getCountryName();

            Customer customer = new Customer(custId, custName, custAddress, custPostalCode, custPhone, custDivision, custCountry);
            modifyCustomer(customer);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/MainScreen.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * On action display main. This method exits the Modify Customer Form and returns the user to the Main Screen.
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
     * Gets selected customer. This method gets the selected customer from the Main Screen and populates the Modify
     * Customer screen with the customer's information.
     *
     * @param customer the customer
     */
    public void getSelectedCustomer(Customer customer) {

        modCustCustID.setText(String.valueOf(customer.getCustId()));
        modCustCustName.setText(customer.getCustName());
        modCustAddress.setText(customer.getCustAddress());
        modCustPostalCode.setText(customer.getCustPostalCode());
        modCustPhone.setText(customer.getCustPhone());

        modCustCountryCombo.setValue(getCountryFromCountryName(customer.getCustCountry()));
        modCustDivisionCombo.setValue(getDivFromDivName(customer.getCustDivision()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modCustCountryCombo.setItems(getAllCountries());
        modCustDivisionCombo.setItems(getAllDivisions());

    }
}
