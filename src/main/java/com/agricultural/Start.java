package com.agricultural;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexey on 29.09.2017.
 */
public class Start extends Application {


    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/start_page.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Agricultural");
//        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        /*
         * For Operation window
         * */
//        primaryStage.setMinHeight(480);
//        primaryStage.setMinWidth(450);

        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
//        startAcceleration();
        Application.launch(args);
    }

    private static void startAcceleration() {
        Thread thread = new Thread(new Util());
        thread.start();
        //change!!!!!!!!!!
    }

}
