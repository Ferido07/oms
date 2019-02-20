package com.kft.oms.repository;

import com.kft.crud.domain.OffenderEntity;
import com.kft.oms.domain.Offence;
import com.kft.oms.domain.OffenceCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface OffenceRepository extends JpaRepository<Offence,Integer> {

    List<Offence> findAllByOffenderAndDateBetween(OffenderEntity offenderEntity, LocalDate startingDate, LocalDate endDate);
    Integer countOffencesByOffenderAndOffenceCodesContainsAndDateBetween(OffenderEntity offenderEntity, OffenceCode offenceCode, LocalDate startingDate, LocalDate endDate);
    List<Offence> findAllByOffenderIdAndDateBetween(Integer id, LocalDate startDate, LocalDate endDate);
    Page<Offence> findAllByOffenderId(Integer id, Pageable pageable);
    Page<Offence> findAllByOffenderIdAndOffenceCodesContains(Integer offenderId, OffenceCode offenceCode, Pageable pageable);
    List<Offence> findAllByOffenderIdAndOffenceCodesContainsAndDateBetween(Integer offenderId, OffenceCode offenceCode, LocalDate startDate, LocalDate endDate);
    Page<Offence> findOffenceByTicketNoStartingWith(String ticketNo, Pageable pageable);
}
