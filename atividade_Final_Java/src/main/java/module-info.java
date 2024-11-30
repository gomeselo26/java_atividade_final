module com.example.dsjavafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dsjavafinal to javafx.fxml;
    exports com.example.dsjavafinal;

}