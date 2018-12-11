package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.crud.domain.Organization;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("ASSOCIATION")
public class Association extends Organization {

    @OneToMany(mappedBy = "association")
    private List<Vehicle> vehicles;

    @ManyToMany
    private List<VehicleOwner> vehicleOwners;


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
