package com.example.demo1;

import com.example.demo1.Vetements.Accessoire;
import com.example.demo1.Vetements.Chaussure;
import com.example.demo1.Vetements.Produit;
import com.example.demo1.Vetements.Vetement;
import com.example.demo1.sqlOperation.GeneralUtils;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class PageVente extends Application {

    public ChoiceBox choiceBox1;
    public TextField stock_achat;
    public TextField prix_achat;
    public TextField taille_achat;
    public TextField descriptif_field;
    public TableView tableView;
    public TextField vendre_field;
    public CheckBox discount;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    @FXML
    public void initialize(){

        var type_produit = new String[]{"Chaussure","Vêtement","Accessoire"};
        choiceBox1.setItems(FXCollections.observableArrayList(type_produit));

        tableView.getItems().clear();
        tableView.getColumns().clear();
        String cmd = "Select * from produit";
        TableView temp = new MysqlInterface().ReadData(cmd);
        tableView.getColumns().addAll(temp.getColumns());
        tableView.setItems(temp.getItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    @FXML
    public void vendre() throws Exception {

        var num_vend = vendre_field.getText();
        try{
            var row_val = new GeneralUtils().getRowSelected2StrArray(tableView);
            var stock = row_val[4];

            if(stock != null && !num_vend.isEmpty() && new GeneralUtils().isInt(num_vend)){
                if(Integer.parseInt(num_vend) >0){
                    if (Integer.parseInt(stock) >= Integer.parseInt(num_vend)) {

                        var type_produit = row_val[1];
                        var descriptif = row_val[5];
                        var prix = Double.parseDouble(row_val[2]);
                        var taille = Integer.parseInt(row_val[3]);

                        var remise = 0.0;

                        try {
                            if (discount.isSelected()) {
                                var produit = new GeneralUtils().checkProduit(type_produit,descriptif, prix, num_vend,taille);
                                produit.remise();
                                remise = -(produit.Prix() - prix);
                                prix = produit.Prix();
                            }
                        }catch(Exception e){
                            Alert a = new Alert(Alert.AlertType.WARNING, e.toString());
                            a.show();
                        }

                        new MysqlInterface().WriteData("Update produit set Stock = Stock-"+num_vend+" where Id =" + row_val[0] + ";");
                        new MysqlInterface().WriteData("Insert into Commande(Type_produit,reduc_appliquee,Id_client, quantite, Descriptif,prix_vendu_unite)"+
                                " values ('"+type_produit+"',"+remise+","+1+
                                ","+num_vend+",'"+ descriptif + "',"+prix+");");
                        System.out.println(remise);
                        tableView.getItems().clear();
                        tableView.getColumns().clear();
                        initialize();
                    } else {
                        Alert a = new Alert(Alert.AlertType.WARNING, "Not enough stock to sell item");
                        a.show();
                    }
                }else {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Champs mal rempli");
                    a.show();
                }
            }else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Champs mal rempli");
                a.show();
            }
        }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.WARNING,e+": No item selected");
            a.show();
        }

    }
    @FXML
    public void buyNew(){
        var type_produit = choiceBox1.getSelectionModel().getSelectedItem();
        var prix = prix_achat.getText();
        var taille = taille_achat.getText();
        var stock = stock_achat.getText();
        boolean allIsNum = new GeneralUtils().isInt(taille) && new GeneralUtils().isInt(stock) && new GeneralUtils().isDouble(prix);

        if (type_produit != null && prix!=null && (taille!=null || type_produit == "Accessoire") && stock !=null && allIsNum){
            if (Integer.parseInt(stock) > 0) {

                try {
                    var descriptif = descriptif_field.getText();

                    Produit produit = new GeneralUtils().checkProduit(type_produit.toString(), descriptif, Double.parseDouble(prix), stock, Integer.parseInt(taille));

                    String cmd3 = "Select Id from produit where Descriptif = '" + descriptif + "' and taille=" + taille + ";";
                    TableView temp3 = new MysqlInterface().ReadData(cmd3);
                    var retour_id = temp3.getItems().toArray();

                    var id_produit = retour_id.length == 0 ? "" : retour_id[0].toString().replace("[", "").replace("]", "");

                    if (!id_produit.isEmpty()) {
                        String cmd = "Update produit set Stock = Stock+" + stock + " where Id =" + id_produit + ";";
                        new MysqlInterface().WriteData(cmd);
                    } else {
                        new MysqlInterface().WriteData("Insert into produit(Descriptif,Categorie,Taille, Prix, Stock) values ('" + descriptif + "','"
                                + type_produit + "'," + taille + "," + prix + "," + stock + ");");
                    }
                    new MysqlInterface().WriteData("Insert into Commande(Type_produit,Id_client, quantite, Descriptif,prix_vendu_unite) values ('" + type_produit + "'," + 1 +
                            "," + stock + ",'" + descriptif + "'," + (-Integer.parseInt(prix)) + ");");
                } catch (Exception e) {
                    Alert a = new Alert(Alert.AlertType.WARNING, e.toString());
                    a.show();
                }
            }else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Champ mal rempli");
                a.show();
            }
        }else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Champ mal rempli");
            a.show();
        }

        initialize();

    }

    //Navigation
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
