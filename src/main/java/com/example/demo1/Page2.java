package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class Page2 extends Application {


    @Override
    public void start(Stage primaryStage) {

    }
    @FXML
    private void switchToSecondary() throws IOException {
        HelloApplication.setRoot("hello-view");
    }


}
