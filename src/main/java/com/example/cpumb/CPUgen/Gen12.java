package com.example.cpumb.CPUgen;

import com.example.cpumb.InfoReader.InfoReader;
import com.example.cpumb.SQL.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Gen12 implements Gen{
    @Override
    public void LoadMB(Stage primaryStage) {
        InfoReader IR = new InfoReader("\"12gen\"");
        IR.LoadInfo(primaryStage);
    }

    @Override
    public void RAMselect() {

    }
}
