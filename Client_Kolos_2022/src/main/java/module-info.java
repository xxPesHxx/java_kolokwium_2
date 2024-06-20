module com.example.client_kolos_2022 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.client_kolos_2022 to javafx.fxml;
    exports com.example.client_kolos_2022;
}