package com.agricultural.service;

import com.agricultural.domains.dto.MachineDto;
import com.agricultural.domains.main.MachineTractorUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 11.09.2017.
 */
public interface MachineService {

    void createMachine(String machineName);
    void deleteMachine(MachineTractorUnit machineTractorUnit);
    void editMachine(MachineTractorUnit tractor);
    List<MachineDto> getMachines();
    String[] getAllMachinesName();
    MachineTractorUnit getMachineByName(String machineName);
    boolean isExistMachine(String machineName);

}
