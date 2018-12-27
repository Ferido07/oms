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
    @RequestMapping(value = "/plate")
    public List<VehicleModel> getVehiclesByPlateNoStartingWith(@RequestParam("term") String plateNo){
        return vehicleService.findByPlateNoStartingWith(plateNo);
    }

    @ResponseBody
    @RequestMapping(value = "/sideNo/{sideNo}")
    public Optional<VehicleModel> getVehiclesBySideNo(@PathVariable Integer sideNo){
        return vehicleService.findBySideNo(sideNo);
    }
}
