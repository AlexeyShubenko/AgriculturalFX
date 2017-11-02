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

    private final String ADD_EMPLOYEE = "Додати нового працівника";



    private void addEmployee(ActionEvent actionEvent) {
        System.out.println("ADD");

        createAddEditWindow(actionEvent, ADD_EMPLOYEE);

    }



    private void createAddEditWindow(ActionEvent actionEvent, String title) {


    }


}
