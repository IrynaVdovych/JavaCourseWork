package com.example.cw2.view;

import com.example.cw2.model.BaseAutoFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutoEditDialogController {
    @FXML
    private ComboBox typeComboBox;
    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField regNumberField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField uofField;
    @FXML
    private TextField avgVelocityField;

    private Stage dialogStage;
    private BaseAutoFX auto;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        ObservableList<String> types = FXCollections.observableArrayList("car", "suv", "truck", "van");
        typeComboBox.setItems(types);
        typeComboBox.getSelectionModel().select(0);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAuto(BaseAutoFX auto) {
        this.auto = auto;

        typeComboBox.setValue(auto.getType());
        brandField.setText(auto.getBrand());
        modelField.setText(auto.getModel());
        regNumberField.setText(auto.getRegNumber());
        yearField.setText(Integer.toString(auto.getYearOfRelease()));
        priceField.setText(Integer.toString(auto.getInitialPrice()));
        uofField.setText(Double.toString(auto.getUof()));
        avgVelocityField.setText(Integer.toString(auto.getAvgVelocity()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            auto.setType((String) typeComboBox.getValue());
            auto.setBrand(brandField.getText());
            auto.setModel(modelField.getText());
            auto.setRegNumber(regNumberField.getText());
            auto.setYearOfRelease(Integer.parseInt(yearField.getText()));
            auto.setInitialPrice(Integer.parseInt(priceField.getText()));
            auto.setUof(Double.parseDouble(uofField.getText()));
            auto.setAvgVelocity(Integer.parseInt(avgVelocityField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (typeComboBox.getValue() == null) {
            errorMessage += "No valid type!\n";
        }
        if (brandField.getText() == null || brandField.getText().length() == 0) {
            errorMessage += "No valid brand!\n";
        }
        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "No valid model!\n";
        }
        if (regNumberField.getText() == null || regNumberField.getText().length() == 0) {
            errorMessage += "No valid reg number!\n";
        }
        if (yearField.getText() == null || yearField.getText().length() == 0) {
            errorMessage += "No valid year!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(yearField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid year (must be an integer)!\n";
            }
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(priceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be an integer)!\n";
            }
        }
        if (uofField.getText() == null || uofField.getText().length() == 0) {
            errorMessage += "No valid uof!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Double.parseDouble(uofField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid uof (must be an double)!\n";
            }
        }
        if (avgVelocityField.getText() == null || avgVelocityField.getText().length() == 0) {
            errorMessage += "No valid avg velocity!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(avgVelocityField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid avg velocity (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
