package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.oms.constants.OffenceRepetitionTracker;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"offenderType", "level", "penaltyAmount", "numberLabel"}))
public class OffenceCode extends AbstractEntity{

    //Driver, Association, VehicleOwner,... maybe use an enum or interface or Abstract superclass
    //Canceled: maybe add a column or foreign key to reference the entity_type from OffenderEntity
    @NotNull
    private String offenderType;

    @NotNull
    private String level;

    @NotNull
    private Integer penaltyAmount;

    private String numberLabel;

    @NotNull
    private String description;

    @NotNull
    private boolean offenceRepetitionConsidered = true;

    @Enumerated(EnumType.STRING)
    private OffenceRepetitionTracker offenceRepetitionTrackedBy = OffenceRepetitionTracker.DRIVER;


    public String getOffenderType() {
        return offenderType;
    }

    public void setOffenderType(String offenderType) {
        this.offenderType = offenderType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Integer penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getNumberLabel() {
        return numberLabel;
    }

    public void setNumberLabel(String numberLabel) {
        this.numberLabel = numberLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOffenceRepetitionConsidered() {
        return offenceRepetitionConsidered;
    }

    public void setOffenceRepetitionConsidered(boolean offenceRepetitionConsidered) {
        this.offenceRepetitionConsidered = offenceRepetitionConsidered;
    }

    public OffenceRepetitionTracker getOffenceRepetitionTrackedBy() {
        return offenceRepetitionTrackedBy;
    }

    public void setOffenceRepetitionTrackedBy(OffenceRepetitionTracker offenceRepetitionTrackedBy) {
        this.offenceRepetitionTrackedBy = offenceRepetitionTrackedBy;
    }
}
