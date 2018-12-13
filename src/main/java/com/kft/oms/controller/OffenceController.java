package com.kft.oms.controller;

import com.kft.oms.domain.*;
import com.kft.oms.model.OffenceModel;
import com.kft.oms.service.OffenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/offence")
public class OffenceController {

    private final OffenceService offenceService;

    @Autowired
    public OffenceController(OffenceService offenceService) {
        this.offenceService = offenceService;
    }

    @RequestMapping({"","/list"})
    public String index(Model model){
        model.addAttribute("offences",offenceService.findAll());
        return "offence/list";
    }

    @RequestMapping("/{id}")
    public String getOffence(@PathVariable Integer id, Model model){
        Optional<Offence> offence = offenceService.findById(id);
        offence.ifPresent(offence1 -> model.addAttribute("offence", offence1));
        return "offence/show";
    }

    //Todo: create and pass a different model than offence object to the view. Maybe all controller actions could use a different model
    @RequestMapping("/create")
    public String createOffence(Model model){
        model.addAttribute("offenceModel",new OffenceModel());
        return "offence/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOrUpdate(OffenceModel offenceModel){
        Offence offence = new Offence();
        List<OffenceCode> offenceCodes = new ArrayList<>();
        OffenceCode offenceCode = new OffenceCode();
        offenceCode.setId(1);
        offenceCodes.add(offenceCode);
        offence.setOffenceCodes(offenceCodes);
        offence.setDate(offenceModel.getDate());
        offence.setTime(offenceModel.getTime());
        Driver driver = offenceModel.getDriver();
//        Driver driver = new Driver();
//        driver.setId(offenceModel.getDriver().getId());
//        if (driver.getId()==null)
//            driver.setId(1);
        offence.setOffender(driver);
        offence.setDriver(driver);
        //offence.setId();
        Offence savedOffence = offenceService.save(offence);
        return "redirect:/offence/" + savedOffence.getId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Offence> offenceOptional = offenceService.findById(id);

        offenceOptional.ifPresent(offence -> {
            if(offence.getOffender() instanceof Driver){
                Driver driver = ((Driver) offence.getOffender());
                OffenceModel offenceModel = new OffenceModel();
                offenceModel.setId(offence.getId());
                offenceModel.setDriver(driver);
                model.addAttribute("offenceModel", offenceModel);
            }
            else if (offence.getOffender() instanceof VehicleOwner){
                OffenceModel offenceModel = new OffenceModel();
                offenceModel.setId(offence.getId());
                offenceModel.setDriver(offence.getDriver());
                offenceModel.setVehicle(offence.getVehicle());
                model.addAttribute("offenceModel", offenceModel);
            }
        });

        return "offence/form";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        offenceService.deleteById(id);
        return "redirect:/offence";
    }
}
