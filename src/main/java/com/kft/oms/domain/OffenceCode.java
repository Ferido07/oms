package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"offenderType", "level", "penaltyAmount"}))
public class OffenceCode extends AbstractEntity{

    //Driver, Association, VehicleOwner,... maybe use an enum or interface or Abstract superclass
    @Column(unique = true)
    @NotNull
    private String offenderType;

    @NotNull
    private String level;

    @NotNull
    private Integer penaltyAmount;

    @NotNull
    private String description;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
