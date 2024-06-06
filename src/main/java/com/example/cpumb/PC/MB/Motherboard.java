package com.example.cpumb.PC.MB;

import com.example.cpumb.Database;
import com.example.cpumb.Singleton.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.sql.*;

public class Motherboard {
    private final Database db = new Database();
    Singleton Build = Singleton.getInstance();
    ResultSet rs;
    public void LoadInfo(TableView tableView, WebView wb) {
        try (Connection con = db.getConnection()) {
            Statement stmt = con.createStatement();
            if (Build.CPUInfo == null) {
                 rs = stmt.executeQuery("SELECT * FROM " + "\"Motherboard\"");
            }
            else {
                 rs = stmt.executeQuery("SELECT * FROM " + "\"Motherboard\" WHERE  \"Socket\" = " + "'"+ Build.CPUInfo.Socket + "'");
            }

            int columnCount = rs.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rs.getMetaData().getColumnName(i));
                column.setCellValueFactory(param -> {
                    ObservableList<String> row = param.getValue();
                    return javafx.beans.binding.Bindings.createObjectBinding(() -> row.get(j));
                });

                tableView.getColumns().add(column);
            }

            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    String[] MBdata = newSelection.toString().substring(1, newSelection.toString().length() - 1).split("\\s*,\\s*");

                    Build.BoardInfo = new MBinfo(MBdata[1],MBdata[MBdata.length-2],MBdata[MBdata.length-1]);
                    WebEngine we = wb.getEngine();
                    we.load("https://www.dns-shop.ru/search/?q=" + Build.BoardInfo.Name );

                }
            });
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void onClick(TextField ConfigMB){
        ConfigMB.setText(Build.BoardInfo.Name);
    }
}
