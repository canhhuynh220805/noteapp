module com.htc.noteapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.htc.noteapp to javafx.fxml;
    exports com.htc.noteapp;
    exports com.htc.pojo;
}
