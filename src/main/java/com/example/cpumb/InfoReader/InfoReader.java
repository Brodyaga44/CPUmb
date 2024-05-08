package com.example.cpumb.InfoReader;

import com.example.cpumb.SQL.Database;
import com.example.cpumb.Singleton.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    TableView<ObservableList<String>> tableView = new TableView<>();
    public void LoadInfo(Stage primaryStage, Button butt) {
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
    }
    public Button saveSelect(){
        Button SelectedButton = new Button("Выбрать запись");
        SelectedButton.setOnAction(event -> {
            ObservableList<String> selectedRecord = tableView.getSelectionModel().getSelectedItem();
            if (selectedRecord != null) {
                tableView.getItems().add(selectedRecord);
                System.out.println(selectedRecord);
                Singleton.getInstance().MBName = "" +selectedRecord;

            }
        });
        return SelectedButton;

    }
    public void showAll(Stage primaryStage, Button btn1, Button btn2,String text){
        VBox vbox = new VBox(tableView,btn1,btn2);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle(text);
        primaryStage.show();
    }
    public void showBuild(Stage primaryStage){
        Alert alert = new Alert(AlertType.INFORMATION);
        Singleton Build = Singleton.getInstance();
        alert.setTitle("Ваша сборка для " + Build.CPUname);
        alert.setHeaderText("Материнская палата: " + Build.MBName);
        alert.setContentText("Оперативная память:" + Build.RAMName +"\n" + "Данный процессор совместим с: " + Build.RAMType);

        alert.showAndWait();

    }
    public Button generateBuild(Stage primaryStage){
        Button anotherBtn = new Button("Составить сборку");
        System.out.println("123");
        anotherBtn.setOnAction(event -> {
            ObservableList<String> selectedRecord = tableView.getSelectionModel().getSelectedItem();
            if (selectedRecord != null) {
                tableView.getItems().add(selectedRecord);
                System.out.println(selectedRecord);
                Singleton.getInstance().RAMName = "" + selectedRecord;
            }
            showBuild(primaryStage);;
        });
        return anotherBtn;
    }

    public void concatRAM(Stage primaryStage, Button butt) {
        try (Connection con = db.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + "\"ddr4\"" + " UNION " + "SELECT * FROM " + "\"ddr5\"");
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
    }
}
