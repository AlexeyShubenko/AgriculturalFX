package com.agricultural.controllers.machines;

import com.agricultural.domains.dto.MachineDto;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.exceptions.InternalDBException;
import com.agricultural.service.MachineService;
import com.agricultural.service.impl.MachineServiceImpl;
import com.agricultural.utils.DialogManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Alexey on 15.10.2017.
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
    private TableColumn<MachineDto, Integer> columnSerialNumberMach;
    @FXML
    private TableColumn<MachineDto, String> columnMachineNameMach;
    @FXML
    private Label machineCountLabel;

    private MachineDialogController machineDialogController;

    private Stage dialogMachineWindow;
    private Parent fxmlParent;
    private FXMLLoader fxmlLoader = new FXMLLoader();


    @FXML
    private void initialize(){

        machines = FXCollections.observableArrayList(machineService.getMachines());
        // fill the table with data from database
        columnSerialNumberMach.setCellValueFactory(new PropertyValueFactory<MachineDto, Integer>("serialNumber"));
        columnMachineNameMach.setCellValueFactory(new PropertyValueFactory<MachineDto, String>("machineName"));
        countUpdate();

        machines.addListener((ListChangeListener<MachineDto>) c -> {
            countUpdate();
        });

        machinesTableView.setItems(machines);

        try {
            fxmlLoader.setLocation(getClass().getResource("/views/machines/addMachineDialog.fxml"));
            fxmlParent = fxmlLoader.load();
            machineDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        machines.sort((o1, o2) -> o1.getSerialNumber()-o2.getSerialNumber());
    }

    private void countUpdate() {
        machineCountLabel.setText("Кількість записів в таблиці: " + machines.size());
    }

    private void createDialogWindow(ActionEvent actionEvent, String title) {

        if (dialogMachineWindow == null) {
            dialogMachineWindow = new Stage();
            dialogMachineWindow.setTitle(title);
            dialogMachineWindow.setResizable(false);
            dialogMachineWindow.setScene(new Scene(fxmlParent));
            dialogMachineWindow.initModality(Modality.WINDOW_MODAL);
            dialogMachineWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        }
        machineDialogController.setParentScene(((Node) actionEvent.getSource()).getScene());
        dialogMachineWindow.showAndWait();
    }


    /*
    * when click on add new operation Button
    * */
//    public void addMachine(ActionEvent actionEvent) {
//
////        String machine = machineField.getText().trim();
//
//        if(machine.equals("")){
//            DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
//        }
//        boolean isExistMachine = machineService.isExistMachine(machine);
//        if(isExistMachine){
//            DialogManager.showError("Помилка при введені даних", "Машино тракторний аргегат " + machine + " вже існує!" );
//        }
//
//    }
}