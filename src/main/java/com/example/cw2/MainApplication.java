package com.example.cw2;

import com.example.auto.BaseAuto;
import com.example.commands.*;
import com.example.cw2.model.BaseAutoFX;
import com.example.cw2.view.*;
import com.example.db.SqLite;
import com.example.taxopark.Taxopark;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApplication extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    SqLite sqlConnector = new SqLite();
    private Taxopark tp = null;
    private ConsoleMenuExecutor consoleMenuExecutor = new ConsoleMenuExecutor();

    private String user = "";


    private ObservableList<BaseAutoFX> autoData = FXCollections.observableArrayList();

    public MainApplication() {

        this.tp = sqlConnector.readTaxopark();
        if (this.tp == null) {
            this.tp = new Taxopark();
        }
        else {
            this.sqlConnector.readAuto(this.tp);
        }

        Command create = new TaxoparkCreate(this.tp);
        Command addAuto = new TaxoparkAddAuto(this.tp);
        Command removeAuto = new TaxoparkRemoveAuto(this.tp);

        this.consoleMenuExecutor.register("create", create, "створити таксопарк.");
        this.consoleMenuExecutor.register("addAuto", addAuto, "додати автомобіль.");
        this.consoleMenuExecutor.register("delAuto", removeAuto, "вилучити автомобіль.");

    }

    public void saveAll() {
        this.sqlConnector.saveAll(this.tp);
    }

    public void setAutoData(ArrayList<BaseAuto> autoList) {
        this.autoData.clear();
        for (BaseAuto auto : autoList) {
            this.autoData.add(new BaseAutoFX(auto.getType(), auto.getBrand(), auto.getModel(), auto.getYearOfRelease(), auto.getRegNumber(), (int) auto.getInitialPrice(),  auto.getUof(), auto.getAvgVelocity()));
        }
    }

    public ObservableList<BaseAutoFX> getAutoData() {
        return autoData;
    }

    public Taxopark getTp() {
        return tp;
    }

    public ConsoleMenuExecutor getConsoleMenuExecutor() {
        return consoleMenuExecutor;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Taxopark");
        this.primaryStage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/images/taxi.jpg")));
        initRootLayout();
    }

    @Override
    public void stop() {
        saveAll();
    }

    public boolean showLogInDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("LogInDialog.fxml"));
            AnchorPane editDialog = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("LogIn");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(editDialog);
            dialogStage.setScene(scene);

            LogInDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApplication(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setMainApplication(this);

            primaryStage.show();

            boolean okClicked = showLogInDialog();
            if (!okClicked) {
                System.exit(0);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showTaxoparkView() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("TaxoparkView.fxml"));
            AnchorPane taxoparkView = (AnchorPane) loader.load();

            rootLayout.setCenter(taxoparkView);

            TaxoparkViewController taxoparkViewController = loader.getController();
            taxoparkViewController.setMainApplication(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTaxoparkSearch() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("TaxoparkSearchDialog.fxml"));
            AnchorPane taxoparkSearchDialog = (AnchorPane) loader.load();

            rootLayout.setCenter(taxoparkSearchDialog);

            TaxoparkSearchDialogController taxoparkSearchDialogController = loader.getController();
            taxoparkSearchDialogController.setMainApplication(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showAutoEditDialog(BaseAutoFX auto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("AutoEditDialog.fxml"));
            AnchorPane editDialog = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Automobile");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(editDialog);
            dialogStage.setScene(scene);

            AutoEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAuto(auto);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setUser(String user) {
        this.user = user;
    }
    public boolean isAdmin() {
        return "admin".equals(this.user);
    }
}
