package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class PageVente extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @FXML
    private void switchToSecondePage() throws IOException {
        HelloApplication.setRoot("page2-view");
    }
    @FXML
    private void switchToMain() throws IOException {
        HelloApplication.setRoot("hello-view");
    }
    @FXML
    private void switchToVente() throws IOException {
        HelloApplication.setRoot("pagevente-view");
    }
}
