package com.example.demo1;

import com.example.demo1.Vetements.Produit;
import com.example.demo1.sqlOperation.GeneralUtils;
import com.example.demo1.sqlOperation.MysqlInterface;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void confirmer(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        String row_id = (String) stage.getUserData();

        var type_produit = type_field.getSelectionModel().getSelectedItem();
        var desc = desc_field.getText();
        var prix = prix_field.getText();
        var taille = taille_field.getText();
        var stock = stock_field.getText();
        boolean allIsNum = new GeneralUtils().isInt(taille) && new GeneralUtils().isInt(stock) && new GeneralUtils().isDouble(prix);

        if (type_produit != null && prix!=null && (taille!=null || type_produit == "Accessoire") && stock !=null && allIsNum){
            if(type_produit == "Accessoire"){
                taille = "null";
            }
            try{
                Produit produit = new GeneralUtils().checkProduit(type_produit.toString(), desc, Double.parseDouble(prix), stock, Integer.parseInt(taille));


                System.out.println(row_id);
                new MysqlInterface().WriteData("Update produit set Categorie ='"
                        + type_produit + "',Prix="+prix+",Stock="+stock+",Taille="+taille+",Descriptif='"+desc+"' where Id ="+row_id+";");

                Alert a = new Alert(Alert.AlertType.INFORMATION,"Stock has been added !");
                a.show();
                stage.close();
                switchToSecondePage();

            }catch(Exception e){
                Alert a = new Alert(Alert.AlertType.WARNING, e.toString());
                a.show();
            }
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING,"Champs mal remplis");
            a.show();
        }
    }
    private void switchToSecondePage() throws IOException {
        HelloApplication.setRoot("page2-view");
    }

}
