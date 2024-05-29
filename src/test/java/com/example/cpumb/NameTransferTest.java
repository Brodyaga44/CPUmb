package com.example.cpumb;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.concurrent.TimeUnit;

@ExtendWith(ApplicationExtension.class)
public class NameTransferTest {

    private TableView<Item> tableView;
    private ObservableList<Item> data;
    private TextField textField;
    private Button button;

    public static class Item {
        private final String name;
        private final int value;

        public Item(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    @Start
    public void start(Stage stage) {
        tableView = new TableView<>();
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, Integer> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(valueColumn);

        data = FXCollections.observableArrayList(
                new Item("Item 1", 100),
                new Item("Item 2", 200)
        );

        tableView.setItems(data);

        textField = new TextField();

        button = new Button("Set Text");
        button.setOnAction(e -> {
            Item selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                textField.setText(selectedItem.getName());
            }
        });

        VBox vbox = new VBox(tableView, textField, button);
        Scene scene = new Scene(vbox, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testSetTextFromTableView() throws InterruptedException {
        Platform.runLater(() -> tableView.getSelectionModel().select(0));


        Platform.runLater(() -> button.fire());

        TimeUnit.SECONDS.sleep(3);

        assertEquals("Item 1", textField.getText());
    }
}
