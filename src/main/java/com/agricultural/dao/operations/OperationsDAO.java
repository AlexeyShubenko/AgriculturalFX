package com.agricultural.dao.operations;

import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.TechnologicalOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 14.02.2017.
 */
public interface OperationsDAO {

    Long createOperation(String operationName);
    void deleteOperation(TechnologicalOperation operation);
    void editOperation(TechnologicalOperation operation);
    List<TechnologicalOperation> getOperations();
    String[] getAllOperationsName();
    TechnologicalOperation getOperationByName(String operationName);
    boolean isExistOperation(String operation);
}
