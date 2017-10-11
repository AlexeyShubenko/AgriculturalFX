package com.agricultural.controllers.operations;

import com.agricultural.domains.dto.TechnologicalOperationDto;
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
import java.util.Objects;

/**
 * Created by Alexey on 09.10.2017.
 */
/*
* handle all actions for operation window
* */
public class OperationController {

    private OperationService operationService = OperationServiceImpl.getInstance();
    private ObservableList<TechnologicalOperationDto> operations
            = FXCollections.observableArrayList(operationService.getOperations());
    private final String ADD_OPERATIONS = "Технологічна операція";
    private final String EDIT_OPERATIONS = "Редагувати технологічну операцію";


    @FXML
    private TextField operationField;
    @FXML
    private TableView operationsTableView;
    @FXML
    private TableColumn<TechnologicalOperationDto, Integer> columnSerialNumber;
    @FXML
    private TableColumn<TechnologicalOperationDto, String> columnOperationName;
    @FXML
    private Label operationCountLabel;
    private OperationDialogController operationDialogController;

    private Parent fxmlParent;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private void initialize() {
        // fill the table with data from database
        columnSerialNumber.setCellValueFactory(new PropertyValueFactory<TechnologicalOperationDto, Integer>("serialNumber"));
        columnOperationName.setCellValueFactory(new PropertyValueFactory<TechnologicalOperationDto, String>("operationName"));
        countUpdate();

        operations.addListener(new ListChangeListener<TechnologicalOperationDto>() {
            @Override
            public void onChanged(Change<? extends TechnologicalOperationDto> c) {
                countUpdate();
            }
        });

        operationsTableView.setItems(operations);
    }

    private void countUpdate() {
        operationCountLabel.setText("Кількість записів в таблиці: " + operations.size());
    }

    /*
    * when click on add new operation Button
    * */
    public void openAddOperationWindow(ActionEvent actionEvent) {
        //null - because we want to create new operation
        createDialogWindow(actionEvent,ADD_OPERATIONS,null);
    }


    public void editOperationOpenWindow(ActionEvent actionEvent) {
        //take exist operation from table for further updating
        TechnologicalOperationDto operationDto = (TechnologicalOperationDto) operationsTableView.getSelectionModel().getSelectedItem();
        if(Objects.isNull(operationDto)){
            DialogManager.showInfo("Wrong action!", "Для редагування треба вибрати поле в таблиці");
            return;
        }
        createDialogWindow(actionEvent,EDIT_OPERATIONS,operationDto);

    }

    private  void createDialogWindow(ActionEvent actionEvent, String title, TechnologicalOperationDto operationDto){
        try {
            Stage stage = new Stage();
            fxmlLoader.setLocation(getClass().getResource("/views/addOperationDialog.fxml"));
            fxmlParent = fxmlLoader.load();

            //obtain instance of controller
            operationDialogController = fxmlLoader.getController();
            operationDialogController.setParentScene(((Node)actionEvent.getSource()).getScene());
            operationDialogController.setOperationDto(operationDto);

            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(fxmlParent));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();

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