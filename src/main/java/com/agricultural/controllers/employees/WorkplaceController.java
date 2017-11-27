package com.agricultural.controllers.employees;

import com.agricultural.domains.dto.EmployeeDto;
import com.agricultural.service.EmployeeService;
import com.agricultural.service.impl.EmployeeServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkplaceController {

    private WorkplaceService workplaceService = WorkplaceServiceImpl.getInstance();
    private EmployeeDto employeeDto;
    private String workplace;
    @FXML
    private TextField workplaceField;

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }


    public void addWorkplace(ActionEvent actionEvent) {
        System.out.println("IN addWorkplace!");
        String placeName = workplaceField.getText().trim();
        if (placeName.equals("")) {
            return;
            //DialogManager.showError("Помилка при введені даних", "Заповніть текстове поле!");
        }
        boolean isExistWorkplace = workplaceService.isExistWorkplace(placeName);
        if (isExistWorkplace) {
            DialogManager.showError("Помилка при введені даних", "Місце роботи " + placeName + " вже існує!");
            return;
        }
        //save into database
        try {
            workplaceService.createWorkPlace(placeName);
        } catch (InternalDBException internalException) {
            DialogManager.showError("Помилка!", internalException.getMessage());
            return;
        }
        closeOperationDialog(actionEvent);
    }

     public void closeOperationDialog(ActionEvent actionEvent) {
        // close this dialog window
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

//        Object source = actionEvent.getSource();
//        if (source instanceof Button) {
//            Button cancelButton = (Button) source;
//            //if cancel button was clicked
//            if (cancelButton.getId().equals("cancelBtn")) {
//                return;
//            }
//        }
        //close operation window
        Stage parentStage = (Stage) parentScene.getWindow();
        parentStage.close();
        //open operation window

//        FXMLLoader fxmlLoader = new FXMLLoader();
//
//         fxmlLoader.setLocation(getClass().getResource("/views/employee/employees.fxml"));
//        try {
//            Parent fxmlParentAddEdit = fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        EmployeeController employeeController = fxmlLoader.getController();
//
//        employeeController.addEmployee(this.parentEmployeeActionEvent);

    }

    
}
