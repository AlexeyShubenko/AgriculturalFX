package com.agricultural.service.impl;

import com.agricultural.dao.operations.OperationDAOImpl;
import com.agricultural.dao.operations.OperationsDAO;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.TechnologicalOperation;
import com.agricultural.service.OperationService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Alexey on 12.09.2017.
 */
public class OperationServiceImpl implements OperationService {

    private static OperationServiceImpl instance = new OperationServiceImpl();
    private OperationsDAO operationsDAO = OperationDAOImpl.getInstance();

    private OperationServiceImpl() {
    }

    public static OperationServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Long createOperation(String operationName) {
        return operationsDAO.createOperation(operationName);
    }

    @Override
    public void deleteOperation(TechnologicalOperationDto operationDto) {
        TechnologicalOperation operation = new TechnologicalOperation.Builder()
                .setOperationId(operationDto)
                .setOperationName(operationDto)
                .build();
        System.out.println(operationDto.toString());
        System.out.println(operation.toString());
        operationsDAO.deleteOperation(operation);
    }

    @Override
    public void editOperation(TechnologicalOperationDto operationDto) {
        TechnologicalOperation operation = new TechnologicalOperation.Builder()
                .setOperationId(operationDto)
                .setOperationName(operationDto)
                .build();
        operationsDAO.editOperation(operation);
    }

    @Override
    public List<TechnologicalOperationDto> getOperations() {
        List<TechnologicalOperation> allOperations = operationsDAO.getOperations();
        List<TechnologicalOperationDto> allOperationsDto = new ArrayList<>();

        int i = 1;
        for(TechnologicalOperation operation: allOperations){
            TechnologicalOperationDto operationDto = new TechnologicalOperationDto.Builder()
                    .setId(operation)
                    .setOperationName(operation)
                    .build();
            operationDto.setSerialNumber(i++);
            allOperationsDto.add(operationDto);
            System.out.println(i + ": " + operation.toString());
//            allOperationsDto.sort((o1, o2) -> (int)(o1.getId()-o2.getId()));
        }

//        for (TechnologicalOperationDto op:allOperationsDto) {
//            System.out.println(op.toString());
//        }

        return allOperationsDto;
    }

    @Override
    public String[] getAllOperationsName() {
        return operationsDAO.getAllOperationsName();
    }

    @Override
    public TechnologicalOperation getOperationByName(String operationName) {
        return operationsDAO.getOperationByName(operationName);
    }

    @Override
    public boolean isExistOperation(String operationName) {
        return operationsDAO.isExistOperation(operationName);
    }
}
