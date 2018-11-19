package com.kft.oms.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ProofDocument {

    @Embedded
    private VehiclePlate vehiclePlate;

    @Embedded
    private DriversLicense driversLicense;

    private String bolo;

    private String libre;

    public VehiclePlate getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(VehiclePlate vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
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
}
