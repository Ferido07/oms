package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.oms.constants.OffenceRepetitionTracker;
import com.kft.oms.constants.OffenderType;
import javax.persistence.*;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"sectionHeaderLabel", "level", "penaltyAmount", "numberLabel"}))
public class OffenceCode extends AbstractEntity{

    // The next 2 are only added for convenience purposes to the user.
    //Header like 9.3.3
    @Column(length = 9, nullable = false)
    private String sectionHeaderLabel;

    //like አሽከርካሪ ረዳትን የሚያስጠይቁ that describes the section.
    private String sectionHeader;

    //Driver, Association, VehicleOwner,... maybe use an enum or interface or Abstract superclass
    //Canceled: maybe add a column or foreign key to reference the entity_type from OffenderEntity
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OffenderType offenderType;

    @Column(nullable = false)
    private Short level;

    @Column(nullable = false)
    private Integer penaltyAmount;

    //Not an int or short because numberLabel can contain 1.1, 1.2.3
    @Column(nullable = false, length = 5)
    private String numberLabel;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean offenceRepetitionConsidered = true;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private OffenceRepetitionTracker offenceRepetitionTrackedBy = OffenceRepetitionTracker.DRIVER;




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
