package com.kft.oms.model;

import java.util.List;

public class VehicleOwnershipModel {
    private Integer id;
    private String libreNo;
    private VehicleModel vehicleModel;
    private List<PersonModel> personModelOwners;


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

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public List<PersonModel> getPersonModelOwners() {
        return personModelOwners;
    }

    public void setPersonModelOwners(List<PersonModel> personModelOwners) {
        this.personModelOwners = personModelOwners;
    }
}
