module com.example.cpumb {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi;


    opens com.example.cpumb to javafx.fxml;
    exports com.example.cpumb;
}