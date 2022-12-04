package com.example.cw2.view;

import com.example.cw2.MainApplication;
import com.example.cw2.model.BaseAutoFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class TaxoparkSearchDialogController {
    @FXML
    private TableView<BaseAutoFX> autoTable;
    @FXML
    private TableColumn<BaseAutoFX, String> regNumberColumn;
    @FXML
    private TableColumn<BaseAutoFX, String> brandColumn;
    @FXML
    private TableColumn<BaseAutoFX, String> modelColumn;
    @FXML
    private TableColumn<BaseAutoFX, Integer> avgVelocityColumn;

    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;

    private MainApplication mainApplication;

    @FXML
    private void initialize() {
        regNumberColumn.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        avgVelocityColumn.setCellValueFactory(cellData -> cellData.getValue().avgVelocityProperty().asObject());
    }

    @FXML
    public void handleSearch(ActionEvent actionEvent) {
        if (isInputValid()) {
            int minVelocity = Integer.parseInt(fromField.getText());
            int maxVelocity = Integer.parseInt(toField.getText());

            mainApplication.setAutoData(this.mainApplication.getTp().velocityInRange(minVelocity, maxVelocity));
            autoTable.setItems(mainApplication.getAutoData());
        }

    }

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (fromField.getText() == null || fromField.getText().length() == 0) {
            errorMessage += "No valid from!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(fromField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid from (must be an integer)!\n";
            }
        }
        if (toField.getText() == null || toField.getText().length() == 0) {
            errorMessage += "No valid to!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(toField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid to (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApplication.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Please select an automobile in the table.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}
