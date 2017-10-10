package com.agricultural.controllers;

import com.agricultural.service.OperationService;
import com.agricultural.service.impl.OperationServiceImpl;
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
public class OperationController {

    private OperationService operationService = OperationServiceImpl.getInstance();

    @FXML
    private TextField operationField;

    /*
    * when click on add new operation Button
    * */
    public void addOperation(ActionEvent actionEvent) {

        String newOperation = operationField.getText().trim();

        if(newOperation.equals("")){
            DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
        }
        boolean isExistOperation= operationService.isExistOperation(newOperation);

        if(isExistOperation){
            DialogManager.showError("Помилка при введені даних", "Операція " + newOperation + " вже існує!" );
        }

    }
}