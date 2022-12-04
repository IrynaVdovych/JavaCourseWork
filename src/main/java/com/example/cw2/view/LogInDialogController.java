package com.example.cw2.view;

import com.example.cw2.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInDialogController {
    @FXML
    private TextField logInField;
    @FXML
    private PasswordField passwordField;

    private MainApplication mainApplication;

    private Stage dialogStage;

    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void handleOk(ActionEvent actionEvent) {
        if (isInputValid()) {
            String logIn = logInField.getText();
            String password = passwordField.getText();
            if("admin".equals(logIn.toLowerCase())) {
                 this.okClicked = true;
                mainApplication.setUser("admin");
            }
            else if ("user".equals(logIn.toLowerCase())) {
                this.okClicked = true;
                mainApplication.setUser("user");
            }
            else {
                this.okClicked = false;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid user");
                alert.setHeaderText("Invalid user");
                alert.setContentText("Username is not valid");
                alert.showAndWait();
            }
            dialogStage.close();
        }

    }

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (logInField.getText() == null || logInField.getText().length() == 0) {
            errorMessage += "No valid logIn!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApplication.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
