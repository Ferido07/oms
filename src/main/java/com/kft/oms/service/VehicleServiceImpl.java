package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.config.Mapper;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.model.VehicleModel;
import com.kft.oms.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl extends CrudServiceImpl<Vehicle,Integer,VehicleRepository> implements VehicleService {

    private final Mapper mapper;

    public VehicleServiceImpl(VehicleRepository repository, Mapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public Optional<VehicleModel> findByPlateNo(String plateNo) {
        Optional<Vehicle> vehicleByPlate = repository.findByPlateNo(plateNo);
        return vehicleByPlate.map(vehicle -> mapper.map(vehicle,VehicleModel.class));
    }

    @Override
    public List<VehicleModel> findByPlateNoStartingWith(String plateNo) {
        List<Vehicle> vehicles = repository.findAllByPlateNoStartingWith(plateNo);
        return mapper.mapAsList(vehicles, VehicleModel.class);
    }

    @Override
    public Page<VehicleModel> findByPlateNoStartingWith(String plateNo, Pageable pageable) {
        Page<Vehicle> vehicles = repository.findAllByPlateNoStartingWith(plateNo, pageable);
        return vehicles.map(vehicle -> mapper.map(vehicle, VehicleModel.class));
    }

    @Override
    public Optional<VehicleModel> findBySideNo(String sideNo) {
        Optional<Vehicle> vehicleBySideNo = repository.findVehicleBySideNo(sideNo);
        return vehicleBySideNo.map(vehicle -> mapper.map(vehicle, VehicleModel.class));
    }

    @Override
    public Page<VehicleModel> getAllAsVehicleModel(Pageable pageable) {
        Page<Vehicle> vehicles = repository.findAll(pageable);
        return vehicles.map(vehicle -> mapper.map(vehicle, VehicleModel.class));
    }
}
