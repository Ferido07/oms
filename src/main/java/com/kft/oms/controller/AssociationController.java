package com.kft.oms.controller;

import com.kft.oms.service.AssociationService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/associations")
public class AssociationController {

    private final AssociationService associationService;

    public AssociationController(AssociationService associationService) {
        this.associationService = associationService;
    }

    @GetMapping({"", "/list"})
    public String getAllDrivers(Model model, Pageable pageable){
        model.addAttribute("associationModels", associationService.findAll(pageable));
        return "association/list";
    }

    @GetMapping(params = "name")
    public String getDriverByLicenseNo(@RequestParam String name, Model model, Pageable pageable){
        model.addAttribute("searchResult",true);
        model.addAttribute("associationModels", associationService.findByNameStartingWith(name, pageable));
        return "association/list";
    }
}
