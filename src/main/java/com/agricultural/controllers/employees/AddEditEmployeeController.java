package com.agricultural.controllers.employees;

import com.agricultural.domains.dto.EmployeeDto;
import com.agricultural.domains.main.Workplace;
import com.agricultural.service.EmployeeService;
import com.agricultural.service.WorkplaceService;
import com.agricultural.service.impl.EmployeeServiceImpl;
import com.agricultural.service.impl.WorkplaceServiceImpl;
import com.agricultural.utils.DialogManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class AddEditEmployeeController {

    private WorkplaceService workplaceService = WorkplaceServiceImpl.getInstance();
    private EmployeeService employeeService = EmployeeServiceImpl.getInstance();

    private ObservableList workPlacesList = FXCollections.observableArrayList(workplaceService.getAllWorkplaceName());

    private EmployeeDto employeeDto;


    private Stage addWorkplace;
    private Parent fxmlParentAdd;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldWageRate;
    @FXML
    private TextField textFieldPosition;
    @FXML
    private ChoiceBox workPlacesBox;
    private boolean isUpdate;

    @FXML
    private void initialize(){

        workPlacesBox.setItems(workPlacesList);
        workPlacesBox.setTooltip(new Tooltip("Оберіть пункт"));

        try {
            fxmlLoader.setLocation(getClass().getResource("/views/employee/addWorkplace.fxml"));
            fxmlParentAdd = fxmlLoader.load();
//            addEditEmployeeController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        //isNull -> new Employee
        if(Objects.isNull(employeeDto)){
            this.employeeDto = new EmployeeDto();
            this.employeeDto.setName("");
            this.employeeDto.setWageRate(0);
            this.employeeDto.setPosition("");
        }else {
            this.employeeDto = employeeDto;
            this.isUpdate = true;
            //choiceBox заповнюється необхідним значенням workPlace
            this.workPlacesBox.getSelectionModel().select(employeeDto.getWorkName());
        }
        textFieldName.setText(this.employeeDto.getName());
        textFieldPosition.setText(this.employeeDto.getPosition());
        textFieldWageRate.setText(String.valueOf(this.employeeDto.getWageRate()));
    }


    /*
     * when click on Button to add new Employee
    */
    public void saveEditAction(ActionEvent actionEvent){

        workPlacesBox.getItems().addAll("new");

        String emplName = textFieldName.getText().trim();
        String emplWageRate = textFieldWageRate.getText().trim();
        String emplPosition = textFieldPosition.getText().trim();
        String emplWorkPlace = (String) workPlacesBox.getValue();


        if (emplName.equals("")
                || emplWageRate.equals("")
                || emplPosition.equals("") ) {
            DialogManager.showError("Помилка при введені даних", "Заповніть необхідні поля!");
            return;
        }
        boolean isExistEmployee = employeeService.isExistEmployee(emplName,emplPosition);

        if (isExistEmployee) {
            DialogManager.showError("Помилка при введені даних",
                    "Працівник: " + emplName+ " з посадою: " + emplPosition + " вже існує!");
            return;
        }

        if(isUpdate){
            //якщо редагуємо дані праціника


        }else {

        }

    }

    public void addWorkplace(ActionEvent actionEvent){
        if (addWorkplace == null) {
            addWorkplace = new Stage();
            addWorkplace.setTitle("За місцем роботи");
            addWorkplace.setResizable(false);
            addWorkplace.setScene(new Scene(fxmlParentAdd));
            addWorkplace.initModality(Modality.WINDOW_MODAL);
            addWorkplace.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        }
        addWorkplace.showAndWait();
    }


}
