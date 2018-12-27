package com.kft.oms.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DriverModel extends PersonModel{

    @NotNull
    private Integer licenseNo;
    @NotBlank
    private String licenseType;


    public Integer getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(Integer licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }
}
