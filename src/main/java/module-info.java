module com.example.cpumb {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi;
    requires java.sql;
    requires javafx.web;


    opens com.example.cpumb to javafx.fxml;
    exports com.example.cpumb;
}