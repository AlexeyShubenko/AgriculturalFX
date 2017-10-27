package com.agricultural.domains.dto;

import com.agricultural.domains.main.Employee;
import com.agricultural.domains.main.Workplace;

/**
 * Created by Alexey on 02.04.2017.
 */
public class EmployeeDto {

    private Long id;
    private String name;
    private Integer wageRate;
    private String position;
    private String workName;

    private Workplace workplace;

    private Long serialNumber;

    public EmployeeDto() {}

    public static class Builder{

        EmployeeDto employeeDto = new EmployeeDto();

        public Builder setId(Employee employee) {
            employeeDto.setId(employee.getId());
            return this;
        }

        public Builder setName(Employee employee) {
            employeeDto.setName(employee.getName());
            return this;
        }
        public Builder setWageRate(Employee employee) {
            employeeDto.setWageRate(employee.getWageRate());
            return this;
        }

        public Builder setPosition(Employee employee) {
            employeeDto.setPosition(employee.getPosition());
            return this;
        }
        public Builder setWorkplace(Employee employee) {
            employeeDto.setWorkplace(employee.getWorkplace());
            employeeDto.setWorkName(employee.getWorkplace().getWorkPlaceName());
            return this;
        }

        public EmployeeDto build(){
            return employeeDto;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWageRate() {
        return wageRate;
    }

    public void setWageRate(Integer wageRate) {
        this.wageRate = wageRate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}


