package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"plateNo", "plateRegion", "plateCode", "plateCountry"}))
@DiscriminatorColumn(name = "vehicle_type")
public class Vehicle extends AbstractEntity {

    //stands for types like vitz, landcruser,..
    private String type;

//    NOTE: All the attribute overrides are required otherwise jpa will not create a unique constraint on embeddable...
//      type or implement the other constraints which are also defined in VehiclePlate Embeddable class
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "plateNo", column = @Column(name = "plateNo", nullable = false, length = 6) ),
            @AttributeOverride(name = "plateRegion", column = @Column(name = "plateRegion", nullable = false, length = 2) ),
            @AttributeOverride(name = "plateCode", column = @Column(name = "plateCode", nullable = false) ),
            @AttributeOverride(name = "plateCountry", column = @Column(name = "plateCountry", nullable = false, length = 2) )
    })
    private VehiclePlate plate;

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
