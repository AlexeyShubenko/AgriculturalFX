package com.agricultural.domains.main;


import com.agricultural.domains.dto.EmployeeDto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 12.02.2017.
 */
@Data
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fio")
    private String name;
    //тарифна ставка
    @Column(name = "wage")
    private int wageRate;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "workplaceId")
    private Workplace workplace;///за місцем роботи

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DateAndInformation> dateAndInformation = new ArrayList<>();

    public Employee(){}

    public static class Builder {

        Employee employee = new Employee();

        public Builder setId(EmployeeDto employeeDto) {
            employee.setId(employeeDto.getId());
            return this;
        }

        public Builder setName(EmployeeDto employeeDto) {
            employee.setName(employeeDto.getName());
            return this;
        }
        public Builder setWageRate(EmployeeDto employeeDto) {
            employee.setWageRate(employeeDto.getWageRate());
            return this;
        }

        public Builder setPosition(EmployeeDto employeeDto) {
            employee.setPosition(employeeDto.getPosition());
            return this;
        }
        public Builder setWorkplace(EmployeeDto employeeDto) {
            employee.setWorkplace(employeeDto.getWorkplace());
            return this;
        }

        public Employee build(){
            return employee;
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

    public void setWageRate(int wageRate) {
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

    public List<DateAndInformation> getDateAndInformation() {
        return dateAndInformation;
    }

    public void setDateAndInformation(List<DateAndInformation> dateAndInformation) {
        this.dateAndInformation = dateAndInformation;
    }


}
