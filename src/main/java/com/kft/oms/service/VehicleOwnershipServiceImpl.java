package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.domain.VehicleOwnership;
import com.kft.oms.repository.VehicleOwnershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleOwnershipServiceImpl extends CrudServiceImpl<VehicleOwnership,Integer,VehicleOwnershipRepository>
        implements VehicleOwnershipService {

    public VehicleOwnershipServiceImpl(VehicleOwnershipRepository repository) {
        super(repository);
    }

    @Override
    public Optional<VehicleOwnership> getByLibreNo(String libreNo) {
        return repository.getByLibreNo(libreNo);
    }

    @Override
    public List<VehicleOwnership> getByVehicle(Vehicle vehicle) {
        return repository.getByVehicle(vehicle);
    }
}
