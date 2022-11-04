package com.example.lab3a;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ResourceBundle;

public class MainApplication extends Application {
    private final Double[] xArray = {-0.9,-0.8,-0.7, -0.6, -0.5, -0.4, -0.3, -0.2, -0.1, 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
    private final Double[] epsArray = {0.01, 0.001, 0.0001, 0.00001, 0.000001, 0.0000001, 0.00000001, 0.0000000001};
    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("img/icon.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainapp-resource.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("style.css").toExternalForm());
        stage.setScene(scene);

        ComboBox<String> epsValues = (ComboBox<String>)fxmlLoader.getNamespace().get("epsValues");

        for(int i = 0; i < Math.max(xArray.length, epsArray.length); i++) {
            if(i < epsArray.length - 1) epsValues.getItems().add(epsArray[i].toString());
        }
        epsValues.getSelectionModel().selectFirst();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}