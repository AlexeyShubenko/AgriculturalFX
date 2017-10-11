package com.agricultural.controllers.operations;

import com.agricultural.controllers.StartPageController;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.TechnologicalOperation;
import com.agricultural.service.OperationService;
import com.agricultural.service.impl.OperationServiceImpl;
import com.agricultural.utils.DialogManager;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Alexey on 09.10.2017.
 */
/*
* handle all actions for operation window
* */
public class OperationDialogController {

    private OperationService operationService = OperationServiceImpl.getInstance();

    //use for updating parent window after operation save or update
    private Scene parentScene;
    //user fot creating parent window
    private static StartPageController startPageController = new StartPageController();

    //use for updating of existing operation
    private TechnologicalOperationDto operationDto;
    //check is operation for updating
    private boolean isUpdate = false;

    @FXML
    private TextField operationField;

    public TechnologicalOperationDto getOperationDto() {
        return operationDto;
    }

    public void setOperationDto(TechnologicalOperationDto operationDto) {
        if (Objects.isNull(operationDto)) {
            this.operationDto = new TechnologicalOperationDto();
            this.operationDto.setOperationName("");
        }else {
            this.operationDto = operationDto;
            this.isUpdate = true;
        }
        //if we try to create new operation then text field will be empty
        //if edit -> field will be filled
        operationField.setText(this.operationDto.getOperationName());
    }

    public Scene getParentScene() {
        return parentScene;
    }

    public void setParentScene(Scene parentScene) {
        this.parentScene = parentScene;
    }


    /*
     * when click on Button to add new Operation
    */
    public void saveEditAction(ActionEvent actionEvent){
        String newOperation = operationField.getText().trim();

        if (newOperation.equals("")) {
            return;
            //DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
        }
        boolean isExistOperation = operationService.isExistOperation(newOperation);

        if (isExistOperation) {
            DialogManager.showError("Помилка при введені даних", "Операція " + newOperation + " вже існує!");
            return;
        }

        operationDto.setOperationName(newOperation);
        if(isUpdate){
            //then update in db
            operationService.editOperation(operationDto);
        }else {
            operationService.createOperation(newOperation);
        }
        closeOperationDialog(actionEvent);
    }


    /*
   * close add operation dialog window
   * */
    public void closeOperationDialog(ActionEvent actionEvent) {
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        //close operation window
        Stage parentStage = (Stage) parentScene.getWindow();
        parentStage.close();

        startPageController.allTechnologicalOperations(new ActionEvent());

    }
}