package com.agricultural.controllers.employees;

import com.agricultural.domains.dto.EmployeeDto;
import com.agricultural.domains.main.Workplace;
import com.agricultural.service.WorkplaceService;
import com.agricultural.service.impl.WorkplaceServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.Objects;


public class AddEditEmployeeController {

    private WorkplaceService workplaceService = WorkplaceServiceImpl.getInstance();

    private ObservableList workPlacesList = FXCollections.observableArrayList(workplaceService.getAllWorkplaceName());

    private EmployeeDto employeeDto;

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

    }





}
