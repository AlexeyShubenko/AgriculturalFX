package com.agricultural.controllers.machines;

import com.agricultural.controllers.StartPageController;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.exceptions.InternalDBException;
import com.agricultural.service.OperationService;
import com.agricultural.service.impl.OperationServiceImpl;
import com.agricultural.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Created by Alexey on 09.10.2017.
 */
/*
* handle all actions for operation window
* */
public class MachineDialogController {

    private OperationService operationService = OperationServiceImpl.getInstance();

    //use for updating parent window after operation save or update
    private Scene parentScene;
    //user fot creating parent window
    private static StartPageController startPageController = new StartPageController();

    //use for updating of existing operation
    private TechnologicalOperationDto operationNeedToEdit;
    //check is operation for updating
    private boolean isUpdate = false;

    @FXML
    private TextField operationField;
//    private ObservableList<TechnologicalOperationDto> operations;


    public TechnologicalOperationDto getOperationDto() {
        return operationNeedToEdit;
    }

    public void setOperationDto(TechnologicalOperationDto operationDtoToEdit) {
        if (Objects.isNull(operationDtoToEdit)) {
            this.operationNeedToEdit = new TechnologicalOperationDto();
            this.operationNeedToEdit.setOperationName("");
        }else {
            this.operationNeedToEdit = operationDtoToEdit;
            this.isUpdate = true;
        }
        //if we try to create new operation then text field will be empty
        //if edit -> field will be filled
        operationField.setText(this.operationNeedToEdit.getOperationName());
    }


    public void setParentScene(Scene parentScene) {
        this.parentScene = parentScene;
    }

    /*
     * when click on Button to add new Operation
    */
    public void saveEditAction(ActionEvent actionEvent){

        String newOperationName = operationField.getText().trim();

        if (newOperationName.equals("")) {
            return;
            //DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
        }
        boolean isExistOperation = operationService.isExistOperation(newOperationName);
                //isExistOperation(newOperationName);

        if (isExistOperation) {
            DialogManager.showError("Помилка при введені даних", "Операція " + newOperationName + " вже існує!");
            return;
        }

        if(isUpdate){
            //then update in db
            //індекс операції в списку відповідає її serialNumber
            //для оновлення даних в таблицы просто оновлюэмо дані в observableList
            this.operationNeedToEdit.setOperationName(newOperationName);
            //оновлюємо дані в базі даних
            try{
                operationService.editOperation(operationNeedToEdit);
            }catch (InternalDBException internalException){
                DialogManager.showError("Помилка!", internalException.getMessage());
                // зануляємо силку, це буде значити що не вдалося виконати зміни в бд
                this.operationNeedToEdit = null;
            }

        }else {
            try{
                Long id = operationService.createOperation(newOperationName);
                this.operationNeedToEdit.setOperationName(newOperationName);
                this.operationNeedToEdit.setId(id);
            }catch (InternalDBException internalException){
                DialogManager.showError("Помилка!", internalException.getMessage());
                // зануляємо силку, це буде значити що не вдалося виконати зміни в бд
                this.operationNeedToEdit = null;
            }
        }

        closeOperationDialog(actionEvent);
    }

//    private boolean isExistOperation(String newOperation) {
//        // find how many operations have name newOperation
//        long count = operations.stream().filter(element -> element.getOperationName().equals(newOperation)).count();
//        //if count>0 -> operation exists
//        return count>0?true:false;
//    }

    /*
     * close add operation dialog window
     * */
    public void closeOperationDialog(ActionEvent actionEvent) {
        // close this dialog window
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Object source = actionEvent.getSource();
        if(source instanceof Button){
            Button cancelButton = (Button) source;
            //if cancel button was clicked
            if(cancelButton.getId().equals("cancelBtn")){
                return;
            }
        }

        //close operation window
//        Stage parentStage = (Stage) parentScene.getWindow();
//        parentStage.close();
//        //open operation window
//        startPageController.allTechnologicalOperations(new ActionEvent());

    }


}