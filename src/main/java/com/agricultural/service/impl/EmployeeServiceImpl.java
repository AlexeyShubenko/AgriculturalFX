package com.agricultural.service.impl;

import com.agricultural.dao.tractordrivers.TractorDriverDao;
import com.agricultural.dao.tractordrivers.TractorDriverDaoImpl;
import com.agricultural.domains.dto.EmployeeDto;
import com.agricultural.domains.main.Employee;
import com.agricultural.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexey on 12.09.2017.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeServiceImpl instance = new EmployeeServiceImpl();
    private TractorDriverDao tractorDriverDao = TractorDriverDaoImpl.getInstance();

    private EmployeeServiceImpl() {
    }

    public static EmployeeServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void createOrUpdateEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee.Builder()
                .setId(employeeDto)
                .setName(employeeDto)
                .setPosition(employeeDto)
                .setWageRate(employeeDto)
                .setWorkplace(employeeDto)
                .build();
        tractorDriverDao.createOrUpdateTractorDriver(employee);
    }

    @Override
    public void deleteTractorDriver(EmployeeDto employeeDto) {
        Employee employee = new Employee.Builder()
                .setId(employeeDto)
                .setName(employeeDto)
                .setPosition(employeeDto)
                .setWageRate(employeeDto)
                .setWorkplace(employeeDto)
                .build();
        tractorDriverDao.deleteTractorDriver(employee);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = tractorDriverDao.getTractorDrivers();

        List<EmployeeDto> employeesDto = employees.stream()
                .map(employee -> new EmployeeDto.Builder()
                        .setId(employee)
                        .setName(employee)
                        .setPosition(employee)
                        .setWageRate(employee)
                        .setWorkplace(employee)
                        .build())
                .collect(Collectors.toList());
        long serialNumber =0;
        for(EmployeeDto employeeDto: employeesDto){
            employeeDto.setSerialNumber(++serialNumber);
        }
        return employeesDto;
    }

    @Override
    public void editEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee.Builder()
                .setId(employeeDto)
                .setName(employeeDto)
                .setPosition(employeeDto)
                .setWageRate(employeeDto)
                .setWorkplace(employeeDto)
                .build();
        tractorDriverDao.editTractorDriver(employee);
    }

    @Override
    public Employee getTractorDriverByName(String name) {
        return tractorDriverDao.getTractorDriverByName(name);
    }

    @Override
    public Employee getEmployeeById(Long driverId) {
        return tractorDriverDao.getEmployeeById(driverId);
    }

    @Override
    public String[] getAllEmployeesName() {
        return tractorDriverDao.getAllTractorDriversName();
    }

    @Override
    public boolean isExistEmployee(String emplName, String emplPosition) {
        return tractorDriverDao.isExistEmployee(emplName, emplPosition);
    }
}
