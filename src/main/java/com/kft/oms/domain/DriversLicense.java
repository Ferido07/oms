package com.kft.oms.domain;

import com.kft.oms.constants.LicenseType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
class DriversLicense {

    @NotNull
    private Integer licenseNo;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private LicenseType licenseType;
}
