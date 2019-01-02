package com.kft.oms.controller;

import com.kft.oms.domain.OffenceCode;
import com.kft.oms.model.OffenceCodeModel;
import com.kft.oms.service.OffenceCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/offence-code")
public class OffenceCodeController {

    private final OffenceCodeService offenceCodeService;

    public OffenceCodeController(OffenceCodeService offenceCodeService) {
        this.offenceCodeService = offenceCodeService;
    }

    @ResponseBody
    @GetMapping
    public List<String> getOffenceCodes(@RequestParam String sectionHeaderLabel,
                                        @RequestParam Short level,
                                        @RequestParam Integer penaltyAmount){
        return offenceCodeService.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(
                sectionHeaderLabel, level, penaltyAmount)
                .stream()
                .map(offenceCodeModel ->
                        offenceCodeModel.getSectionHeaderLabel() + " " +
                        offenceCodeModel.getLevel() + " " +
                        offenceCodeModel.getPenaltyAmount() + " " +
                        offenceCodeModel.getNumberLabel())
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/get")
    public Optional<OffenceCodeModel> getOffenceCode(@RequestParam String sectionHeaderLabel,
                                                     @RequestParam Short level,
                                                     @RequestParam Integer penaltyAmount,
                                                     @RequestParam String numberLabel){
        return offenceCodeService.findBySectionHeaderLabelAndLevelAndPenaltyAmountAndNumberLabel(
                sectionHeaderLabel, level, penaltyAmount, numberLabel
        );
    }
}
