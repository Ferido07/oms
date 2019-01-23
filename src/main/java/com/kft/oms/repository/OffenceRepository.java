package com.kft.oms.repository;

import com.kft.crud.domain.OffenderEntity;
import com.kft.oms.domain.Offence;
import com.kft.oms.domain.OffenceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface OffenceRepository extends JpaRepository<Offence,Integer> {

    List<Offence> findAllByOffenderAndDateBetween(OffenderEntity offenderEntity, LocalDate startingDate, LocalDate endDate);
    Integer countOffencesByOffenderAndOffenceCodesContainsAndDateBetween(OffenderEntity offenderEntity, OffenceCode offenceCode, LocalDate startingDate, LocalDate endDate);
    List<Offence> findAllByOffenderIdAndDateBetween(Integer id, LocalDate startDate, LocalDate endDate);
    List<Offence> findAllByOffenderId(Integer id);
    List<Offence> findAllByOffenderIdAndOffenceCodesContains(Integer offenderId, OffenceCode offenceCode);
    List<Offence> findAllByOffenderIdAndOffenceCodesContainsAndDateBetween(Integer offenderId, OffenceCode offenceCode, LocalDate startDate, LocalDate endDate);
}
