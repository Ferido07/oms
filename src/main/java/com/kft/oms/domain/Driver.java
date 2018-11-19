package com.kft.oms.domain;

import com.kft.crud.domain.Person;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Driver extends Person {

    @Embedded
    private DriversLicense driversLicense;

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }
}
