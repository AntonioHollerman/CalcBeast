module app {
    requires api;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.swing;

    requires jlatexmath;

    opens application to javafx.fxml;
    opens controller to javafx.fxml;

    exports application;
}