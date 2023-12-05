package com.example.demo1.sqlOperation;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class MysqlInterface {
    String jdbcURL = "jdbc:mysql://localhost:3306/women_store?serverTimezone=Europe%2FParis";
    String user = "root";
    String pwd = "root";
    public TableView ReadData(String command) {

        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        TableView locTableView = new TableView();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    jdbcURL, user, pwd);
//here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(command);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Dynamically create columns based on column names
            for (int i = 1; i <= columnCount; i++) {
                final int columnIndex = i;
                TableColumn<ObservableList<String>, String> col = new TableColumn<>(metaData.getColumnName(i));
                col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(columnIndex - 1)));
                locTableView.getColumns().add(col);
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            locTableView.setItems(data);

        }catch(Exception e){ System.out.println(e);}

        return locTableView;
    }
    public void WriteData(String command){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    jdbcURL, user, pwd);
//here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(command);
        }catch(Exception e){ System.out.println(e);}
    }
}
