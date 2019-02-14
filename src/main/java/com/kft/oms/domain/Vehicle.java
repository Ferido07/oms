package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@DiscriminatorColumn(name = "vehicle_type")
public class Vehicle extends AbstractEntity {

    //stands for types like vitz, landcruser,..
    private String type;

    @NotBlank
    @Column(unique = true, nullable = false, length = 14)
    @Pattern(regexp = "[1-5]-[A-Z]?[0-9]{5}-[A-Z]{2}")
    private String plateNo;

    //Done: check uniqueness and add if necessary
    //@Max(99999)
    @Column(unique = true, length = 10)
    private String sideNo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Association association;

    //TODO: Add constraint for bolo
    private String bolo;

    @Column(unique = true, length = 30)
    private String libre;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleOwnership> vehicleOwnerships;



    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo.toUpperCase();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSideNo() {
        return sideNo;
    }

    public void setSideNo(String sideNo) {
        this.sideNo = sideNo == null ? null : sideNo.isEmpty() ? null : sideNo;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        if(association == null)
            this.association = null;
        else if (association.getName() == null)
            this.association = null;
        else
            this.association = association.getName().trim().isEmpty() ? null : association;
    }

    public String getBolo() {
        return bolo;
    }

    public void setBolo(String bolo) {
        this.bolo = bolo;
    }

    public String getLibre() {
        return libre;
    }

    public void setLibre(String libre) {
        this.libre = libre == null ? null : libre.isEmpty() ? null : libre;
    }

    public List<VehicleOwnership> getVehicleOwnerships() {
        return vehicleOwnerships;
    }

    public void setVehicleOwnerships(List<VehicleOwnership> vehicleOwnerships) {
        this.vehicleOwnerships = vehicleOwnerships;
    }
}
