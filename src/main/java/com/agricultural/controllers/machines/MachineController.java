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

    /*
    * when click on add new machine Button
    * */
    public void openAddMachineWindow(ActionEvent actionEvent) {
        //null - because we want to create new operation
        machineDialogController.setMachineDto(null);
        createDialogWindow(actionEvent, ADD_MACHINES);

        MachineDto machineDto = machineDialogController.getMachineDto();
        // силка нульова якщо зміни не вдалося виконати
        if(machineDto.getMachineName().equals("")){
            return;
        }
//        if(Objects.isNull(machineDto)){
//            return;
//        }
        machineDto.setSerialNumber(machines.size()+1);
        machines.add(machineDto);
    }

    /*
    * called when want to edit exist operation
    * */
    public void editMachineOpenWindow(ActionEvent actionEvent) {
        //take exist operation from table for further updating
        MachineDto machineDtoFromTable =
                (MachineDto) machinesTableView.getSelectionModel().getSelectedItem();
        if (Objects.isNull(machineDtoFromTable)) {
            DialogManager.showInfo("Wrong action!", "Для редагування треба вибрати поле в таблиці!");
            return;
        }
       machineDialogController.setMachineDto(machineDtoFromTable);
        createDialogWindow(actionEvent, EDIT_MACHINES);
        MachineDto machineDto = machineDialogController.getMachineDto();
        // силка нульова якщо зміни не вдалося виконати
        if(Objects.isNull(machineDto)){
            return;
        }
        //якщо користувач ввів однакові дані
        //перевіряється якщо силкі не вказують на один
        //і той же об'єкт то виконуємо зміни в observableList
        //для оновлення таблиці треба змінити дані в таблиці
        machines.set((machineDto.getSerialNumber()-1),machineDto);

    }

    /*
    * called when want to delete exist operation
    * */
    public void deleteMachine(ActionEvent actionEvent) {
        MachineDto machineDto =
                (MachineDto) machinesTableView.getSelectionModel().getSelectedItem();
        if (Objects.isNull(machineDto)) {
            DialogManager.showInfo("Wrong action!", "Для видалення треба вибрати поле в таблиці");
            return;
        }
        try {
            machineService.deleteMachine(machineDto);
        }catch (InternalDBException internalException){
            DialogManager.showError("Помилка!", internalException.getMessage());
            return;
        }

        int num = machineDto.getSerialNumber();
        machines.remove(machineDto);
        for (int i = num; i < machines.size()-1; i++) {
            machines.get(i).setSerialNumber(i);
        }


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