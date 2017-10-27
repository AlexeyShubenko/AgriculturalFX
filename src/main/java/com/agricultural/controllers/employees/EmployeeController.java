package com.agricultural.controllers.employees;

import com.agricultural.domains.dto.EmployeeDto;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.service.EmployeeService;
import com.agricultural.service.impl.EmployeeServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeController {

    private EmployeeService employeeService = EmployeeServiceImpl.getInstance();
    private ObservableList<EmployeeDto> employees;

    private Stage addEditEmployeeWindow;
    private Parent fxmlParentAdd;
    private FXMLLoader fxmlLoaderAdd = new FXMLLoader();

    private Stage detailInformationWindow;
    private Parent fxmlParentDetailInf;
    private FXMLLoader fxmlLoaderDetailInf = new FXMLLoader();


    @FXML
    private TableView employeesTableView;
    @FXML
    private TableColumn<EmployeeDto, Long> columnSerialNumber;
    @FXML
    private TableColumn<EmployeeDto, String> columnEmplName;
    @FXML
    private TableColumn<EmployeeDto, Integer> columnEmplWageRate;
    @FXML
    private TableColumn<EmployeeDto, String> columnEmplPosition;
    @FXML
    private TableColumn<EmployeeDto, String> columnEmplWorkPlace;

    @FXML
    private void initialize(){
        employees = FXCollections.observableArrayList(employeeService.getEmployees());

        // fill the table with data from database
        columnSerialNumber.setCellValueFactory(new PropertyValueFactory<EmployeeDto, Long>("serialNumber"));
        columnEmplName.setCellValueFactory(new PropertyValueFactory<EmployeeDto, String>("name"));
        columnEmplWageRate.setCellValueFactory(new PropertyValueFactory<EmployeeDto, Integer>("wageRate"));
        columnEmplPosition.setCellValueFactory(new PropertyValueFactory<EmployeeDto, String>("position"));
        columnEmplWorkPlace.setCellValueFactory(new PropertyValueFactory<EmployeeDto, String>("workName"));

//        try {
//            fxmlLoaderAdd.setLocation(getClass().getResource("/views/employee/addEmployee.fxml"));
//            fxmlParentDetailInf = fxmlLoaderAdd.load();
            //////////////////////////////////////////
//            fxmlLoaderDetailInf.setLocation(getClass().getResource("/views/employee/employeeDetailInf.fxml"));
//            fxmlParentAdd = fxmlLoaderDetailInf.load();
//            operationDialogController = fxmlLoader.getController();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        employeesTableView.setItems(employees);

    }


}
