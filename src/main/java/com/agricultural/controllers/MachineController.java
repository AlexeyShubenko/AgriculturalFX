package com.agricultural.controllers;

import com.agricultural.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Alexey on 09.10.2017.
 */
/*
* handle all actions for operation window
* */
public class MachineController {

    @FXML
    private TextField machineField;

    /*
    * when click on add new operation Button
    * */
    public void addMachine(ActionEvent actionEvent) {

        String operation = machineField.getText();

        if(operation.equals("")){
            DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
        }

    }
}