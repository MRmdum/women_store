package com.example.demo1;

import com.example.demo1.sqlOperation.GeneralUtils;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModifPopUp {
    public TextField prix_field;
    public TextField desc_field;
    public TextField taille_field;
    public ComboBox type_field;
    public TextField stock_field;
    @FXML
    protected void initialize(){

        var type_produit = new String[]{"Chaussure","Vêtement","Accessoire"};
        type_field.setItems(FXCollections.observableArrayList(type_produit));
    }
    @FXML
    public void confirmer(ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        String row_id = (String) stage.getUserData();

        var type_produit = type_field.getSelectionModel().getSelectedItem();
        var prix = prix_field.getText();
        var taille = taille_field.getText();
        var stock = stock_field.getText();
        boolean allIsNum = new GeneralUtils().isInt(taille) && new GeneralUtils().isInt(stock) && new GeneralUtils().isDouble(prix);

        if (type_produit != null && prix!=null && (taille!=null || type_produit == "Accessoire") && stock !=null && allIsNum){
            if(type_produit == "Accessoire"){
                taille = "null";
            }
            new MysqlInterface().WriteData("Update produit set Categorie ='"
                    + type_produit + "',Prix="+prix+",Stock="+stock+",Taille="+taille+" where Id ="+row_id+";");

            Alert a = new Alert(Alert.AlertType.INFORMATION,"Stock has been added !");
            a.show();
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING,"Champs mal remplis");
            a.show();
        }
        stage.close();
    }

}
