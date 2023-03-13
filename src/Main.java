import Utilities.databaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {



        FXMLLoader startup = new FXMLLoader(getClass().getResource("/View/LoginScreen.fxml"));

        //Previously used FXMLLoader settings before translation and final product
        //FXMLLoader startup = new FXMLLoader(getClass().getResource("/View/MainScreen.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/View/LoginScreen.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));

        startup.setResources(ResourceBundle.getBundle("Utilities/translate", Locale.getDefault()));

        stage.setTitle(startup.getResources().getString("screen_title"));
        stage.setScene(new Scene(startup.load(), 600, 400));
        stage.show();



    }



    public static void main(String[] args) {
        databaseConnection.openConnection();
        launch(args);
        databaseConnection.closeConnection();
    }


}