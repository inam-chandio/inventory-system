package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private CustomerManager customerManager = new CustomerManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        // Table to display customers
        TableView<Customer> customerTable = createCustomerTable();
        root.setCenter(customerTable);

        // Scene setup
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Customer Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem addMenuItem = new MenuItem("Add Customer");
        MenuItem removeMenuItem = new MenuItem("Remove Customer");
        MenuItem updateMenuItem = new MenuItem("Update Customer");
        MenuItem aboutMenuItem = new MenuItem("About");

        addMenuItem.setOnAction(e -> openAddCustomerWindow());
        removeMenuItem.setOnAction(e -> openRemoveCustomerWindow());
        updateMenuItem.setOnAction(e -> openUpdateCustomerWindow());
        aboutMenuItem.setOnAction(e -> openAboutWindow());

        fileMenu.getItems().addAll(addMenuItem, removeMenuItem, updateMenuItem, aboutMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private TableView<Customer> createCustomerTable() {
        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));

        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(emailColumn);

        return table;
    }

    private void openAddCustomerWindow() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Add Customer");
        inputDialog.setHeaderText("Enter Customer Information");
        inputDialog.setContentText("Name:");

        inputDialog.showAndWait().ifPresent(name -> {
            String email = "example@example.com"; // Default email for simplicity
            Customer newCustomer = new Customer(customerManager.getCustomers().size() + 1, name, email);
            customerManager.addCustomer(newCustomer);
            LoggingService.logAction("Added new customer: " + newCustomer);
        });
    }

    private void openRemoveCustomerWindow() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Remove Customer");
        inputDialog.setHeaderText("Enter Customer ID to Remove");
        inputDialog.setContentText("Customer ID:");

        inputDialog.showAndWait().ifPresent(id -> {
            customerManager.removeCustomer(Integer.parseInt(id));
            LoggingService.logAction("Removed customer with ID: " + id);
        });
    }

    private void openUpdateCustomerWindow() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Update Customer");
        inputDialog.setHeaderText("Enter Customer ID to Update");
        inputDialog.setContentText("Customer ID:");

        inputDialog.showAndWait().ifPresent(id -> {
            String name = "Updated Name"; // Dummy update for simplicity
            String email = "updated@example.com"; // Dummy update for simplicity
            customerManager.updateCustomer(Integer.parseInt(id), name, email);
            LoggingService.logAction("Updated customer with ID: " + id);
        });
    }

    private void openAboutWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Project Team");
        alert.setContentText("Team Members:\n1. Rohana\n2. John Doe\n3. Jane Doe");
        alert.showAndWait();
    }
}
