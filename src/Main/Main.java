package Main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setTitle("Appointment Scheduler - Login");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }



    public static void main(String[] args) throws SQLException {

        launch(args);
    }

}