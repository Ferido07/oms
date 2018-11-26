package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.crud.domain.OffenderEntity;
import com.kft.oms.constants.OffenceStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Offence extends AbstractEntity {

    @NotNull
    private LocalDate offenceDate;
    @NotNull
    private LocalTime offenceTime;

    private String place;
    private String description;
    private String reportingLocation;
    @Enumerated(value = EnumType.STRING)
    private OffenceStatus status;

    private Long dispatchNo;

    //An offender can be Association or organization as noted in 9.3.6 and 9.3.8 of the rule book or a Person
    @NotNull
    @ManyToOne
    private OffenderEntity offender;

    private Integer penaltyAmount;

    @Embedded
    private ProofDocument proofDocument;

    @NotNull
    @ManyToMany
    private List<OffenceCode> offenceCodes;

    public LocalDate getOffenceDate() {
        return offenceDate;
    }

    public void setOffenceDate(LocalDate offenceDate) {
        this.offenceDate = offenceDate;
    }

    public LocalTime getOffenceTime() {
        return offenceTime;
    }

    public void setOffenceTime(LocalTime offenceTime) {
        this.offenceTime = offenceTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public ProofDocument getProofDocument() {
        return proofDocument;
    }

    public void setProofDocument(ProofDocument proofDocument) {
        this.proofDocument = proofDocument;
    }

    public Long getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(Long dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public Integer getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Integer penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public List<OffenceCode> getOffenceCodes() {
        return offenceCodes;
    }

    public void setOffenceCodes(List<OffenceCode> offenceCodes) {
        this.offenceCodes = offenceCodes;
    }
}
