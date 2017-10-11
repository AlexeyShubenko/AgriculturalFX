package com.agricultural.domains.dto;

import com.agricultural.domains.main.TechnologicalOperation;

/**
 * Created by Alexey on 02.04.2017.
 */
public class TechnologicalOperationDto{

    private Long id;
    private Integer serialNumber;
    private String operationName;

    public TechnologicalOperationDto(){}

    public TechnologicalOperationDto(String operationName){
        this.operationName = operationName;
    }

    public static class Builder{

        TechnologicalOperationDto operationDto = new TechnologicalOperationDto();


        public Builder setId(TechnologicalOperation operation) {
            operationDto.setId(operation.getOperationId());
            return this;
        }

        public Builder setOperationName(TechnologicalOperation operation) {
            operationDto.setOperationName(operation.getName());
            return this;
        }

        public TechnologicalOperationDto build(){
            return operationDto;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Override
    public String toString() {
        return "TechnologicalOperationDto{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", operationName='" + operationName + '\'' +
                '}';
    }
}
