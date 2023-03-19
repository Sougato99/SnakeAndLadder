module com.example.snakeandladdermarch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeandladdermarch to javafx.fxml;
    exports com.example.snakeandladdermarch;
}