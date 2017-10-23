package com.agricultural.service;

import com.agricultural.domains.dto.MachineDto;
import com.agricultural.domains.main.MachineTractorUnit;
import com.agricultural.exceptions.InternalDBException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 11.09.2017.
 */
public interface MachineService {

    Long createMachine(String machineName) throws InternalDBException;
    void deleteMachine(MachineDto machineDto) throws InternalDBException;
    void editMachine(MachineDto machineDto) throws InternalDBException;
    List<MachineDto> getMachines();
    String[] getAllMachinesName();
    MachineTractorUnit getMachineByName(String machineName);
    boolean isExistMachine(String machineName);

}
