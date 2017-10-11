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
    public void createOperation(String operationName) {
        operationsDAO.createOperation(operationName);
    }

    @Override
    public void deleteOperation(TechnologicalOperation operation) {
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
