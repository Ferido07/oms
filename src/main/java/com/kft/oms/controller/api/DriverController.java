package com.kft.oms.controller.api;

import com.kft.oms.model.DriverModel;
import com.kft.oms.service.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("apiDriverController")
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/{licenseNo}")
    public Optional<DriverModel> getDriverByLicenseNo(@PathVariable("licenseNo") String licenseNo){
        return driverService.findByDriversLicenseNo(licenseNo);
    }

    @GetMapping
    public List<String> getDriversLicenseNosStartingWith(@RequestParam String licenseNo){
        return driverService.findByDriversLicenseNoStartingWith(licenseNo)
                .stream()
                .map(DriverModel::getLicenseNo)
                .collect(Collectors.toList());
    }
}
