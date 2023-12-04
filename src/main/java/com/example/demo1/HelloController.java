package com.example.demo1;

import com.example.demo1.Vetements.Vetement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void testsql(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/women_store?serverTimezone=Europe%2FParis","root","root");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from produit");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    @FXML
    protected void CallVetement(){
        Vetement a = new Vetement("test",10.3,2,52);
        System.out.println(a);
    }

    @FXML
    private void switchToSecondePage() throws IOException {
        HelloApplication.setRoot("page2-view");
    }

}