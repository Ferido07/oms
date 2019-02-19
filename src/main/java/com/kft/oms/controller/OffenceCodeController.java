package com.kft.oms.controller;

import com.kft.oms.model.OffenceCodeModel;
import com.kft.oms.service.OffenceCodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

    @GetMapping({"/list"})
    public String getAll(Model model, Pageable pageable){
        Page<OffenceCodeModel> offenceCodeModels = offenceCodeService.findAllOffenceCodeModels(pageable);

        List<OffenceCodeModel> offenceCodeModelsList = offenceCodeModels.getContent();

        //Map<SectionHeaderLabel,Map<Level,Map<PenaltyAmount,List<OffenceCodeModel>>>>
        Map<String, Map<Short, Map<Integer, List<OffenceCodeModel>>>> offenceCodeModelsBySectionHeaderLabelByLevelByPenaltyAmount;

        offenceCodeModelsBySectionHeaderLabelByLevelByPenaltyAmount =
                offenceCodeModelsList.stream().collect(groupingBy(OffenceCodeModel::getSectionHeaderLabel,
                    groupingBy(OffenceCodeModel::getLevel,
                        groupingBy(OffenceCodeModel::getPenaltyAmount))));

        model.addAttribute("mappedOffenceCodeModels", offenceCodeModelsBySectionHeaderLabelByLevelByPenaltyAmount);

        return "offence-code/list";
    }
}
