package com.agricultural.controllers;

import com.agricultural.service.MachineService;
import com.agricultural.service.impl.MachineServiceImpl;
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

    private MachineService machineService = MachineServiceImpl.getInstance();
    @FXML
    private TextField machineField;

    /*
    * when click on add new operation Button
    * */
    public void addMachine(ActionEvent actionEvent) {

        

    }
}