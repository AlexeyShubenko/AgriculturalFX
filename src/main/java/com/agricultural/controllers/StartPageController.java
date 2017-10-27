package com.agricultural.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Alexey on 29.09.2017.
 */
public class StartPageController {

    private final String ALL_EMPLOYEES_STAGE_TITLE = "All employees";
    private final String ALL_OPERATIONS_STAGE_TITLE = "All operations";
    private final String ALL_MACHINES_STAGE_TITLE = "All machines";

    private final int WIDTH_CLEARANCE = 20;
    private final int HEIGHT_CLEARANCE = 50;

    public void allEmployees(ActionEvent actionEvent) {
        try {
            Stage allEmployeesStage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/views/employee/employees.fxml"));
            Scene employeesScene = new Scene(parent);

            allEmployeesStage.setTitle(ALL_EMPLOYEES_STAGE_TITLE);
            allEmployeesStage.setScene(employeesScene);

            allEmployeesStage.setMinHeight(350+ HEIGHT_CLEARANCE);
            allEmployeesStage.setMinWidth(1200+ WIDTH_CLEARANCE);

            //встановлює вікно з якого було визвано дане вікно
            allEmployeesStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            allEmployeesStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void allTechnologicalOperations(ActionEvent actionEvent) {

        try {
            Stage allOperationsStage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/views/operations/operations.fxml"));

            Scene operationsScene = new Scene(parent);

            allOperationsStage.setScene(operationsScene);
            allOperationsStage.setTitle(ALL_OPERATIONS_STAGE_TITLE);

            allOperationsStage.setMinHeight(450+ HEIGHT_CLEARANCE);
            allOperationsStage.setMinWidth(450+ WIDTH_CLEARANCE);

            //Вікно встановлюєтсья як модальне
//            allOperationsStage.initModality(Modality.WINDOW_MODAL);
            //встановлює вікно з якого було визвано дане вікно
//            allOperationsStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            allOperationsStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void allMachines(ActionEvent actionEvent) {

        try {
            Stage allMachineStage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/views/machines/machines.fxml"));
            Scene machinesScene = new Scene(parent);

            allMachineStage.setScene(machinesScene);
            allMachineStage.setTitle(ALL_MACHINES_STAGE_TITLE);

            allMachineStage.setMinHeight(450+ HEIGHT_CLEARANCE);
            allMachineStage.setMinWidth(550+ WIDTH_CLEARANCE);
            allMachineStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());

            allMachineStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
