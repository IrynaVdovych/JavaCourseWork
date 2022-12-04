package com.example.cw2.view;

import com.example.cw2.MainApplication;
import com.example.cw2.model.BaseAutoFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TaxoparkViewController {
    @FXML
    private TableView<BaseAutoFX> autoTable;
    @FXML
    private TableColumn<BaseAutoFX, String> regNumberColumn;
    @FXML
    private TableColumn<BaseAutoFX, String> brandColumn;
    @FXML
    private TableColumn<BaseAutoFX, String> modelColumn;

    @FXML
    private TextField nameField;
    @FXML
    private Label totalCost;

    @FXML
    private Label typeLabel;
    @FXML
    private Label brandLabel;
    @FXML
    private Label modelLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label regNumberLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label uofLabel;
    @FXML
    private Label avgVelocityLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;

    private MainApplication mainApplication;

    public TaxoparkViewController() {
    }

    @FXML
    private void initialize() {
        regNumberColumn.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());

        showAutoDetails(null);
        autoTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAutoDetails(newValue));
    }

    @FXML
    private void handleSave() {
        String errorMessage = "";
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (errorMessage.length() > 0) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return;
        }

        if (this.mainApplication.getTp().getName() == null) {
            String[] parameters = new String[1];
            parameters[0] = nameField.getText();
            mainApplication.getConsoleMenuExecutor().execute("create", parameters);
        }
        else{
            mainApplication.getTp().setName(nameField.getText());
        }
    }

    @FXML
    private void handleTotalCost() {
        totalCost.setText(String.valueOf(mainApplication.getTp().totalCost()));
    }

    @FXML
    private void handleNewAuto() {
        BaseAutoFX tempAuto = new BaseAutoFX();
        boolean okClicked = mainApplication.showAutoEditDialog(tempAuto);
        if (okClicked) {
            mainApplication.getAutoData().add(tempAuto);
            mainApplication.getConsoleMenuExecutor().execute("addAuto", parametersFromAuto(tempAuto));
        }
    }

    @FXML
    protected void handleDeleteAuto() {
        BaseAutoFX selectedAuto = autoTable.getSelectionModel().getSelectedItem();
        int selectedIndex = autoTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            autoTable.getItems().remove(selectedIndex);
            String[] parameters = new String[1];
            parameters[0] = selectedAuto.getRegNumber();
            mainApplication.getConsoleMenuExecutor().execute("delAuto", parameters);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApplication.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Automobile Selected");
            alert.setContentText("Please select an automobile in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleSortByUof() {
        mainApplication.setAutoData(this.mainApplication.getTp().sortedByUoF());
        autoTable.setItems(mainApplication.getAutoData());
    }

    @FXML
    private void handleSortByRegNumber() {
        mainApplication.setAutoData(this.mainApplication.getTp().autoArrayList());
        autoTable.setItems(mainApplication.getAutoData());

    }

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        this.mainApplication.setAutoData(this.mainApplication.getTp().autoArrayList());

        nameField.setText(this.mainApplication.getTp().getName());
        totalCost.setText(String.valueOf(this.mainApplication.getTp().totalCost()));
        autoTable.setItems(this.mainApplication.getAutoData());

        saveButton.setVisible(this.mainApplication.isAdmin());
        newButton.setVisible(this.mainApplication.isAdmin());
        deleteButton.setVisible(this.mainApplication.isAdmin());
        nameField.setEditable(this.mainApplication.isAdmin());
    }

    private void showAutoDetails(BaseAutoFX auto) {
        if (auto != null) {
            typeLabel.setText(auto.getType());
            brandLabel.setText(auto.getBrand());
            modelLabel.setText(auto.getModel());
            yearLabel.setText(Integer.toString(auto.getYearOfRelease()));
            regNumberLabel.setText(auto.getRegNumber());
            priceLabel.setText(Integer.toString(auto.getInitialPrice()));
            uofLabel.setText(Double.toString(auto.getUof()));
            avgVelocityLabel.setText(Integer.toString(auto.getAvgVelocity()));
        }
        else {
            typeLabel.setText("");
            brandLabel.setText("");
            modelLabel.setText("");
            yearLabel.setText("");
            regNumberLabel.setText("");
            priceLabel.setText("");
            uofLabel.setText("");
            avgVelocityLabel.setText("");
        }
    }

    private String[] parametersFromAuto(BaseAutoFX tempAuto) {
        String[] parameters = new String[8];
        parameters[0] = tempAuto.getType();
        parameters[1] = tempAuto.getBrand();
        parameters[2] = tempAuto.getModel();
        parameters[3] = tempAuto.getRegNumber();
        parameters[4] = Integer.toString(tempAuto.getYearOfRelease());
        parameters[5] = Integer.toString(tempAuto.getInitialPrice());
        parameters[6] = Double.toString(tempAuto.getUof());
        parameters[7] = Integer.toString(tempAuto.getAvgVelocity());
        return parameters;
    }
}
