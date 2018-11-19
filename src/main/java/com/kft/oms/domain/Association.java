package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Association extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "association")
    private List<Vehicle> vehicles;

    @ManyToMany
    private List<VehicleOwner> vehicleOwners;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<VehicleOwner> getVehicleOwners() {
        return vehicleOwners;
    }

    public void setVehicleOwners(List<VehicleOwner> vehicleOwners) {
        this.vehicleOwners = vehicleOwners;
    }
}
