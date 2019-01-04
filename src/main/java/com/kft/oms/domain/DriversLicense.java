package com.kft.oms.domain;

import com.kft.oms.constants.LicenseType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class DriversLicense {

    @NotBlank
    @Size(max = 10)
    private String licenseNo;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private LicenseType licenseType;


    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
