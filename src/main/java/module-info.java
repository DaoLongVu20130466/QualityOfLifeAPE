module com.example.ape {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;

    opens com.example.ape to javafx.fxml;
    exports com.example.ape;
}