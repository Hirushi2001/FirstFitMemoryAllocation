module com.example.mpfirstfitallocation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.mpfirstfitallocation to javafx.fxml;
    exports com.example.mpfirstfitallocation;
}