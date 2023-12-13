package com.example.demo1;

import com.example.demo1.sqlOperation.GeneralUtils;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PageVente extends Application {

    public ChoiceBox choiceBox1;
    public TextField stock_achat;
    public TextField prix_achat;
    public TextField taille_achat;
    public TextField descriptif_field;
    public TableView tableView;

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
        String cmd = "Select * from produit";
        TableView temp = new MysqlInterface().ReadData(cmd);
        tableView.getColumns().addAll(temp.getColumns());
        tableView.setItems(temp.getItems());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    @FXML
    public void buyNew(){
        var type_produit = choiceBox1.getSelectionModel().getSelectedItem();
        var prix = prix_achat.getText();
        var taille = taille_achat.getText();
        var stock = stock_achat.getText();
        boolean allIsNum = new GeneralUtils().isInt(taille) && new GeneralUtils().isInt(stock) && new GeneralUtils().isDouble(prix);

        if (type_produit != null && prix!=null && (taille!=null || type_produit == "Accessoire") && stock !=null && allIsNum){

            var descriptif = descriptif_field.getText();
            String cmd3 = "Select Id from produit where Descriptif = '"+descriptif+"' and taille="+taille+";";
            TableView temp3 = new MysqlInterface().ReadData(cmd3);
            var retour_id = temp3.getItems().toArray();

            var id_produit = retour_id.length == 0? "" : retour_id[0].toString().replace("[","").replace("]","");

            if(!id_produit.isEmpty()){
                String cmd = "Update produit set Stock = Stock+"+stock+" where Id ="+id_produit+";";
                new MysqlInterface().WriteData(cmd);
            }
            else {
                new MysqlInterface().WriteData("Insert into produit(Descriptif,Categorie,Taille, Prix, Stock) values ('"+descriptif+"','"
                        + type_produit + "',"+taille+","+prix+","+stock+");");
            }
            new MysqlInterface().WriteData("Insert into Comande(Type_produit,Id_client, quantite, Descriptif,prix_vendu_unite) values ('"+type_produit+"',"+1+
                    ","+stock+",'"+ descriptif + "',"+(-Integer.parseInt(prix))+");");
        }



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
