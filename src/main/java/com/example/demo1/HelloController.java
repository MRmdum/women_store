package com.example.demo1;

import com.example.demo1.Vetements.Vetement;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    public TableView client_table;
    public TableView commande_table;
    public Label somme_label;
    @FXML
    private Label welcomeText;
    @FXML
    protected void initialize(){

        client_table.getItems().clear();
        commande_table.getItems().clear();

        String cmd1 = "Select * from commande";


        TableView temp = new MysqlInterface().ReadData(cmd1);
        commande_table.getColumns().addAll(temp.getColumns());
        commande_table.setItems(temp.getItems());
        commande_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        String cmd2 = "Select * from commande";

        client_table.getColumns().addAll(temp.getColumns());
        client_table.setItems(temp.getItems());
        client_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        String cmd3 = "Select sum(col1) from (Select sum(prix_vendu_unite)*quantite as col1 from commande where prix_vendu_unite>0 group by Id_commande) as retour;";
        TableView temp3 = new MysqlInterface().ReadData(cmd3);
        var a = temp3.getItems().toArray()[0].toString().replace("[","").replace("]","");

        somme_label.setText(a);


    }
    @FXML
    private void switchToSecondePage() throws IOException {
        HelloApplication.setRoot("page2-view");
    }
    @FXML
    private void switchToVente() throws IOException {
        HelloApplication.setRoot("pagevente-view");
    }
    @FXML
    private void switchToMain() throws IOException {
        HelloApplication.setRoot("hello-view");
    }

}