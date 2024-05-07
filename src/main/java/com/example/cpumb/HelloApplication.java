package com.example.cpumb;

import com.example.cpumb.CPUgen.Gen;
import com.example.cpumb.CPUgen.GenFabric;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {
        GenFabric genFabric = new GenFabric();
        Gen CPUgen = null;
        while (CPUgen == null) {
            CPUgen = genFabric.CPUtype(promptProcessorType());
            if (CPUgen != null) {CPUgen.LoadMB(primaryStage);}
            else {showErrorAlert("Неверный процессор!", "Введите другой процессор");}
        }
    }

    private String promptProcessorType() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Подбор материнской платы");
        dialog.setHeaderText("Введите модель процессора (пока 10 или 12):");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("").replaceAll("[^0-9]", "").substring(0, 2);
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
