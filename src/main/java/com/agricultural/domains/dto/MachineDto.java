package com.agricultural.domains.dto;

import com.agricultural.domains.main.MachineTractorUnit;

/**
 * Created by Alexey on 02.04.2017.
 */
public class MachineDto {

    private Long id;
    private String machineName;
    private Long serialNumber;

    public MachineDto(){}

    public static class Builder{

        MachineDto machineDto = new MachineDto();


        public Builder setId(MachineTractorUnit machine) {
            machineDto.setId(machine.getMachineId());
            return this;
        }

        public Builder setMachineName(MachineTractorUnit machine) {
            machineDto.setMachineName(machine.getName());
            return this;
        }

        public MachineDto build(){
            return machineDto;
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }
}
