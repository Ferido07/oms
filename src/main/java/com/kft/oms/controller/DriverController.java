package com.kft.oms.controller;

import com.kft.oms.model.DriverModel;
import com.kft.oms.service.DriverService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping({"", "/list"})
    public String getAllDrivers(Model model, Pageable pageable){
        model.addAttribute("driverModels", driverService.getAllAsDriverModel(pageable));
        return "offender/driver/list";
    }

    @GetMapping(params = "licenseNo")
    public String getDriverByLicenseNo(@RequestParam String licenseNo, Model model){
        model.addAttribute("searchResult",true);
        List<DriverModel> driverModels = driverService.findByDriversLicenseNoStartingWith(licenseNo);
        model.addAttribute("driverModels", driverModels);
        return "offender/driver/list";
    }
}
