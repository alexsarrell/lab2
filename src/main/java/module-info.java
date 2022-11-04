module com.example.lab3a {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab3a to javafx.fxml;
    exports com.example.lab3a;
}