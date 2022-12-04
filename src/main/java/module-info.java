module com.example.cw2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.cw2 to javafx.fxml;
    exports com.example.cw2;
    opens com.example.cw2.view to javafx.fxml;
    exports com.example.cw2.view;
    opens com.example.cw2.model to javafx.fxml;
    exports com.example.cw2.model;
}