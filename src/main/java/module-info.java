module com.example.control {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.control to javafx.fxml;
    exports com.example.control;
}