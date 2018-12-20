package com.kft.oms.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VehicleModel {

    private Integer id;

    private String type;

    @Size(max=9)
    @Pattern(regexp = "[1-5]-[A-Z]?[0-9]{5}-[aA-zZ]{2}")//matches codes like 1-A67876-eT
    private String plateNo;

    @Max(9999)
    private Integer sideNo;

    private String bolo;

    private String libre;

    private Integer seatingCapacity;

    private Integer loadInQuintals;

    private VehicleInstanceType vehicleInstanceType;


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

    public Integer getSideNo() {
        return sideNo;
    }

    public void setSideNo(Integer sideNo) {
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

    public enum VehicleInstanceType{
        PUBLIC_TRANSPORT, CARGO_VEHICLE
    }
}
