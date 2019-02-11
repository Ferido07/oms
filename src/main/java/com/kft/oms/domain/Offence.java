package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.crud.domain.OffenderEntity;
import com.kft.crud.domain.Person;
import com.kft.oms.constants.OffenceStatus;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Offence extends AbstractEntity {

    @Column(unique = true, length = 12)
    private String ticketNo;

    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotNull
    private String location;
    private String description;
    private String reportingLocation;
    @Enumerated(value = EnumType.STRING)
    private OffenceStatus status = OffenceStatus.PENDING;
    @Size(max = 12)
    private String dispatchNo;

    private Integer tariff;

    private String start;

    private String destination;

    //An offender can be Association or organization as noted in 9.3.6 and 9.3.8 of the rule book or a Person
    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private OffenderEntity offender;
    private Integer penaltyAmount;
    @NotNull
    @ManyToMany
    private Set<OffenceCode> offenceCodes;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vehicle vehicle;

    private boolean isDriversLicenseTaken;
    @Size(max = 10)
    private String driversLicenseTakenNo;

    private boolean isVehiclePlateTaken;
    @Size(max = 14)
    @Pattern(regexp = "[1-5]-[A-Z]?[0-9]{5}-[A-Z]{2}")
    private String vehiclePlateTakenNo;

    private boolean isVehicleBoloTaken;
    private String vehicleBoloTakenNo;

    private boolean isLibreTaken;
    @Size(max = 30)
    private String libreTakenNo;
    /*Supervisor is the person who submits the charges according to the requirements it can be either the terminal
      supervisor or traffic police
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Person supervisor;
    /* NOTE: On driver entity in offence while there is also offender entity
      *  Driver is included because it is present in their manual offence registration form. The offenderEntity can be
      *  a driver but this driver property serves for data keeping purposes. Their typical offence  code
      *  9.3.1. level 2 2000 birr no 1 charges the vehicle owner which in this entity would be the offenderEntity
      *  and their form also records the driver so this driver property is needed and can have many uses. Even though
      *  the drivers are not those punished in this specific offence code they are the ones who actually commit the
      *  offence and should be recorded to know which drivers are violating more often. Other Entity types like
      *  association may be added when needed if the forms for other specialized offence types of offences require it.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Driver driver;


    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo == null ? null : ticketNo.isEmpty() ? null : ticketNo;
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

    public OffenceStatus getStatus() {
        return status;
    }

    public void setStatus(OffenceStatus status) {
        this.status = status;
    }

    public OffenderEntity getOffender() {
        return offender;
    }

    public void setOffender(OffenderEntity offender) {
        this.offender = offender;
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

    public Integer getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Integer penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public Set<OffenceCode> getOffenceCodes() {
        return offenceCodes;
    }

    public void setOffenceCodes(Set<OffenceCode> offenceCodes) {
        this.offenceCodes = offenceCodes;
    }

    public boolean isDriversLicenseTaken() {
        return isDriversLicenseTaken;
    }

    public void setDriversLicenseTaken(boolean driversLicenseTaken) {
        isDriversLicenseTaken = driversLicenseTaken;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public String getDriversLicenseTakenNo() {
        return driversLicenseTakenNo;
    }

    public void setDriversLicenseTakenNo(String driversLicenseTakenNo) {
        this.driversLicenseTakenNo = driversLicenseTakenNo == null ? null : driversLicenseTakenNo.isEmpty() ? null : driversLicenseTakenNo;
        isDriversLicenseTaken = this.driversLicenseTakenNo != null;
    }

    public String getVehiclePlateTakenNo() {
        return vehiclePlateTakenNo;
    }

    public void setVehiclePlateTakenNo(String vehiclePlateTakenNo) {
        this.vehiclePlateTakenNo = vehiclePlateTakenNo == null ? null : vehiclePlateTakenNo.isEmpty() ? null : vehiclePlateTakenNo;
        isVehiclePlateTaken = this.vehiclePlateTakenNo != null;
    }

    public String getVehicleBoloTakenNo() {
        return vehicleBoloTakenNo;
    }

    public void setVehicleBoloTakenNo(String vehicleBoloTakenNo) {
        this.vehicleBoloTakenNo = vehicleBoloTakenNo == null ? null : vehicleBoloTakenNo.isEmpty()? null : vehicleBoloTakenNo;
        isVehicleBoloTaken = this.vehicleBoloTakenNo != null;
    }

    public String getLibreTakenNo() {
        return libreTakenNo;
    }

    public void setLibreTakenNo(String libreTakenNo) {
        this.libreTakenNo = libreTakenNo == null ? null : libreTakenNo.isEmpty() ? null : libreTakenNo;
        isLibreTaken = this.libreTakenNo != null;
    }

    public Person getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Person supervisor) {
        this.supervisor = supervisor;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
