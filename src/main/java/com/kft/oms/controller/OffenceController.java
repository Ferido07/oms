package com.kft.oms.controller;

import com.kft.oms.domain.*;
import com.kft.oms.model.OffenceModel;
import com.kft.oms.service.OffenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"","/list"})
    public String index(Model model){
        model.addAttribute("offenceModels", offenceService.getAllAsOffenceModel());
        return "offence/list";
    }

    @GetMapping("/{id}")
    public String getOffence(@PathVariable Integer id, Model model){
        Optional<OffenceModel> offenceModel = offenceService.findOffenceModelById(id);
        if(offenceModel.isPresent()){
            model.addAttribute("offenceModel", offenceModel.get());
            return "offence/show";
        }else{
            throw new OffenceNotFoundException();
        }
    }

    //Todo: create and pass a different model than offence object to the view. Maybe all controller actions could use a different model
    @GetMapping("/create")
    public String createOffence(Model model){
        model.addAttribute("offenceModel", new OffenceModel());
        return "offence/form";
    }

    @PostMapping("/create")
    public String createOrUpdate(OffenceModel offenceModel){

        List<OffenceCode> offenceCodes = new ArrayList<>();
        OffenceCode offenceCode = new OffenceCode();
        offenceCode.setId(1);
        offenceCodes.add(offenceCode);
        offenceModel.setOffenceCodes(offenceCodes);
        //todo: add a check to find out if vehicle owner and vehicle pass requirements and remove the code below
        offenceModel.setVehicle(null);

        OffenceModel savedOffence = offenceService.save(offenceModel);
        System.out.println("successfully persisted");
        return "redirect:/offence/" + savedOffence.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<OffenceModel> offenceModel = offenceService.findOffenceModelById(id);
        if(offenceModel.isPresent()){
            model.addAttribute("offenceModel", offenceModel.get());
            return "offence/form";
        }else{
            throw new OffenceNotFoundException();
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        offenceService.deleteById(id);
        return "redirect:/offence";
    }
}
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Offence not found")
class OffenceNotFoundException extends RuntimeException {
}
