module de.neuefische.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;


    opens de.neuefische.frontend to javafx.fxml;
    exports de.neuefische.frontend;
}