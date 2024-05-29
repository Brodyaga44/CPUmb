package com.example.cpumb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class HelloControllerTest {

    private TableView<Item> tableView;
    private ObservableList<Item> data;
    private Item selectedItem;
    private Button saveButton;

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

        saveButton = new Button("Save Selected");
        saveButton.setOnAction(e -> {
            selectedItem = tableView.getSelectionModel().getSelectedItem();
            System.out.println("Сохранилось: " + selectedItem.getName() + ", " + selectedItem.getValue());
        });

        VBox vbox = new VBox(tableView, saveButton);
        Scene scene = new Scene(vbox, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testSaveSelectedItem() throws InterruptedException {
        tableView.getSelectionModel().select(0);
        System.out.println("Выбрано: " + tableView.getSelectionModel().getSelectedItem().getName() + ", " + tableView.getSelectionModel().getSelectedItem().getValue());

        saveButton.fire();

        Thread.sleep(3000); // Задержка в 3 секунды

        assertEquals("Item 1", selectedItem.getName());
        assertEquals(100, selectedItem.getValue());
    }
}
