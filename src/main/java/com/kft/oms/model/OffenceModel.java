package com.kft.oms.model;

import com.kft.oms.constants.OffenceStatus;
import com.kft.oms.constants.ProofDocument;
import com.kft.oms.domain.OffenceCode;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class OffenceModel {

    private Integer id;
    private Integer dispatchNo;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    @NotBlank
    @Size(max=255)
    private String location;

    @Size(max=255)
    private String description;

    @Size(max=255)
    private String reportingLocation;

    private OffenceStatus status = OffenceStatus.PENDING;

    private Integer penaltyAmount;

    private VehicleModel vehicleModel;
    private DriverModel driverModel;
    private PersonModel supervisor;

    private List<OffenceCode> offenceCodes;
    private Map<ProofDocument,Boolean> proofDocumentTaken;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(Integer dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public DriverModel getDriverModel() {
        return driverModel;
    }

    public void setDriverModel(DriverModel driverModel) {
        this.driverModel = driverModel;
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

    public PersonModel getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(PersonModel supervisor) {
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
