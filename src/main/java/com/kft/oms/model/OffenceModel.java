package com.kft.oms.model;

import com.kft.crud.domain.Person;
import com.kft.oms.constants.OffenceStatus;
import com.kft.oms.constants.ProofDocument;
import com.kft.oms.domain.Driver;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.domain.Vehicle;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class OffenceModel {

    private Vehicle vehicle;
    private Driver driver;

    private Integer dispatchNo;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String description;
    private String reportingLocation;
    private OffenceStatus status;
    private List<OffenceCode> offenceCodes;
    private Map<ProofDocument,Boolean> proofDocumentTaken;
    private Integer penaltyAmount;

    //Done :add the person in offence who is submitting the charge in domain the name could be different than supervisor
    private Person supervisor;



    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(Integer dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driverModel) {
        this.driver = driverModel;
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

    public Person getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Person supervisor) {
        this.supervisor = supervisor;
    }

    public OffenceStatus getStatus() {
        return status;
    }

    public void setStatus(OffenceStatus status) {
        this.status = status;
    }

    public List<OffenceCode> getOffenceCodes() {
        return offenceCodes;
    }

    public void setOffenceCodes(List<OffenceCode> offenceCodes) {
        this.offenceCodes = offenceCodes;
    }

    public Map<ProofDocument, Boolean> getProofDocumentTaken() {
        return proofDocumentTaken;
    }

    public void setProofDocumentTaken(Map<ProofDocument, Boolean> proofDocumentTaken) {
        this.proofDocumentTaken = proofDocumentTaken;
    }

    public Integer getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Integer penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
}
