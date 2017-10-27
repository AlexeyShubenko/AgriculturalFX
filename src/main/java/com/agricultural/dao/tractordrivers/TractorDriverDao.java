package com.agricultural.dao.tractordrivers;

import com.agricultural.domains.main.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 14.02.2017.
 */
public interface TractorDriverDao {

    void createOrUpdateTractorDriver(Employee employee);
    void deleteTractorDriver(Employee driver);
    void editTractorDriver(Employee driver);
    List<Employee> getTractorDrivers();
    Employee getTractorDriverByName(String name);
    Employee getEmployeeById(Long driverId);
    String[] getAllTractorDriversName();
}
