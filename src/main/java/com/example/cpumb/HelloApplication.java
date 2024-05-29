package com.example.cpumb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class HelloApplication extends Application {


    //@Override
            //public void start(Stage primaryStage) throws SQLException {
        //    GenFabric genFabric = new GenFabric();
        //    Gen CPUgen = null;
        //    while (CPUgen == null) {
            //        CPUgen = genFabric.CPUtype(promptProcessorType());
            //        if (CPUgen != null) {
                //            CPUgen.LoadMB(primaryStage);
                //        }
            //        else {showErrorAlert("Неверный процессор!", "Введите другой процессор");}
            //    }
        //}

 //   private String promptProcessorType() {
 //       TextInputDialog dialog = new TextInputDialog();
 //       dialog.setTitle("Подбор материнской платы");
 //       dialog.setHeaderText("Введите модель процессора (10 12 13):");
 //       Optional<String> result = dialog.showAndWait();
 //       Singleton Build = Singleton.getInstance();
 //       Build.CPUname = result.orElse("");
 //       return result.orElse("").replaceAll("[^0-9]", "").substring(0, 2);
 //   }
//
 //   private void showErrorAlert(String title, String message) {
 //       Alert alert = new Alert(Alert.AlertType.ERROR);
 //       alert.setTitle(title);
 //       alert.setHeaderText(null);
 //       alert.setContentText(message);
 //       alert.showAndWait();
 //   }
//
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 600);
        stage.setTitle("ConfiguratorPC!");
        stage.setScene(scene);
        stage.show();

    }
}
