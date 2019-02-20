package com.kft.oms.controller.api;

import com.kft.oms.model.VehicleModel;
import com.kft.oms.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("apiVehicleController")
@RequestMapping("/api/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping(value = "/plate/{plateNo}")
    public Optional<VehicleModel> getVehicleByPlateNo(@PathVariable String plateNo){
        return vehicleService.findByPlateNo(plateNo);
    }


    @GetMapping
    public List<String> getPlateNosStartingWith(@RequestParam String plateNo){
        return vehicleService.findByPlateNoStartingWith(plateNo)
                .stream()
                .map(VehicleModel::getPlateNo)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/sideNo/{sideNo}")
    public Optional<VehicleModel> getVehicleBySideNo(@PathVariable String sideNo){
        return vehicleService.findBySideNo(sideNo);
    }
}
