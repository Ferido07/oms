package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.config.Mapper;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.model.OffenceCodeModel;
import com.kft.oms.repository.OffenceCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffenceCodeServiceImpl extends CrudServiceImpl<OffenceCode,Integer,OffenceCodeRepository> implements OffenceCodeService {

    private final Mapper mapper;

    public OffenceCodeServiceImpl(OffenceCodeRepository repository, Mapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public Optional<OffenceCodeModel> findBySectionHeaderLabelAndLevelAndPenaltyAmountAndNumberLabel(String sectionHeaderLabel, Short level, Integer penaltyAmount, String numberLabel) {
        return repository.findBySectionHeaderLabelAndLevelAndPenaltyAmountAndNumberLabel(sectionHeaderLabel, level, penaltyAmount, numberLabel)
                .map(offenceCode -> mapper.map(offenceCode, OffenceCodeModel.class));
    }

    @Override
    public List<OffenceCodeModel> findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(String sectionHeaderLabel, Short level, Integer penaltyAmount) {
        List<OffenceCode> offenceCodes = repository.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(sectionHeaderLabel, level, penaltyAmount);
        return mapper.mapAsList(offenceCodes, OffenceCodeModel.class);
    }

    @Override
    public Page<OffenceCodeModel> findAllOffenceCodeModels(Pageable pageable) {
        return repository.findAll(pageable).map(offenceCode -> mapper.map(offenceCode, OffenceCodeModel.class));
    }
}
