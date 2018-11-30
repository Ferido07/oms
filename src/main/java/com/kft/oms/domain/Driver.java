package com.kft.oms.domain;

import com.kft.crud.domain.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "DRIVER")
public class Driver extends Person {

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "licenseNo", column = @Column(name = "licenseNo", nullable = false, unique = true)),
            @AttributeOverride(name = "licenseType", column = @Column(name = "licenseType", nullable = false, length = 10))
    })
    private DriversLicense driversLicense;

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }
}
