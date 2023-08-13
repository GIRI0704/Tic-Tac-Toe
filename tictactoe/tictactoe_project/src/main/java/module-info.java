module com.example.tictactoe_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe_project to javafx.fxml;
    exports com.example.tictactoe_project;
}