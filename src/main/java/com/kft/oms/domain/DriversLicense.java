package com.kft.oms.domain;

import com.kft.oms.constants.LicenseType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
class DriversLicense {

    private Integer licenseNo;
    @Enumerated(value = EnumType.STRING)
    private LicenseType licenseType;
}
