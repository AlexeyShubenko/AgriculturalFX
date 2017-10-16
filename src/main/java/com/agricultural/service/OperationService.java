package com.agricultural.service;

import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.TechnologicalOperation;
import com.agricultural.exceptions.InternalDBException;

import java.util.List;

/**
 * Created by Alexey on 12.09.2017.
 */
public interface OperationService {

    Long createOperation(String operationName) throws InternalDBException;
    void deleteOperation(TechnologicalOperationDto operationDto) throws InternalDBException;
    void editOperation(TechnologicalOperationDto operationDto) throws InternalDBException;
    List<TechnologicalOperationDto> getOperations();
    String[] getAllOperationsName();
    TechnologicalOperation getOperationByName(String operationName);
    boolean isExistOperation(String operationName);

}
