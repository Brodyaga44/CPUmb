package com.example.cpumb.InfoReader;

import com.example.cpumb.SQL.Database;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InfoReader {
    private final String tableName;
    private final Database db = new Database();

    public InfoReader(String tableName) {
        this.tableName = tableName;
    }

    public void LoadInfo(Stage primaryStage) {
        TableView<ObservableList<String>> tableView = new TableView<>();

        try (Connection con = db.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
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

        Button removeSelectedButton = new Button("Удалить запись");
        removeSelectedButton.setOnAction(event -> {
            ObservableList<String> selectedRecord = tableView.getSelectionModel().getSelectedItem();
            if (selectedRecord != null) {
                tableView.getItems().remove(selectedRecord);
            }
        });

        VBox vbox = new VBox(tableView, removeSelectedButton);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Подходящие платы");
        primaryStage.show();
    }
}
