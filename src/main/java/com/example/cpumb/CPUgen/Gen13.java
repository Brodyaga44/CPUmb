package com.example.cpumb.CPUgen;

import com.example.cpumb.InfoReader.InfoReader;
import com.example.cpumb.Singleton.Singleton;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Gen13 implements Gen{

    private Button funcBtn;

    @Override
    public void LoadMB(Stage primaryStage) {
        InfoReader IR = new InfoReader("\"13gen\"");

        funcBtn = new Button("Подобрать ОЗУ");
        IR.LoadInfo(primaryStage, funcBtn);
        funcBtn.setOnAction(event -> {
            RAMselect(primaryStage);
        });
        IR.showAll(primaryStage,IR.saveSelect(),funcBtn,"Подходящие материнки");


    }
    @Override
    public void RAMselect(Stage primaryStage) {
        funcBtn = new Button("Вернуться назад");
        InfoReader IR2 = new InfoReader("\"ddr5\"");
        Singleton.getInstance().RAMType = "Данный процессор совместим только с ddr5";
        IR2.LoadInfo(primaryStage, funcBtn);
        funcBtn.setOnAction(event -> {
            LoadMB(primaryStage);
        });
        IR2.showAll(primaryStage,IR2.generateBuild(primaryStage),funcBtn,"Совместимое ОЗУ");

    }
}
