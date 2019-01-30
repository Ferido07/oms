package com.kft.oms.controller;

import com.kft.oms.model.OffenceModel;
import com.kft.oms.service.OffenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/offender")
public class OffenderController {

    private final OffenceService offenceService;

    public OffenderController(OffenceService offenceService) {
        this.offenceService = offenceService;
    }

    @ResponseBody
    @GetMapping("/{offenderId}")
    public List<OffenceModel> getOffencesByOffender(@PathVariable Integer offenderId){
        return offenceService.getAllOffencesByOffenderId(offenderId);
    }

    @ResponseBody
    @GetMapping("/{offenderId}/code/{offenceCodeId}")
    public List<OffenceModel> getOffences(@PathVariable Integer offenderId, @PathVariable Integer offenceCodeId){
        return offenceService.getAllOffencesByOffenderIdAndOffenceCodeId(offenderId, offenceCodeId);
    }
}
