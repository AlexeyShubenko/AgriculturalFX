package com.agricultural.controllers.employees;

import com.agricultural.domains.dto.EmployeeDto;
import com.agricultural.service.EmployeeService;
import com.agricultural.service.impl.EmployeeServiceImpl;
import com.agricultural.utils.DialogManager;
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
import java.util.Objects;

public class EmployeeController {

    private EmployeeService employeeService = EmployeeServiceImpl.getInstance();
    private ObservableList<EmployeeDto> employees;

    private final String ADD_EMPLOYEE = "Додати нового працівника";

    private Stage addEditEmployeeWindow;
    private Parent fxmlParentAddEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Stage detailInformationWindow;
    private Parent fxmlParentDetailInf;
//    private FXMLLoader fxmlLoaderDetailInf = new FXMLLoader();

    private AddEditEmployeeController addEditEmployeeController;

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

        try {
            fxmlLoader.setLocation(getClass().getResource("/views/employee/addEditEmployee.fxml"));
            fxmlParentAddEdit = fxmlLoader.load();
            addEditEmployeeController = fxmlLoader.getController();
            ////////////////////////////////////////
//            fxmlLoaderDetailInf.setLocation(getClass().getResource("/views/employee/employeeDetailInf.fxml"));
//            fxmlParentAdd = fxmlLoaderDetailInf.load();
//            operationDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        employeesTableView.setItems(employees);

    }

    /*
    * даний метод розріхняє яка кнопка була натиснути
    * */
    public void checkButtonAction(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)){
            return;
        }
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()){
            case "addEmpl":
                addEmployee(actionEvent);
                break;
            case "editEmpl":
                editEmployee(actionEvent);
                break;
            case "detailInfEmpl":
                detailInfEmployee(actionEvent);
                break;
            case "deleteEmpl":
                deleteEmployee(actionEvent);
                break;
        }


    }

    private void addEmployee(ActionEvent actionEvent) {
        System.out.println("ADD");
        this.addEditEmployeeController.setEmployeeDto(null);
        createAddEditWindow(actionEvent, ADD_EMPLOYEE);



    }

    private void editEmployee(ActionEvent actionEvent) {
        System.out.println("EDIT");
        EmployeeDto employeeToEdit =
                (EmployeeDto) employeesTableView.getSelectionModel().getSelectedItem();
        if (Objects.isNull(employeeToEdit)) {
            DialogManager.showInfo("Wrong action!", "Для редагування треба вибрати поле в таблиці!");
            return;
        }
        this.addEditEmployeeController.setEmployeeDto(employeeToEdit);
        createAddEditWindow(actionEvent, ADD_EMPLOYEE);

    }

    private void detailInfEmployee(ActionEvent actionEvent) {
        System.out.println("DETAIL");
    }

    private void deleteEmployee(ActionEvent actionEvent) {
        System.out.println("DELETE");
    }


    private void createAddEditWindow(ActionEvent actionEvent, String title) {

        if (addEditEmployeeWindow == null) {
            addEditEmployeeWindow = new Stage();
            addEditEmployeeWindow.setTitle(title);
            addEditEmployeeWindow.setResizable(false);
            addEditEmployeeWindow.setScene(new Scene(fxmlParentAddEdit));
            addEditEmployeeWindow.initModality(Modality.WINDOW_MODAL);
            addEditEmployeeWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        }

        //obtain instance of controller
//        addEditEmployeeController.setParentScene(((Node) actionEvent.getSource()).getScene());
//        operationDialogController.setOperationDto(operationDto);
//        operationDialogController.setOperations(operations);

        addEditEmployeeWindow.showAndWait();
    }


}
