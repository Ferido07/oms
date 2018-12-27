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
    @Pattern(regexp = "[1-5]-[A-Z]?[0-9]{5}-[aA-zZ]{2}")
    private String plateNo;

    //Done: check uniqueness and add if necessary
    //@Max(99999)
    @Column(unique = true)
    private Integer sideNo;

    @ManyToMany(mappedBy = "vehicles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<VehicleOwner> owners;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Association association;

    //TODO: Add constraint for bolo and libre
    private String bolo;

    private String libre;



    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
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
        this.libre = libre;
    }
}
