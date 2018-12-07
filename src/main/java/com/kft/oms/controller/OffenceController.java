package com.kft.oms.controller;

import com.kft.oms.config.Mapper;
import com.kft.oms.domain.Driver;
import com.kft.oms.domain.Offence;
import com.kft.oms.model.OffenceModel;
import com.kft.oms.service.OffenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Optional;

@Controller
@RequestMapping("/offence")
public class OffenceController {

    private final OffenceService offenceService;

    private final Mapper mapper;

    @Autowired
    public OffenceController(OffenceService offenceService, Mapper mapper) {
        this.offenceService = offenceService;
        this.mapper = mapper;
    }

    @RequestMapping({"","/list"})
    public String index(Model model){
        model.addAttribute("offences",offenceService.findAll());
        return "offence/list";
    }

    @RequestMapping("/{id}")
    public String getOffence(@PathVariable Integer id, Model model){
        Optional<Offence> offence = offenceService.findById(id);
        offence.ifPresent(offence1 -> {
            OffenceModel offenceModel = mapper.map(offence1, OffenceModel.class);
            model.addAttribute("offence", offenceModel);
        });
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
        Offence offence = mapper.map(offenceModel, Offence.class);
        Offence savedOffence = offenceService.save(offence);
        return "redirect:/offence/" + savedOffence.getId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Offence> offence = offenceService.findById(id);

        offence.ifPresent(offence1 -> {
            OffenceModel offenceModel = mapper.map(offence1, OffenceModel.class);
            if(offence1.getOffender() instanceof Driver){
                offenceModel.setDriver((Driver)offence1.getOffender());
            }
            model.addAttribute("offenceModel", offenceModel);
        } );
        return "offence/form";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        offenceService.delete(id);
        return "redirect:/offence";
    }
}
