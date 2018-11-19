package com.kft.oms.domain;

import com.kft.crud.domain.Person;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class VehicleOwner extends Person{

    @ManyToMany
    private List<Association> associations;

    @ManyToMany
    private List<Vehicle> vehicles;

    public List<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(List<Association> associations) {
        this.associations = associations;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
