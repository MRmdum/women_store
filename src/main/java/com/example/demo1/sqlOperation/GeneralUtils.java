package com.example.demo1.sqlOperation;

import javafx.collections.ObservableList;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;

public class GeneralUtils {

    public boolean isDouble(String strNum){

        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public boolean isInt(String strNum){

        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String[] getRowSelected2StrArray(TableView tableview){

        TableView.TableViewSelectionModel selectionModel = tableview.getSelectionModel();
        ObservableList selectedCells = selectionModel.getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);

//        int colIndex = tableview.getColumns().indexOf(tableview.getFocusModel().getFocusedCell().getTableColumn());
        int rowIndex = tableview.getSelectionModel().getSelectedIndex();

        Object val = tablePosition.getTableColumn().getCellData(rowIndex);
        var row =  tableview.getSelectionModel().getSelectedItems().get(0);
        return row.toString().replace("[","").replace("]","").replace(" ","").split(",");
    }

}
