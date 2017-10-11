package com.agricultural.service;

import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.TechnologicalOperation;
import java.util.List;

/**
 * Created by Alexey on 12.09.2017.
 */
public interface OperationService {

    void createOperation(String operationName);
    void deleteOperation(TechnologicalOperation operation);
    void editOperation(TechnologicalOperationDto operationDto);
    List<TechnologicalOperationDto> getOperations();
    String[] getAllOperationsName();
    TechnologicalOperation getOperationByName(String operationName);
    boolean isExistOperation(String operationName);

}
