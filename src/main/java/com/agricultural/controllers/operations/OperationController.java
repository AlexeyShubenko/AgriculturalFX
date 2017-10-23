package com.agricultural.controllers.operations;

import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.exceptions.InternalDBException;
import com.agricultural.service.OperationService;
import com.agricultural.service.impl.OperationServiceImpl;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by Alexey on 09.10.2017.
 */
/*
* handle all actions for operation window
* */
public class OperationController {

    private OperationService operationService = OperationServiceImpl.getInstance();
    private ObservableList<TechnologicalOperationDto> operations;

    private final String ADD_OPERATIONS = "Технологічна операція";
    private final String EDIT_OPERATIONS = "Редагувати технологічну операцію";

    @FXML
    private TableView operationsTableView;
    @FXML
    private TableColumn<TechnologicalOperationDto, Integer> columnSerialNumber;
    @FXML
    private TableColumn<TechnologicalOperationDto, String> columnOperationName;
    @FXML
    private Label operationCountLabel;

    private OperationDialogController operationDialogController;

    private Stage dialogOperationWindow;
    private Parent fxmlParent;
    private FXMLLoader fxmlLoader = new FXMLLoader();


    @FXML
    private void initialize() {
        operations = FXCollections.observableArrayList(operationService.getOperations());
        // fill the table with data from database
        columnSerialNumber.setCellValueFactory(new PropertyValueFactory<TechnologicalOperationDto, Integer>("serialNumber"));
        columnOperationName.setCellValueFactory(new PropertyValueFactory<TechnologicalOperationDto, String>("operationName"));
        countUpdate();

        operations.addListener((ListChangeListener<TechnologicalOperationDto>) c -> {
            countUpdate();
        });

        operationsTableView.setItems(operations);

        try {
            fxmlLoader.setLocation(getClass().getResource("/views/operations/addOperationDialog.fxml"));
            fxmlParent = fxmlLoader.load();
            operationDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        operations.sort(Comparator.comparingInt(TechnologicalOperationDto::getSerialNumber));

    }

    private void countUpdate() {
        operationCountLabel.setText("Кількість записів в таблиці: " + operations.size());
    }
    /*
    * when click on add new operation Button
    * */
    public void openAddOperationWindow(ActionEvent actionEvent) {
        //null - because we want to create new operation
        operationDialogController.setOperationDto(null);
        createDialogWindow(actionEvent, ADD_OPERATIONS);

        TechnologicalOperationDto operationDto = operationDialogController.getOperationDto();
        // силка нульова якщо зміни не вдалося виконати
        if(Objects.isNull(operationDto)){
            return;
        }
        operationDto.setSerialNumber(operations.size()+1);
        operations.add(operationDto);
    }

    /*
    * called when want to edit exist operation
    * */
    public void editOperationOpenWindow(ActionEvent actionEvent) {
        //take exist operation from table for further updating
        TechnologicalOperationDto operationDtoFromTable =
                (TechnologicalOperationDto) operationsTableView.getSelectionModel().getSelectedItem();
        if (Objects.isNull(operationDtoFromTable)) {
            DialogManager.showInfo("Wrong action!", "Для редагування треба вибрати поле в таблиці!");
            return;
        }
        operationDialogController.setOperationDto(operationDtoFromTable);
        createDialogWindow(actionEvent, EDIT_OPERATIONS);
        TechnologicalOperationDto operationDtoToEdit = operationDialogController.getOperationDto();
        // силка нульова якщо зміни не вдалося виконати
        if(Objects.isNull(operationDtoToEdit)){
            return;
        }
        //якщо користувач ввів однакові дані
        //перевіряється якщо силкі не вказують на один
        //і той же об'єкт то виконуємо зміни в observableList
//        if(!operationDtoFromTable.getOperationName().equals(operationDtoToEdit.getOperationName())){
            //для оновлення таблиці треба змінити дані в таблиці
            operations.set(operationDtoToEdit.getSerialNumber()-1,operationDtoToEdit);
//        }

    }

    /*
    * called when want to delete exist operation
    * */
    public void deleteOperation(ActionEvent actionEvent) {
        TechnologicalOperationDto operationDto =
                (TechnologicalOperationDto) operationsTableView.getSelectionModel().getSelectedItem();
        if (Objects.isNull(operationDto)) {
            DialogManager.showInfo("Wrong action!", "Для видалення треба вибрати поле в таблиці");
            return;
        }
        try {
            operationService.deleteOperation(operationDto);
        }catch (InternalDBException internalException){
            DialogManager.showError("Помилка!", internalException.getMessage());
            return;
        }

        int num = operationDto.getSerialNumber();
        operations.remove(operationDto);
        for (int i = num; i < operations.size()-1; i++) {
            operations.get(i).setSerialNumber(i);
        }


    }


    private void createDialogWindow(ActionEvent actionEvent, String title) {

        if (dialogOperationWindow == null) {

            dialogOperationWindow = new Stage();

            dialogOperationWindow.setTitle(title);
            dialogOperationWindow.setResizable(false);
            dialogOperationWindow.setScene(new Scene(fxmlParent));
            dialogOperationWindow.initModality(Modality.WINDOW_MODAL);
            dialogOperationWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        }

        //obtain instance of controller
        operationDialogController.setParentScene(((Node) actionEvent.getSource()).getScene());
//        operationDialogController.setOperationDto(operationDto);
//        operationDialogController.setOperations(operations);

        dialogOperationWindow.showAndWait();
    }


    //functional method for closing and opening this window
    private void closeAndOpenThisWindow(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        final String ALL_OPERATIONS_STAGE_TITLE = "All employees";
        final int WIDTH_CLEARANCE = 20;
        final int HEIGHT_CLEARANCE = 50;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/operations/operations.fxml"));

            Scene operationsScene = new Scene(parent);

            stage.setScene(operationsScene);
            stage.setTitle(ALL_OPERATIONS_STAGE_TITLE);

            stage.setMinHeight(450 + HEIGHT_CLEARANCE);
            stage.setMinWidth(450 + WIDTH_CLEARANCE);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


//    public void handleButtonClick(ActionEvent actionEvent){
//        Object source = actionEvent.getSource();
//        // якщо натиснута не кнопка то вихід з метода
//        if(!(source instanceof Button)){
//            return;
//        }
//        Button clickedButton = (Button) source;
//
//    }