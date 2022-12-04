package com.example.cw2.view;

import com.example.cw2.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.*;

public class RootLayoutController {

    private MainApplication mainApplication;

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Iryna Vdovych");

        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        Platform.exit();
    }
    @FXML
    private void handleTaxoparkEdit() {
        mainApplication.showTaxoparkView();
    }

    @FXML
    private void handleSearch() {
        mainApplication.showTaxoparkSearch();
    }

}
