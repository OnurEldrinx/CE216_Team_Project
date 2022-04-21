module com.app.ce216_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.ce216_project to javafx.fxml;
    exports com.app.ce216_project;
}