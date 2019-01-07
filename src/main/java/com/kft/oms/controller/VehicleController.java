package com.kft.oms.controller;

import com.kft.oms.model.VehicleModel;
import com.kft.oms.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @ResponseBody
    @RequestMapping(value = "/plate/{plateNo}")
    public Optional<VehicleModel> getVehicleByPlateNo(@PathVariable String plateNo){
        return vehicleService.findByPlateNo(plateNo);
    }

    @ResponseBody
    @RequestMapping
    public List<String> getPlateNosStartingWith(@RequestParam String plateNo){
        return vehicleService.findByPlateNoStartingWith(plateNo)
                .stream()
                .map(VehicleModel::getPlateNo)
                .collect(Collectors.toList());
    }

    @ResponseBody
    @RequestMapping(value = "/sideNo/{sideNo}")
    public Optional<VehicleModel> getVehicleBySideNo(@PathVariable String sideNo){
        return vehicleService.findBySideNo(sideNo);
    }
}
