package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.crud.domain.Organization;
import com.kft.crud.domain.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@DiscriminatorValue(value = "VEHICLE_OWNERSHIP")
public class VehicleOwnership extends AbstractEntity{

    @NotEmpty
    @Column(unique = true, length = 30, nullable = false)
    private String libreNo;

    @NotNull
    @ManyToOne(optional = false)
    private Vehicle vehicle;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Person> personOwners;

    @ManyToMany
    private List<Organization> organizationOwners;

    /**
     * Shows the status of the ownership.
     * 0 means inactive,
     * 1 means active,
     * other values are currently not used.
     * But maybe used in the future to mean different situations like
     * being under restriction(legal restrictions possibly) or the car being totalled.
     * */
    private int status;

    /**
     * Shows which one of the properties (
     * */
    @NotNull
    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private OwnerType ownerType;


    public VehicleOwnership() {
        this.ownerType = OwnerType.PERSON;
    }

    public VehicleOwnership(String libreNo, Vehicle vehicle, OwnerType ownerType) {
        this.libreNo = libreNo;
        this.vehicle = vehicle;
        this.ownerType = ownerType;
    }


    public String getLibreNo() {
        return libreNo;
    }

    public void setLibreNo(String libreNo) {
        this.libreNo = libreNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Person> getPersonOwners() {
        return personOwners;
    }

    public void setPersonOwners(List<Person> personOwners) {
        this.personOwners = personOwners;
    }

    public List<Organization> getOrganizationOwners() {
        return organizationOwners;
    }

    public void setOrganizationOwners(List<Organization> organizationOwners) {
        this.organizationOwners = organizationOwners;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    enum OwnerType{
        PERSON, ORGANIZATION
    }

    //todo: add a pre persist check to check all the ownerships with the same vehicle and sets the new one as active and all the others as inactive
    //then maybe update the libre of the vehicle or leave it to be edited at a later time by users when they add offence
    //actually better update it here to have data integrity and when the users update through the ui they will be creating
    //new instance of this class nad hence it must be updated here.
    //or implement that in vehicle since vehicle is the parent
}
