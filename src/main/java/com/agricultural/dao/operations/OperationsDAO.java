package com.agricultural.dao.operations;

import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.TechnologicalOperation;
import com.agricultural.exceptions.InternalDBException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 14.02.2017.
 */
public interface OperationsDAO {

    Long createOperation(String operationName) throws InternalDBException;
    void deleteOperation(TechnologicalOperation operation) throws InternalDBException;
    void editOperation(TechnologicalOperation operation) throws InternalDBException;
    List<TechnologicalOperation> getOperations();
    String[] getAllOperationsName();
    TechnologicalOperation getOperationByName(String operationName);
    boolean isExistOperation(String operation);
}
