package com.agricultural.utils;

import javafx.scene.control.Alert;

/**
 * Created by Alexey on 09.10.2017.
 */
public class DialogManager {

    /*
    * for error messages
    * */
    public static void showError(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }


}
