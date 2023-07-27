module de.neuefische.frontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.neuefische.frontend to javafx.fxml;
    exports de.neuefische.frontend;
}