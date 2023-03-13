package Controller;

import Model.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.appointmentsDAO.getUpcomingAppts;
import static DAO.usersDAO.checkLogin;


/**
 * This class is the controller for the login screen.
 */
public class Login implements Initializable {

    /**
     * The Exit button.
     */
    public Button exitButton;
    /**
     * The Username field.
     */
    public TextField usernameField;
    /**
     * The Password field.
     */
    public PasswordField passwordField;

    /**
     * The Login button.
     */
    public Button loginButton;

    @FXML
    private Label loginTitleLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label userLocDisplayLabel;

    @FXML
    private Label userLocLabel;

    @FXML
    private Label usernameLabel;

    private ResourceBundle resBundle;

    /**
     * The Stage.
     */
    Stage stage;
    /**
     * The Scene.
     */
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Used to check French translation worked
        //Locale frLocale = new Locale("fr", "FR");
        //Locale.setDefault(frLocale);

        resBundle = ResourceBundle.getBundle("Utilities/translate", Locale.getDefault());

        loginTitleLabel.setText(resBundle.getString("header"));
        usernameLabel.setText(resBundle.getString("username"));
        passwordLabel.setText(resBundle.getString("password"));
        loginButton.setText(resBundle.getString("login"));
        exitButton.setText(resBundle.getString("exit"));
        userLocLabel.setText(resBundle.getString("location"));
        userLocDisplayLabel.setText(String.valueOf(ZoneId.systemDefault()));


    }

    /**
     * On action exit. This method exits the program.
     *
     * @param ignoredActionEvent the ignored action event
     */
    public void onActionExit(ActionEvent ignoredActionEvent) {
        System.exit(0);
    }


    /**
     * On action login. This method allows a user to log in to the program. It checks to ensure the username and
     * password are correct. It also logs login activity. It also displays an alert if there are appointments
     * scheduled within 15 minutes of a successful login. It uses a lambda expression to simplify the code for the
     * upcoming appointments alert messages.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void onActionLogin(ActionEvent actionEvent) throws IOException {



        FileWriter loginActivityLog = new FileWriter("login_activity.text", true);
        PrintWriter output = new PrintWriter(loginActivityLog);

        String username = usernameField.getText();
        String password = passwordField.getText();

        LocalDateTime now = LocalDateTime.now();

        ObservableList<Appointment> upcomingAppointments;


        if (checkLogin(username, password)) {

            output.println("Successful login by user: " + username + " at " + Timestamp.valueOf(LocalDateTime.now()));
            output.close();

            upcomingAppointments = getUpcomingAppts(now);

            if (upcomingAppointments.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "You have no upcoming appointments.");
                alert.showAndWait();
            } else {
                upcomingAppointments.forEach(appointment -> {
                    String alertMessage = "You have an upcoming appointment.\n" +
                    "Appt ID: " + appointment.getApptId() + "\n" + "Date: " +
                            appointment.getStartDateAndTime().toLocalDate() + "\n" + "Time: "
                            + appointment.getStartDateAndTime().toLocalTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMessage);
                    alert.showAndWait();
                });
            }




            FXMLLoader mainLoader = new FXMLLoader();
            mainLoader.setLocation(getClass().getResource("/view/MainScreen.fxml"));
            mainLoader.load();

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = mainLoader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
        else {
            output.println("Failed login attempt by user: " + username + " at " + Timestamp.valueOf(LocalDateTime.now()));
            output.close();

            Alert alert = new Alert(Alert.AlertType.ERROR, resBundle.getString("error"));
            alert.showAndWait();
        }


    }





}

