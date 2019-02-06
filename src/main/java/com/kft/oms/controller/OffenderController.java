package com.kft.oms.controller;

import com.kft.oms.service.OffenceService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/offender")
public class OffenderController {

    private final OffenceService offenceService;

    public OffenderController(OffenceService offenceService) {
        this.offenceService = offenceService;
    }

    @GetMapping("/{offenderId}")
    public String getOffencesByOffender(@PathVariable Integer offenderId, Model model, Pageable pageable){
        model.addAttribute("offenceModels", offenceService.getAllOffencesByOffenderId(offenderId, pageable));
        return "offender/offence-list";
    }

    @GetMapping("/{offenderId}/code/{offenceCodeId}")
    public String getOffences(@PathVariable Integer offenderId, @PathVariable Integer offenceCodeId, Model model, Pageable pageable){
        model.addAttribute("offenceModels", offenceService.getAllOffencesByOffenderIdAndOffenceCodeId(offenderId, offenceCodeId, pageable));
        return"offender/offence-list";
    }
}
