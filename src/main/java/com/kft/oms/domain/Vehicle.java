package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Vehicle extends AbstractEntity {

    //stands for types like vitz, landcruser,..
    private String type;

    @NotNull
    @Embedded
    private VehiclePlate plate;

    //Todo: check uniqueness and add if necessary
    //@Max(99999)
    private Integer sideNo;

    @ManyToMany(mappedBy = "vehicles")
    private List<VehicleOwner> owners;

    @ManyToOne
    private Association association;



    public VehiclePlate getPlate() {
        return plate;
    }

    public void setPlate(VehiclePlate plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSideNo() {
        return sideNo;
    }

    public void setSideNo(Integer sideNo) {
        this.sideNo = sideNo;
    }

    public List<VehicleOwner> getOwners() {
        return owners;
    }

    public void setOwners(List<VehicleOwner> owners) {
        this.owners = owners;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }
}
