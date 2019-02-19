package com.kft.oms.controller.api;

import com.kft.oms.model.OffenceCodeModel;
import com.kft.oms.service.OffenceCodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("apiOffenceCodeController")
@RequestMapping("/api/offence-code")
public class OffenceCodeController {

    private final OffenceCodeService offenceCodeService;

    public OffenceCodeController(OffenceCodeService offenceCodeService) {
        this.offenceCodeService = offenceCodeService;
    }

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


    @GetMapping(value = "/get", params = {"sectionHeaderLabel","level","penaltyAmount","numberLabel"})
    public Optional<OffenceCodeModel> getOffenceCode(@RequestParam String sectionHeaderLabel,
                                                     @RequestParam Short level,
                                                     @RequestParam Integer penaltyAmount,
                                                     @RequestParam String numberLabel){
        return offenceCodeService.findBySectionHeaderLabelAndLevelAndPenaltyAmountAndNumberLabel(
                sectionHeaderLabel, level, penaltyAmount, numberLabel
        );
    }

}
