package com.agricultural;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Alexey on 29.09.2017.
 */
public class Start extends Application {

    public void start(Stage primaryStage) throws Exception {
//        Parent parent = FXMLLoader.load(getClass().getResource("/views/start_page.fxml"));
//        Parent parent = FXMLLoader.load(getClass().getResource("/views/operations.fxml"));
        Parent parent = FXMLLoader.load(getClass().getResource("/views/machines.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Agricultural");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}