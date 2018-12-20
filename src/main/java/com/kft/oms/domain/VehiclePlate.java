package com.kft.oms.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class VehiclePlate {
    //QuestionAnswered: what to do with A and B prefixes for plate code 2? I think its better to use string and use formatting control
    //private Integer plateNo;//5 digit number
    //@Pattern(regexp = "")
    @NotNull
    @Size(min = 5, max = 6)
    private String plateNo;

    @NotNull
    @Size(min = 2, max = 2)
    private String plateRegion;//aa, sp, or

    @NotNull
    @Min(1)
    @Max(5)
    private Short plateCode;

    @NotNull
    @Size(min = 2, max = 2)
    private String plateCountry = "Et";



    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getPlateRegion() {
        return plateRegion;
    }

    public void setPlateRegion(String plateRegion) {
        this.plateRegion = plateRegion;
    }

    public Short getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(Short plateCode) {
        this.plateCode = plateCode;
    }

    public String getPlateCountry() {
        return plateCountry;
    }

    public void setPlateCountry(String plateCountry) {
        this.plateCountry = plateCountry;
    }

    @Override
    public String toString() {
        return plateCode + "-" + plateNo + "-" + plateRegion; //+ "-" + plateCountry;
    }
}
