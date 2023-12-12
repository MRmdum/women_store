package com.example.demo1;

import com.example.demo1.sqlOperation.GeneralUtils;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Page2 extends Application {


    public TableView tableview;
    public ChoiceBox choiceBox1;
    public TextField stock_field;
    public TextField prix_field;
    public TextField taille_field;

    @Override
    public void start(Stage primaryStage) {

    }
    @FXML
    protected void initialize(){

        var type_produit = new String[]{"Chaussure","Vêtement","Accesoire"};
        choiceBox1.setItems(FXCollections.observableArrayList(type_produit));

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
    private void AddStock() throws IOException{

        var type_produit = choiceBox1.getSelectionModel().getSelectedItem();
        var prix = prix_field.getText();
        var taille = taille_field.getText();
        var stock = stock_field.getText();
        boolean allIsNum = new GeneralUtils().isInt(taille) && new GeneralUtils().isInt(stock) && new GeneralUtils().isDouble(prix);

        if (type_produit != null && prix!=null && (taille!=null || type_produit == "Accessoir") && stock !=null && allIsNum){
            if(type_produit == "Accessoir"){
                taille = "null";
            }
            new MysqlInterface().WriteData("Insert into produit(Categorie,Taille, Prix, Stock) values ('"
                    + type_produit + "',"+taille+","+prix+","+stock+");");

            Alert a = new Alert(Alert.AlertType.INFORMATION,"Stock has been added !");
            a.show();

            switchToSecondePage();
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING,"Champs mal remplis");
            a.show();
        }
    }
    @FXML
    private void SupprimerStock(){

        var row_val = new GeneralUtils().getRowSelected2StrArray(tableview);
        if(row_val != null) {
            new MysqlInterface().WriteData("Delete from produit where Id =" + row_val[0] + ";");

            Alert a = new Alert(Alert.AlertType.INFORMATION,"Row has been deleted");
            a.show();

            initialize();
        }
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
