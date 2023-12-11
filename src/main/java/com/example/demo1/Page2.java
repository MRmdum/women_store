package com.example.demo1;

import com.example.demo1.sqlOperation.GeneralUtils;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

        TableView.TableViewSelectionModel selectionModel = tableview.getSelectionModel();
        ObservableList selectedCells = selectionModel.getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        int colIndex = tableview.getColumns().indexOf(tableview.getFocusModel().getFocusedCell().getTableColumn());
        int rowIndex = tableview.getSelectionModel().getSelectedIndex();

        Object val = tablePosition.getTableColumn().getCellData(rowIndex);
        var row =  tableview.getSelectionModel().getSelectedItems().get(0);
        var row_val = row.toString().replace("[","").replace("]","").replace(" ","").split(",");

        var row_eval = new GeneralUtils().isInt(row_val[0]) ? Integer.parseInt(row_val[0]) : row_val[0];
        System.out.println("rowIndex " + rowIndex+" /colIndex "+colIndex+
                " /val "+ val+" /row "+row+" /row0 "+ row_eval);
    }

}
