package com.kft.oms.controller;

import com.kft.oms.exceptions.NotFoundException;
import com.kft.oms.model.OffenceCodeModel;
import com.kft.oms.model.OffenceModel;
import com.kft.oms.service.OffenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
            throw new NotFoundException("Offence not found");
        }
    }

    @GetMapping("/create")
    public String createOffence(@ModelAttribute OffenceModel offenceModel){
        if(offenceModel.getOffenceCodeModels() == null) {
            offenceModel.setOffenceCodeModels(new ArrayList<>());
            offenceModel.getOffenceCodeModels().add(new OffenceCodeModel());
        }
        return "offence/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<OffenceModel> offenceModel = offenceService.findOffenceModelById(id);
        if(offenceModel.isPresent()){
            model.addAttribute("offenceModel", offenceModel.get());
            return "offence/form";
        }else{
            throw new NotFoundException("Offence not found");
        }
    }

    @PostMapping("/create")
    public String createOrUpdate(@Valid @ModelAttribute OffenceModel offenceModel, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            OffenceModel savedOffence = offenceService.save(offenceModel);
            return "redirect:/offence/" + savedOffence.getId();
        } else {
            return "offence/form";
        }
    }

    @RequestMapping(value = "/create", params = {"addOffenceCodeModel"})
    public String addOffenceCodeModel(OffenceModel offenceModel){
        if(offenceModel.getOffenceCodeModels() == null)
            offenceModel.setOffenceCodeModels(new ArrayList<>());
        offenceModel.getOffenceCodeModels().add(new OffenceCodeModel());
        return "offence/form";
    }

    @RequestMapping(value = "/create", params = {"removeOffenceCodeModel"})
    public String removeOffenceCodeModel(OffenceModel offenceModel, HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("removeOffenceCodeModel"));
        offenceModel.getOffenceCodeModels().remove(id.intValue());
        return "offence/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        offenceService.deleteById(id);
        return "redirect:/offence";
    }

    @ResponseBody
    @GetMapping("/offender/{offenderId}")
    public List<OffenceModel> getOffencesByOffender(@PathVariable Integer offenderId){
        return offenceService.getAllOffencesByOffenderId(offenderId);
    }

    @ResponseBody
    @GetMapping("/offender/{offenderId}/code/{offenceCodeId}")
    public List<OffenceModel> getOffences(@PathVariable Integer offenderId, @PathVariable Integer offenceCodeId){
        return offenceService.getAllOffencesByOffenderIdAndOffenceCodeId(offenderId, offenceCodeId);
    }


    @GetMapping("/{offenceId}/code/{offenceCodeId}")
    public String getRecordOffencesForAnOffenceAndOffenceCode(@PathVariable Integer offenceId,@PathVariable Integer offenceCodeId, Model model){
        List<OffenceModel> offenceModels = offenceService.getRecordOffencesForOffenceAndOffenceCode(offenceId, offenceCodeId);
        model.addAttribute("offenceModels",offenceModels);
        return "offence/list";
    }

    @GetMapping("/{id}/status")
    public String status(@PathVariable("id") Integer offenceId, Model model){
        Optional<OffenceModel> offenceModel = offenceService.findOffenceModelById(offenceId);
        if(offenceModel.isPresent()){
            model.addAttribute("offenceModel", offenceModel.get());
            return "offence/status";
        }else{
            throw new NotFoundException("Offence not found");
        }
    }

    @PostMapping("/{id}/clear")
    public String clearStatus(@PathVariable("id") Integer offenceId, Model model){
         model.addAttribute("offenceModel", offenceService.clearStatus(offenceId));
         return "offence/status";
    }
}
