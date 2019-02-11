package com.kft.oms.model;

import com.kft.oms.constants.OffenceStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OffenceModel {

    private Integer id;

    @Size(max = 12)
    private String ticketNo;

    @Size(max = 12)
    private String dispatchNo;

    @Min(0)
    private Integer tariff;

    @Size(max = 255)
    private String start;

    @Size(max = 255)
    private String destination;

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

    @Valid
    private VehicleModel vehicleModel;

    @Valid
    private DriverModel driverModel;

    @Valid
    private PersonModel supervisor;

    @Valid
    @NotEmpty
    private List<OffenceCodeModel> offenceCodeModels;

    private boolean isDriversLicenseTaken;
    @Size(max = 10)
    private String driversLicenseTakenNo;

    private boolean isVehiclePlateTaken;
    @Size(max = 14)
    private String vehiclePlateTakenNo;

    private boolean isVehicleBoloTaken;
    private String vehicleBoloTakenNo;

    private boolean isLibreTaken;
    @Size(max = 20)
    private String libreTakenNo;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public Integer getTariff() {
        return tariff;
    }

    public void setTariff(Integer tariff) {
        this.tariff = tariff;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public List<OffenceCodeModel> getOffenceCodeModels() {
        return offenceCodeModels;
    }

    public void setOffenceCodeModels(List<OffenceCodeModel> offenceCodeModels) {
        this.offenceCodeModels = offenceCodeModels;
    }

    public boolean isDriversLicenseTaken() {
        return isDriversLicenseTaken;
    }

    public void setDriversLicenseTaken(boolean driversLicenseTaken) {
        isDriversLicenseTaken = driversLicenseTaken;
    }

    public boolean isVehiclePlateTaken() {
        return isVehiclePlateTaken;
    }

    public void setVehiclePlateTaken(boolean vehiclePlateTaken) {
        isVehiclePlateTaken = vehiclePlateTaken;
    }

    public boolean isVehicleBoloTaken() {
        return isVehicleBoloTaken;
    }

    public void setVehicleBoloTaken(boolean vehicleBoloTaken) {
        isVehicleBoloTaken = vehicleBoloTaken;
    }

    public boolean isLibreTaken() {
        return isLibreTaken;
    }

    public void setLibreTaken(boolean libreTaken) {
        isLibreTaken = libreTaken;
    }

    public Integer getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Integer penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getDriversLicenseTakenNo() {
        return driversLicenseTakenNo;
    }

    public void setDriversLicenseTakenNo(String driversLicenseTakenNo) {
        this.driversLicenseTakenNo = driversLicenseTakenNo;
    }

    public String getVehiclePlateTakenNo() {
        return vehiclePlateTakenNo;
    }

    public void setVehiclePlateTakenNo(String vehiclePlateTakenNo) {
        this.vehiclePlateTakenNo = vehiclePlateTakenNo;
    }

    public String getVehicleBoloTakenNo() {
        return vehicleBoloTakenNo;
    }

    public void setVehicleBoloTakenNo(String vehicleBoloTakenNo) {
        this.vehicleBoloTakenNo = vehicleBoloTakenNo;
    }

    public String getLibreTakenNo() {
        return libreTakenNo;
    }

    public void setLibreTakenNo(String libreTakenNo) {
        this.libreTakenNo = libreTakenNo;
    }
}
