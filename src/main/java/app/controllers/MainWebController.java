package app.controllers;

import java.io.IOException;

import app.controllers.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainWebController {
    @FXML private TextField searchItems;
    @FXML private Button logIn;
    @FXML private Button join;
    @FXML private Label logInLabel;
    
    @FXML
    public void callLogIn() {
        try {
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/app/login.fxml"));
            Scene loginScene = new Scene(loginLoader.load());

            ConnectionController connectionController = loginLoader.getController();
            connectionController.setLoginListener(username -> {
                //this is for loginSucceeded function
                logIn.setVisible(false);
                logInLabel.setText("Wellcome " + username + " !");
                logInLabel.setVisible(true);
            });

            Stage loginStage = new Stage();
            loginStage.setScene(loginScene);
            loginStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
