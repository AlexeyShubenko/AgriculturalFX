package com.agricultural.controllers.machines;

import com.agricultural.controllers.StartPageController;
import com.agricultural.domains.dto.MachineDto;
import com.agricultural.exceptions.InternalDBException;
import com.agricultural.service.MachineService;
import com.agricultural.service.impl.MachineServiceImpl;
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

    private MachineService machineService = MachineServiceImpl.getInstance();

    //use for updating parent window after operation save or update
    private Scene parentScene;
    //user fot creating parent window
    private static StartPageController startPageController = new StartPageController();

    //use for updating of existing operation
    private MachineDto machineNeedToEdit;
    //check is operation for updating
    private boolean isUpdate = false;

    @FXML
    private TextField machineField;


    public MachineDto getMachineDto() {
        return machineNeedToEdit;
    }

    public void setMachineDto(MachineDto machineDtoToEdit) {
        if (Objects.isNull(machineDtoToEdit)) {
            this.machineNeedToEdit = new MachineDto();
            this.machineNeedToEdit.setMachineName("");
        }else {
            this.machineNeedToEdit = machineDtoToEdit;
            this.isUpdate = true;
        }
        //if we try to create new operation then text field will be empty
        //if edit -> field will be filled
        machineField.setText(this.machineNeedToEdit.getMachineName());
    }


    public void setParentScene(Scene parentScene) {
        this.parentScene = parentScene;
    }

    /*
     * when click on Button to add new Operation
    */
    public void saveEditAction(ActionEvent actionEvent){

        String newMachineName = machineField.getText().trim();

        if (machineField.equals("")) {
            return;
            //DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
        }
        boolean isExistOperation = machineService.isExistMachine(newMachineName);
                //isExistOperation(newOperationName);

        if (isExistOperation) {
            DialogManager.showError("Помилка при введені даних", "Машино тракторний агрегат " + newMachineName + " вже існує!");
            return;
        }

        if(isUpdate){
            //then update in db
            //індекс операції в списку відповідає її serialNumber
            //для оновлення даних в таблицы просто оновлюэмо дані в observableList
            this.machineNeedToEdit.setMachineName(newMachineName);
            //оновлюємо дані в базі даних
            try{
                machineService.editMachine(machineNeedToEdit);
            }catch (InternalDBException internalException){
                DialogManager.showError("Помилка!", internalException.getMessage());
                // зануляємо силку, це буде значити що не вдалося виконати зміни в бд
                this.machineNeedToEdit = null;
            }

        }else {
            try{
                Long id = machineService.createMachine(newMachineName);
                this.machineNeedToEdit.setMachineName(newMachineName);
                this.machineNeedToEdit.setId(id);
            }catch (InternalDBException internalException){
                DialogManager.showError("Помилка!", internalException.getMessage());
                // зануляємо силку, це буде значити що не вдалося виконати зміни в бд
                this.machineNeedToEdit = null;
            }
        }

        closeOperationDialog(actionEvent);
    }

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

    }


}