package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    public Button exitButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionLogin(ActionEvent actionEvent) {


    }
}

