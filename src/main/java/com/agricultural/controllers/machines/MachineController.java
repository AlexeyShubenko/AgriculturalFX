package com.agricultural.controllers.machines;

import com.agricultural.controllers.operations.OperationDialogController;
import com.agricultural.domains.dto.MachineDto;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.service.MachineService;
import com.agricultural.service.impl.MachineServiceImpl;
import com.agricultural.utils.DialogManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Alexey on 09.10.2017.
 */
/*
* handle all actions for operation window
* */
public class MachineController {

    private MachineService machineService = MachineServiceImpl.getInstance();

    private ObservableList<MachineDto> machines;

    private final String ADD_MACHINES = "Машино тракторний агрегат";
    private final String EDIT_MACHINES = "Редагувати машино тракторний агрегат";

    @FXML
    private TableView machinesTableView;
    @FXML
    private TableColumn<TechnologicalOperationDto, Integer> columnSerialNumberMach;
    @FXML
    private TableColumn<TechnologicalOperationDto, String> columnOperationNameMach;
    @FXML
    private Label machineCountLabel;

    private OperationDialogController operationDialogController;

    private Stage dialogOperationWindow;
    private Parent fxmlParent;
    private FXMLLoader fxmlLoader = new FXMLLoader();




    /*
    * when click on add new operation Button
    * */
    public void addMachine(ActionEvent actionEvent) {

//        String machine = machineField.getText().trim();

//        if(machine.equals("")){
//            DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
//        }
//        boolean isExistMachine = machineService.isExistMachine(machine);
//        if(isExistMachine){
//            DialogManager.showError("Помилка при введені даних", "Машино тракторний аргегат " + machine + " вже існує!" );
//        }

    }
}