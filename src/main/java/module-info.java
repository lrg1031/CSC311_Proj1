module com.mycompany.lukegreen_assignment01_csc311 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;

    opens com.mycompany.lukegreen_assignment01_csc311 to javafx.fxml, com.google.gson;
    exports com.mycompany.lukegreen_assignment01_csc311;
}
