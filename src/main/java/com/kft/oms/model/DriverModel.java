package com.kft.oms.model;


public class DriverModel extends PersonModel{

    private Integer licenseNo;
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
