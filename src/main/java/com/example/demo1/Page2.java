package com.example.demo1;

import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Page2 extends Application {


    public TableView tableview;

    @Override
    public void start(Stage primaryStage) {

    }
    @FXML
    protected void initialize(){

        TableView temp = new MysqlInterface().ReadData("Select * from produit");
        tableview.getColumns().addAll(temp.getColumns());
        tableview.setItems(temp.getItems());
    }
    @FXML
    private void switchToSecondePage() throws IOException {
        HelloApplication.setRoot("page2-view");
    }
    @FXML
    private void switchToVente() throws IOException {
        HelloApplication.setRoot("hello-view");
    }
    @FXML
    private void switchToMain() throws IOException {
        HelloApplication.setRoot("pagevente-view");
    }


}
