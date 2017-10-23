package com.agricultural.dao.machinesunit;

import com.agricultural.domains.main.MachineTractorUnit;
import com.agricultural.exceptions.InternalDBException;

import java.util.ArrayList;

/**
 * Created by Alexey on 14.02.2017.
 */
public interface MachinesDAO {

    Long createMachine(String machineName) throws InternalDBException;
    void deleteMachine(MachineTractorUnit machineTractorUnit) throws InternalDBException;
    void editMachine(MachineTractorUnit tractor) throws InternalDBException;
    ArrayList<MachineTractorUnit> getMachines();
    String[] getAllMachinesName();
    MachineTractorUnit getMachineByName(String machineName);
    boolean isExistMachine(String machineName);

}
