package com.example.cpumb;
import javafx.application.Application;
import com.example.cpumb.InfoReader.InfoReader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {

    public final String FILE_10TH_GEN = "src/main/resources/10thGen.xlsx";
    public final String FILE_12TH_GEN = "src/main/resources/12thGen.xlsx";

    @Override
    public void start(Stage primaryStage) {
        String processorType = promptProcessorType();
        String filePath = null;
        while (true) {
            switch (processorType) {
                case "10":
                    filePath = FILE_10TH_GEN;
                    break;
                case "12":
                    filePath = FILE_12TH_GEN;
                    break;
                default:
                    showErrorAlert("Неверный процессор!", "Введите другой процессор");
                    processorType = promptProcessorType();
                    continue;
            }
            break;
        }

        InfoReader IR = new InfoReader(filePath);
        IR.LoadInfo(primaryStage);
    }

    private String promptProcessorType() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Подбор материнской платы");
        dialog.setHeaderText("Введите модель процессора (пока 10 или 12):");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("").replaceAll("[^0-9]", "").substring(0, 2);
    }
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
