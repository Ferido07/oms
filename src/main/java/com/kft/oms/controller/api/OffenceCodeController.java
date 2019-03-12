package com.kft.oms.controller.api;

import com.kft.oms.model.OffenceCodeModel;
import com.kft.oms.service.OffenceCodeService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController("apiOffenceCodeController")
@RequestMapping("/api/offence-code")
public class OffenceCodeController {

    private final OffenceCodeService offenceCodeService;

    public OffenceCodeController(OffenceCodeService offenceCodeService) {
        this.offenceCodeService = offenceCodeService;
    }

    @GetMapping("/section")
    public List<Dto> getSections(@RequestParam String sectionHeaderLabel){
        List<Dto> dtos = new ArrayList<>();
        if (checkParams(sectionHeaderLabel)) {
            List<OffenceCodeModel> offenceCodeModels = offenceCodeService.findAllBySectionHeaderLabelStartingWith(sectionHeaderLabel);
            Set<String> sections = offenceCodeModels.stream().map(OffenceCodeModel::getSectionHeaderLabel).collect(Collectors.toSet());
            sections.forEach(section -> {
                Dto dto = new Dto();
                dto.label = "Section " + section;
                dto.value = section;
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @GetMapping(params = "sectionHeaderLabel")
    public List<Dto> getLevelsForSection(@RequestParam String sectionHeaderLabel){
        List<Dto> dtos = new ArrayList<>();
        if (checkParams(sectionHeaderLabel)) {
            List<OffenceCodeModel> offenceCodeModels = offenceCodeService.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(sectionHeaderLabel,null, null);
            Set<Short> levels = offenceCodeModels.stream().map(OffenceCodeModel::getLevel).collect(Collectors.toSet());
            levels.forEach(level -> {
                Dto dto = new Dto();
                dto.label = "Level " + level;
                dto.value = sectionHeaderLabel + " " + level;
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @GetMapping(params = {"sectionHeaderLabel", "level"})
    public List<Dto> getPenaltyAmounts(@RequestParam String sectionHeaderLabel, @RequestParam Short level){
        List<Dto> dtos = new ArrayList<>();
        if (checkParams(sectionHeaderLabel) && level != null) {
            List<OffenceCodeModel> offenceCodeModels = offenceCodeService.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(sectionHeaderLabel, level, null);
            Set<Integer> penaltyAmounts = offenceCodeModels.stream().map(OffenceCodeModel::getPenaltyAmount).collect(Collectors.toSet());
            penaltyAmounts.forEach(penaltyAmount -> {
                Dto dto = new Dto();
                dto.label = "Penalty Amount " + penaltyAmount;
                dto.value = sectionHeaderLabel + " " + level + " " + penaltyAmount;
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @GetMapping(params = {"sectionHeaderLabel", "level", "penaltyAmount"})
    public List<Dto> getOffenceCodes(@RequestParam String sectionHeaderLabel, @RequestParam Short level, @RequestParam Integer penaltyAmount){
        List<Dto> dtos = new ArrayList<>();
        if (checkParams(sectionHeaderLabel) && level != null && penaltyAmount != null) {
            Set<String> numberLabels = offenceCodeService.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(sectionHeaderLabel, level, penaltyAmount)
                    .stream().map(OffenceCodeModel::getNumberLabel).collect(Collectors.toSet());
            numberLabels.forEach(numberLabel -> {
                Dto dto = new Dto();
                dto.label = "Number " + numberLabel ;
                dto.value = sectionHeaderLabel + " " + level + " " + penaltyAmount + " " + numberLabel;
                dtos.add(dto);
            });
        }
        return dtos;
    }


    @GetMapping(value = "/get", params = {"sectionHeaderLabel", "level", "penaltyAmount", "numberLabel"})
    public Optional<OffenceCodeModel> getOffenceCode(@RequestParam String sectionHeaderLabel,
                                                     @RequestParam Short level,
                                                     @RequestParam Integer penaltyAmount,
                                                     @RequestParam String numberLabel){
        if (checkParams(sectionHeaderLabel,numberLabel) && level != null && penaltyAmount != null) {
            return offenceCodeService.findBySectionHeaderLabelAndLevelAndPenaltyAmountAndNumberLabel(
                    sectionHeaderLabel, level, penaltyAmount, numberLabel
            );
        }
        else
            return Optional.empty();
    }

    private boolean checkParams(String... params){
        boolean areValid;
        for(String param : params){
            areValid = param != null && !param.isEmpty();
            if(!areValid)
                return false;
        }
        return true;
    }

    public class Dto{
        private String label;
        private String value;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
