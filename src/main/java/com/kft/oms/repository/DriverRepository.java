package com.kft.oms.repository;

import com.kft.oms.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
    Optional<Driver> findByDriversLicenseLicenseNo(String licenseNo);
    List<Driver> findAllByDriversLicenseLicenseNoStartingWith(String licenseNo);
}
