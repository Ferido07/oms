package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Driver;
import com.kft.oms.model.DriverModel;
import com.kft.oms.repository.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DriverService extends CrudService<Driver,Integer,DriverRepository>{
    Optional<DriverModel> findByDriversLicenseNo(String licenseNo);
    List<DriverModel> findByDriversLicenseNoStartingWith(String licenseNo);
    Page<DriverModel> findByDriversLicenseNoStartingWith(String licenseNo, Pageable pageable);
    Page<DriverModel> getAllAsDriverModel(Pageable pageable);
}
