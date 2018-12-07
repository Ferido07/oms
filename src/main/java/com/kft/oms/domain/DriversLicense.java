package com.kft.oms.domain;

import com.kft.oms.constants.LicenseType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
public class DriversLicense {

    @NotNull
    private Integer licenseNo;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private LicenseType licenseType;

    public Integer getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(Integer licenseNo) {
        this.licenseNo = licenseNo;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
