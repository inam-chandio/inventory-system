package com.example;

import javafx.scene.control.Alert;

public class AboutWindow {
    public static void showAboutWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Team Members");
        alert.setContentText("Team Members:\n1. Rohana\n2. Shaharyar Shoukat\n3. John Doe");
        alert.showAndWait();
    }
}
