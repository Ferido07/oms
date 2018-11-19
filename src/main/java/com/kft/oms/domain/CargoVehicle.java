package com.kft.oms.domain;

import javax.persistence.Entity;

@Entity
public class CargoVehicle extends Vehicle {

    private Integer loadInQuintals;

    public Integer getLoadInQuintals() {
        return loadInQuintals;
    }

    public void setLoadInQuintals(Integer loadInQuintals) {
        this.loadInQuintals = loadInQuintals;
    }
}
