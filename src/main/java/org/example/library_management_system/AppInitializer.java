package org.example.library_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent load =FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene= new Scene(load);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load the form").show();
            e.printStackTrace();
        }
    }
}
