package com.example.cpumb.CPUgen;

import com.example.cpumb.InfoReader.InfoReader;
import com.example.cpumb.Singleton.Singleton;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Gen12 implements Gen{
    private Button funcBtn;
    @Override
    public void LoadMB(Stage primaryStage) {
        InfoReader IR = new InfoReader("\"12gen\"");
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
        funcBtn.setOnAction(event -> {
            LoadMB(primaryStage);
        });
        InfoReader IR2 = new InfoReader("\"ddr5\"");

        IR2.concatRAM(primaryStage, funcBtn);
        Singleton.getInstance().RAMType = "Данный процессор совместим с ddr5 и ddr4";

        IR2.showAll(primaryStage,IR2.generateBuild(primaryStage),funcBtn,"Совместимое ОЗУ");
    }
}
