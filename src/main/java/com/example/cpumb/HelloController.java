package com.example.cpumb;

import com.example.cpumb.PC.MB.Motherboard;
import com.example.cpumb.PC.CPU.CPU;
import com.example.cpumb.PC.RAM.RAM;
import com.example.cpumb.Singleton.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    public WebView WebViewer;
    public TableView Database;
    public Button CPUshow;
    public TextField ConfigRAM;
    public Button MBshow;
    public Button RAMshow;
    public Button addToConfig;
    public TextField ConfigCPU;
    public TextField ConfigMB;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    CPU CPU = new CPU();
    Motherboard MB = new Motherboard();
    RAM RAM = new RAM();
    Singleton Build = Singleton.getInstance();
    int State = 0;
    SearchConfig SC = new SearchConfig();
    String SearchLink = SC.getLink();

    public void onCPU(ActionEvent actionEvent) {
        State = 0;
        Database.getItems().clear();
        Database.getColumns().clear();
        CPU.LoadInfo(Database,WebViewer,SearchLink);

    }

    public void onMB(ActionEvent actionEvent) {
        State = 1;
        Database.getItems().clear();
        Database.getColumns().clear();
        MB.LoadInfo(Database,WebViewer,SearchLink);

    }

    public void onRAM(ActionEvent actionEvent) {
        State = 2;
        Database.getItems().clear();
        Database.getColumns().clear();
        RAM.LoadInfo(Database,WebViewer,SearchLink);
    }

    public void onSelect(){
        switch (State){
            case 0 :
                CPU.onClick(ConfigCPU);
                break;
            case 1 :
                MB.onClick(ConfigMB);
                break;
            case 2 :
                RAM.onClick(ConfigRAM);
                break;

        }

    }

    public void onClear(ActionEvent actionEvent) {
        Build.CPUInfo = null;
        Build.BoardInfo = null;
        Build.RAMType = null;
        Build.RAMInfo = null;

        ConfigCPU.clear();
        ConfigMB.clear();
        ConfigRAM.clear();
    }

    public void onSave(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Window theStage = source.getScene().getWindow();
        String text = "Ваш процессор: " + ConfigCPU.getText() + " "+ SearchLink + ConfigCPU.getText()  +
                      "\nВаша материнская плата: " + ConfigMB.getText() + " " + SearchLink + ConfigMB.getText()  +
                      "\nВаша оперативная память: " + ConfigRAM.getText() + " " +  SearchLink + ConfigRAM.getText();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(theStage);
        if (file != null) {
            // Сохраняем текст в файл
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(text);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}