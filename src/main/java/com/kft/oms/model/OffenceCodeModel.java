package com.kft.oms.model;

import com.kft.oms.constants.OffenceRepetitionTracker;
import com.kft.oms.constants.OffenderType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OffenceCodeModel {

    private Integer id;

    // The next 2 are only added for convenience purposes to the user.
    //Header like 9.3.3
    @Size(max = 9)
    private String sectionHeaderLabel;

    //like አሽከርካሪ ረዳትን የሚያስጠይቁ that describes the section.
    private String sectionHeader;

    //Driver, Association, VehicleOwner,... maybe use an enum or interface or Abstract superclass
    //Canceled: maybe add a column or foreign key to reference the entity_type from OffenderEntity
    @NotNull
    private OffenderType offenderType;

    @NotNull
    private Short level;

    @NotNull
    private Integer penaltyAmount;

    //Not an int or short because numberLabel can contain 1.1, 1.2.3
    @Size(max = 5)
    private String numberLabel;

    @NotNull
    private String description;

    @NotNull
    private boolean offenceRepetitionConsidered = true;

    private OffenceRepetitionTracker offenceRepetitionTrackedBy = OffenceRepetitionTracker.DRIVER;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionHeaderLabel() {
        return sectionHeaderLabel;
    }

    public void setSectionHeaderLabel(String sectionHeaderLabel) {
        this.sectionHeaderLabel = sectionHeaderLabel;
    }

    public String getSectionHeader() {
        return sectionHeader;
    }

    public void setSectionHeader(String sectionHeader) {
        this.sectionHeader = sectionHeader;
    }

    public OffenderType getOffenderType() {
        return offenderType;
    }

    public void setOffenderType(OffenderType offenderType) {
        this.offenderType = offenderType;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
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
