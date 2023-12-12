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
import java.util.Arrays;

public class Page2 extends Application {

    //Data's view
    public TableView tableview;
    private static String cmd = "Select * from produit;";

    //Adding stock controls
    public ChoiceBox choiceBox1;
    public TextField stock_field;
    public TextField prix_field;
    public TextField taille_field;

    //filter controls
    public TextField filtre_taille;
    public ComboBox comboBox1;

    @Override
    public void start(Stage primaryStage) {

    }
    @FXML
    protected void initialize(){

        System.out.println(cmd);

        var type_produit = new String[]{"Chaussure","Vêtement","Accessoire"};
        choiceBox1.setItems(FXCollections.observableArrayList(type_produit));

        type_produit = new String[]{"Chaussure","Vêtement","Accessoire",""};
        comboBox1.setItems(FXCollections.observableArrayList(type_produit));

        tableview.getItems().clear();

        TableView temp = new MysqlInterface().ReadData(cmd);
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
            if(type_produit == "Accessoire"){
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
    private void SupprimerStock() throws  Exception{

        var row_val = new GeneralUtils().getRowSelected2StrArray(tableview);
        if(row_val != null) {
            new MysqlInterface().WriteData("Delete from produit where Id =" + row_val[0] + ";");

            Alert a = new Alert(Alert.AlertType.INFORMATION,"Row has been deleted");
            a.show();

            initialize();
        }
    }
    @FXML
    private void SupprimeItem() throws Exception{
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
    private void AddItem() throws Exception{
        var row_val = new GeneralUtils().getRowSelected2StrArray(tableview);

        var stock = row_val[4];

        if(stock != null){
            new MysqlInterface().WriteData("Update produit set Stock = Stock+1 where Id =" + row_val[0] + ";");
            switchToSecondePage();
        }
    }
    @FXML
    private  void filterView(){
        var type_produit = comboBox1.getSelectionModel().getSelectedItem();
        var taille_filtre = filtre_taille.getText();

        tableview.getItems().clear();
        tableview.getColumns().clear();

        cmd = !taille_filtre.isEmpty() ? "Select * from produit where Categorie like '%"+type_produit+"%' and taille="+taille_filtre+";"
                                            : "Select * from produit where Categorie like '%"+type_produit+"%';";

        TableView temp = new MysqlInterface().ReadData(cmd);
        tableview.getColumns().addAll(temp.getColumns());
        tableview.setItems(temp.getItems());
        System.out.println(cmd);
    }

}
