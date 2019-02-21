package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.config.Mapper;
import com.kft.oms.domain.Driver;
import com.kft.oms.model.DriverModel;
import com.kft.oms.repository.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl extends CrudServiceImpl<Driver,Integer,DriverRepository> implements DriverService {

    private final Mapper mapper;

    public DriverServiceImpl(DriverRepository repository, Mapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public Optional<DriverModel> findByDriversLicenseNo(String licenseNo) {
        Optional<Driver> driverOptional = repository.findByDriversLicenseLicenseNo(licenseNo);
        return driverOptional.map(driver -> mapper.map(driver, DriverModel.class));
    }

    @Override
    public List<DriverModel> findByDriversLicenseNoStartingWith(String licenseNo) {
        List<Driver> drivers = repository.findAllByDriversLicenseLicenseNoStartingWith(licenseNo);
        return mapper.mapAsList(drivers,DriverModel.class);
    }

    @Override
    public Page<DriverModel> findByDriversLicenseNoStartingWith(String licenseNo, Pageable pageable) {
        Page<Driver> drivers = repository.findAllByDriversLicenseLicenseNoStartingWith(licenseNo, pageable);
        return drivers.map(driver -> mapper.map(driver, DriverModel.class));
    }

    @Override
    public Page<DriverModel> getAllAsDriverModel(Pageable pageable) {
         Page<Driver> drivers = repository.findAll(pageable);
         return drivers.map(driver -> mapper.map(driver, DriverModel.class));
    }
}
