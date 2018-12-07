package com.kft.oms.model;

import com.kft.oms.domain.Driver;
import com.kft.oms.domain.Vehicle;

import java.time.LocalDate;
import java.time.LocalTime;

public class OffenceModel {

    private Vehicle vehicle;

    private String vehicleOwnerFirstName;
    private String vehicleOwnerMiddleName;
    private String vehicleOwnerLastName;


    private Driver driver;


    private Integer dispatchNo;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String description;
    private String reportingLocation;

    //Todo :add the person in offence who is submitting the charge in domain the name could be different than supervisor
    private String supervisorFirstName;
    private String supervisorMiddleName;
    private String supervisorLastName;



    public String getVehicleOwnerFirstName() {
        return vehicleOwnerFirstName;
    }

    public void setVehicleOwnerFirstName(String vehicleOwnerFirstName) {
        this.vehicleOwnerFirstName = vehicleOwnerFirstName;
    }

    public String getVehicleOwnerMiddleName() {
        return vehicleOwnerMiddleName;
    }

    public void setVehicleOwnerMiddleName(String vehicleOwnerMiddleName) {
        this.vehicleOwnerMiddleName = vehicleOwnerMiddleName;
    }

    public String getVehicleOwnerLastName() {
        return vehicleOwnerLastName;
    }

    public void setVehicleOwnerLastName(String vehicleOwnerLastName) {
        this.vehicleOwnerLastName = vehicleOwnerLastName;
    }

/*    public String getVehicleAssociation() {
        return vehicleAssociation;
    }

    public void setVehicleAssociation(String vehicleAssociation) {
        this.vehicleAssociation = vehicleAssociation;
    }

    public Integer getVehicleSideNo() {
        return vehicleSideNo;
    }

    public void setVehicleSideNo(Integer vehicleSideNo) {
        this.vehicleSideNo = vehicleSideNo;
    }*/

    public Integer getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(Integer dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportingLocation() {
        return reportingLocation;
    }

    public void setReportingLocation(String reportingLocation) {
        this.reportingLocation = reportingLocation;
    }

    public String getSupervisorFirstName() {
        return supervisorFirstName;
    }

    public void setSupervisorFirstName(String supervisorFirstName) {
        this.supervisorFirstName = supervisorFirstName;
    }

    public String getSupervisorMiddleName() {
        return supervisorMiddleName;
    }

    public void setSupervisorMiddleName(String supervisorMiddleName) {
        this.supervisorMiddleName = supervisorMiddleName;
    }

    public String getSupervisorLastName() {
        return supervisorLastName;
    }

    public void setSupervisorLastName(String supervisorLastName) {
        this.supervisorLastName = supervisorLastName;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driverModel) {
        this.driver = driverModel;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
