package com.kft.oms.controller;

import com.kft.oms.model.DriverModel;
import com.kft.oms.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/driver")
public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @ResponseBody
    @GetMapping("/{licenseNo}")
    public Optional<DriverModel> findDriverByLicenseNo(@PathVariable Integer licenseNo){
        return driverService.findByDriversLicenseNo(licenseNo);
    }
}
