package com.kft.oms.repository;

import com.kft.oms.constants.OffenderType;
import com.kft.oms.domain.OffenceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface OffenceCodeRepository extends JpaRepository<OffenceCode,Integer> {
    Optional<OffenceCode> findBySectionHeaderLabelAndLevelAndPenaltyAmountAndNumberLabel(String sectionHeaderLabel,
                                                                                   Short level,
                                                                                   Integer penaltyAmount,
                                                                                   String numberLabel);

    @Query("select offenceCode from OffenceCode offenceCode where offenceCode.sectionHeaderLabel = :sectionHeaderLabel and " +
            "(:level is null or offenceCode.level = :level) and (:penaltyAmount is null or offenceCode.penaltyAmount = :penaltyAmount)")
    List<OffenceCode> findAllBySectionHeaderLabelAndLevelAndPenaltyAmount(@Param("sectionHeaderLabel") String sectionHeaderLabel,
                                                                    @Param("level") Short level,
                                                                    @Param("penaltyAmount") Integer penaltyAmount);
}
