package com.kft.oms.model;

import java.util.List;

public class VehicleOwnershipModel {
    private Integer id;
    private String libreNo;
    private Integer vehicleModelId;
    private List<PersonModel> personModelOwners;
    private int status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibreNo() {
        return libreNo;
    }

    public void setLibreNo(String libreNo) {
        this.libreNo = libreNo;
    }

    public Integer getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Integer vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public List<PersonModel> getPersonModelOwners() {
        return personModelOwners;
    }

    public void setPersonModelOwners(List<PersonModel> personModelOwners) {
        this.personModelOwners = personModelOwners;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}