package com.agricultural.service;

import com.agricultural.domains.dto.EmployeeDto;
import com.agricultural.domains.main.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 12.09.2017.
 */
public interface EmployeeService {

    void createOrUpdateEmployee(EmployeeDto employeeDto);
    void deleteTractorDriver(EmployeeDto employeeDto);
    List<EmployeeDto> getEmployees();
    void editEmployee(EmployeeDto driverDto);
    Employee getTractorDriverByName(String name);
    Employee getEmployeeById(Long driverId);
    String[] getAllEmployeesName();

    boolean isExistEmployee(String emplName, String emplPosition);
}
