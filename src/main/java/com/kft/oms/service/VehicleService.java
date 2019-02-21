package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.model.VehicleModel;
import com.kft.oms.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VehicleService extends CrudService<Vehicle,Integer,VehicleRepository> {

    Optional<VehicleModel> findByPlateNo(String plateNo);
    List<VehicleModel> findByPlateNoStartingWith(String plateNo);
    Page<VehicleModel> findByPlateNoStartingWith(String plateNo, Pageable pageable);
    Optional<VehicleModel> findBySideNo(String sideNo);
    Page<VehicleModel> getAllAsVehicleModel(Pageable pageable);
}
