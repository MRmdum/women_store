package com.example.demo1;

import com.example.demo1.sqlOperation.GeneralUtils;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class Page2 extends Application {


    public TableView tableview;

    @Override
    public void start(Stage primaryStage) {

    }
    @FXML
    protected void initialize(){

        tableview.getItems().clear();

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

    @FXML
    private void tableviewSelect(){

        var row_val = new GeneralUtils().getRowSelected2StrArray(tableview);

        var row_eval = new GeneralUtils().isInt(row_val[0]) ? Integer.parseInt(row_val[0]) : row_val[0];
        System.out.println(" /row0 "+ row_eval);

    }
    @FXML
    private void SupprimerStock(){

        var row_val = new GeneralUtils().getRowSelected2StrArray(tableview);
        new MysqlInterface().WriteData("Delete from produit where Id ="+row_val[0]+";");

        Alert a = new Alert(Alert.AlertType.INFORMATION,"Row has been deleted");
        a.show();

        initialize();
    }
    @FXML
    private void SupprimeItem() throws IOException{
        var row_val = new GeneralUtils().getRowSelected2StrArray(tableview);
        var stock = row_val[4];
        if(stock != null){
            if (Integer.parseInt(stock) > 0) {
                new MysqlInterface().WriteData("Update produit set Stock = Stock-1 where Id =" + row_val[0] + ";");
                switchToSecondePage();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Not enough stock to delete item");
                a.show();
            }
        }


    }
    @FXML
    private void AddItem() throws IOException{
        var row_val = new GeneralUtils().getRowSelected2StrArray(tableview);
        var stock = row_val[4];
        if(stock != null){
            new MysqlInterface().WriteData("Update produit set Stock = Stock+1 where Id =" + row_val[0] + ";");
            switchToSecondePage();
        }

    }

}
