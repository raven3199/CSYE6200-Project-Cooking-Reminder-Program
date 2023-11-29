module org.kessoku_band.csye6200project {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.kessoku_band.csye6200project to javafx.fxml;
    exports org.kessoku_band.csye6200project;
}
