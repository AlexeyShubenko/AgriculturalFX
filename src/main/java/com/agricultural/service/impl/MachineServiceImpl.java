package com.agricultural.service.impl;

import com.agricultural.dao.machinesunit.MachinesDAO;
import com.agricultural.dao.machinesunit.MachinesDAOImpl;
import com.agricultural.domains.dto.MachineDto;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.main.MachineTractorUnit;
import com.agricultural.exceptions.InternalDBException;
import com.agricultural.service.MachineService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexey on 11.09.2017.
 */
public class MachineServiceImpl implements MachineService {

    private static MachineServiceImpl instance = new MachineServiceImpl();
    private MachinesDAO machinesDAO = MachinesDAOImpl.getInstance();

    private MachineServiceImpl() {
    }

    public static MachineServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Long createMachine(String machineName) throws InternalDBException {
       return machinesDAO.createMachine(machineName);
    }

    @Override
    public void deleteMachine(MachineDto machineDto) throws InternalDBException {
        MachineTractorUnit machineTractorUnit = new MachineTractorUnit.Builder()
                .setId(machineDto)
                .setMachineName(machineDto)
                .build();
        machinesDAO.deleteMachine(machineTractorUnit);
    }

    @Override
    public void editMachine(MachineDto machineDto) throws InternalDBException {
        MachineTractorUnit machineTractorUnit = new MachineTractorUnit.Builder()
                .setId(machineDto)
                .setMachineName(machineDto)
                .build();
        machinesDAO.editMachine(machineTractorUnit);
    }

    @Override
    public List<MachineDto> getMachines() {
        List<MachineTractorUnit> allMachines = machinesDAO.getMachines();

        List<MachineDto> allMachinesDto = allMachines.stream()
                .map(machine -> new MachineDto.Builder()
                        .setId(machine)
                        .setMachineName(machine)
                        .build())
                .collect(Collectors.toList());

        int i = 1;
        for(MachineDto machineDto: allMachinesDto){
            machineDto.setSerialNumber(i++);
        }
        return allMachinesDto;
    }

    @Override
    public String[] getAllMachinesName() {
        return machinesDAO.getAllMachinesName();
    }

    @Override
    public MachineTractorUnit getMachineByName(String machineName){
        return machinesDAO.getMachineByName(machineName);
    }

    @Override
    public boolean isExistMachine(String machineName) {
        return machinesDAO.isExistMachine(machineName);
    }
}
