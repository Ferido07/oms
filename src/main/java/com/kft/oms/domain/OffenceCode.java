package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import javax.persistence.*;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"section_id", "level", "penaltyAmount", "numberLabel"}))
public class OffenceCode extends AbstractEntity{

    @ManyToOne(optional = false)
    private OffenceCodeSection section;

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



    public OffenceCodeSection getSection() {
        return section;
    }

    public void setSection(OffenceCodeSection section) {
        this.section = section;
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
}
