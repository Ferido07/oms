package com.kft.oms.model;

import javax.validation.constraints.*;

public class VehicleModel {

    private Integer id;

    private String type;

    @NotBlank
    //@Size(min=9, max=11)
    @Pattern(regexp = "[1-5]-[A-Z]?[0-9]{5}-[aA-zZ]{2}")//matches codes like 1-A67876-eT
    private String plateNo;

    @Size(max = 10)
    private String sideNo;

    private String bolo;

    @Size(max = 20)
    private String libre;

    @Max(100)
    private Integer seatingCapacity;

    private Integer loadInQuintals;

    private VehicleInstanceType vehicleInstanceType;

    private AssociationModel associationModel;

    @NotNull
    private PersonModel owner;

    private PersonModel owner2;

    private PersonModel owner3;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getSideNo() {
        return sideNo;
    }

    public void setSideNo(String sideNo) {
        this.sideNo = sideNo;
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

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getLoadInQuintals() {
        return loadInQuintals;
    }

    public void setLoadInQuintals(Integer loadInQuintals) {
        this.loadInQuintals = loadInQuintals;
    }

    public VehicleInstanceType getVehicleInstanceType() {
        return vehicleInstanceType;
    }

    public void setVehicleInstanceType(VehicleInstanceType vehicleInstanceType) {
        this.vehicleInstanceType = vehicleInstanceType;
    }

    public AssociationModel getAssociationModel() {
        return associationModel;
    }

    public void setAssociationModel(AssociationModel associationModel) {
        this.associationModel = associationModel;
    }

    public enum VehicleInstanceType{
        PUBLIC_TRANSPORT, CARGO_VEHICLE
    }

    public PersonModel getOwner() {
        return owner;
    }

    public void setOwner(PersonModel owner) {
        this.owner = owner;
    }

    public PersonModel getOwner2() {
        return owner2;
    }

    public void setOwner2(PersonModel owner2) {
        this.owner2 = owner2;
    }

    public PersonModel getOwner3() {
        return owner3;
    }

    public void setOwner3(PersonModel owner3) {
        this.owner3 = owner3;
    }
}
