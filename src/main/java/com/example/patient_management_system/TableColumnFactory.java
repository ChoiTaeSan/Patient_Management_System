package com.example.patient_management_system;


import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableColumnFactory {

    public static <S, T> TableColumn<S, T> createStringColumn(String title, String propertyName) {
        TableColumn<S, T> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    public static <S, T> TableColumn<S, T> createIntegerColumn(String title, String propertyName) {
        TableColumn<S, T> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }
}
