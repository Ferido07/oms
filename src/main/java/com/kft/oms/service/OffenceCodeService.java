package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.model.OffenceCodeModel;
import com.kft.oms.repository.OffenceCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OffenceCodeService extends CrudService<OffenceCode,Integer,OffenceCodeRepository> {

    Optional<OffenceCodeModel> findBySectionHeaderLabelAndLevelAndPenaltyAmountAndNumberLabel(String sectionHeaderLabel,
                                                                                              Short level,
                                                                                              Integer penaltyAmount,
                                                                                              String numberLabel);
    List<OffenceCodeModel> findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(String sectionHeaderLabel, Short level, Integer penaltyAmount);

    Page<OffenceCodeModel> findAllOffenceCodeModels(Pageable pageable);
}
