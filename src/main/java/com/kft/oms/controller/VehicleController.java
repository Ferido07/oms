package com.kft.oms.controller;

import com.kft.oms.service.VehicleService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping({"", "/list"})
    public String getAllDrivers(Model model, Pageable pageable){
        model.addAttribute("vehicleModels", vehicleService.getAllAsVehicleModel(pageable));
        return "vehicle/list";
    }

    @GetMapping(params = "plateNo")
    public String getDriverByLicenseNo(@RequestParam String plateNo, Model model, Pageable pageable){
        model.addAttribute("searchResult",true);
        model.addAttribute("vehicleModels", vehicleService.findByPlateNoStartingWith(plateNo, pageable));
        return "vehicle/list";
    }
}
