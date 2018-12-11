package com.kft.oms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CARGO_VEHICLE")
public class CargoVehicle extends Vehicle {

    private Integer loadInQuintals;

    public Integer getLoadInQuintals() {
        return loadInQuintals;
    }

    public void setLoadInQuintals(Integer loadInQuintals) {
        this.loadInQuintals = loadInQuintals;
    }
}
