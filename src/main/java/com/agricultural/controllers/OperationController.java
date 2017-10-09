package com.agricultural.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Alexey on 09.10.2017.
 */
public class OperationController {

    @FXML
    private TextField operationField;


    public void addOperation(ActionEvent actionEvent) {

        String newOperation = operationField.getText();

    }
}
