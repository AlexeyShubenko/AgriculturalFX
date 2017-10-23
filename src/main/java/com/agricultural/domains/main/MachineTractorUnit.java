package com.agricultural.domains.main;

import com.agricultural.domains.dto.MachineDto;
import com.agricultural.domains.dto.TechnologicalOperationDto;
import com.agricultural.domains.gectarniyvirobitok.DriverDataHectare;
import com.agricultural.domains.hoursvirobitok.DriverDataHour;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 12.02.2017.
 */
@Data
@Entity
@Table(name = "machines")
public class MachineTractorUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long machineId;
    @Column(name = "machine")
    private String name;

    @OneToMany(mappedBy = "machine")
    private List<DriverDataHectare> listDataHectare = new ArrayList<>();

    @OneToMany(mappedBy = "machine")
    private List<DriverDataHour> listDataHour = new ArrayList<>();

    public MachineTractorUnit(){}

    public MachineTractorUnit(String name){
        this.name = name;
    }

    public static class Builder{

        MachineTractorUnit machineTractorUnit = new MachineTractorUnit();

        public Builder setId(MachineDto machineDto){
            machineTractorUnit.setMachineId(machineDto.getId());
            return this;
        }
        public Builder setMachineName(MachineDto machineDto){
            machineTractorUnit.setName(machineDto.getMachineName());
            return this;
        }



        public MachineTractorUnit build(){
            return machineTractorUnit;
        }

    }


}
