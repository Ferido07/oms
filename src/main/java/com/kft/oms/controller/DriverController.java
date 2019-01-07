package com.kft.oms.controller;

import com.kft.oms.model.DriverModel;
import com.kft.oms.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/driver")
public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @ResponseBody
    @GetMapping("/{licenseNo}")
    public Optional<DriverModel> findDriverByLicenseNo(@PathVariable String licenseNo){
        return driverService.findByDriversLicenseNo(licenseNo);
    }

    @GetMapping
    @ResponseBody
    public List<String> getDriversLicenseNosStartingWith(@RequestParam String licenseNo){
        return driverService.findByDriversLicenseNoStartingWith(licenseNo)
                .stream()
                .map(DriverModel::getLicenseNo)
                .collect(Collectors.toList());
    }
}
