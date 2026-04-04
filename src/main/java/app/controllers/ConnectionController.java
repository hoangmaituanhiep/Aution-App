package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class ConnectionController {
    @FXML private TextField getUserName;
    @FXML private PasswordField getPassword;
    @FXML private Label status;
    @FXML private PasswordField confirmPassword;

    private ConnectionService service = new ConnectionService();

    @FXML
    private void handleLogin() {
        if(service.authenticate(getUserName.getText(), getPassword.getText())) {
            status.setText("Successfull!");
        }
        else {
            status.setText("Failed to login.");
        }
    }

    @FXML
    private void handleSignUp() {
    }
}
