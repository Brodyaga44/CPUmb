package com.example.cpumb.InfoReader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoReader {

    private String filePath;

    public InfoReader(String filePath) {
        this.filePath = filePath;
    }


    public void LoadInfo(Stage primaryStage) {
        TableView<String[]> tableView = new TableView<>();

        TableColumn<String[], String> column1 = new TableColumn<>("Motherboard Name");
        TableColumn<String[], String> column2 = new TableColumn<>("Form Factor");
        TableColumn<String[], String> column3 = new TableColumn<>("Chipset Socket");
        TableColumn<String[], String> column4 = new TableColumn<>("Socket");
        TableColumn<String[], String> column5 = new TableColumn<>("Max TDP");
        tableView.getColumns().addAll(column1, column2, column3, column4, column5);

        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);

                List<String[]> data = new ArrayList<>();

                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    String[] rowData = new String[5];
                    for (int j = 0; j < 5; j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        DataFormatter formatter = new DataFormatter();
                        rowData[j] = formatter.formatCellValue(cell);
                    }

                    data.add(rowData);
                }

                tableView.getItems().addAll(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        column1.setCellValueFactory(param -> javafx.beans.binding.Bindings.createObjectBinding(() -> param.getValue()[0]));
        column2.setCellValueFactory(param -> javafx.beans.binding.Bindings.createObjectBinding(() -> param.getValue()[1]));
        column3.setCellValueFactory(param -> javafx.beans.binding.Bindings.createObjectBinding(() -> param.getValue()[2]));
        column4.setCellValueFactory(param -> javafx.beans.binding.Bindings.createObjectBinding(() -> param.getValue()[3]));
        column5.setCellValueFactory(param -> javafx.beans.binding.Bindings.createObjectBinding(() -> param.getValue()[4]));

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Подходящие платы");
        primaryStage.show();
    }

}
