package com.kft.oms.service;

import com.kft.crud.domain.OffenderEntity;
import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.domain.Offence;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.repository.OffenceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class OffenceServiceImpl extends CrudServiceImpl<Offence,Integer,OffenceRepository> implements OffenceService {

    public OffenceServiceImpl(OffenceRepository repository) {
        super(repository);
    }

    /**
     * Get all offences committed by an offender after the given date up till now
     * @param offenderEntity the offender
     * @param startingDate the starting date after which the offences are retrieved
     */
    @Override
    public List<Offence> getAllOffencesByOffenderBetween(OffenderEntity offenderEntity, LocalDate startingDate, LocalDate endDate) {
        return repository.findAllByOffenderAndOffenceDateBetween(offenderEntity, startingDate, endDate);
    }

    @Override
    public Integer calculatePenaltyAmount(Offence offence) {
        Map<OffenceCode, Integer> offenceCodeToRepetitionMap = getOffenceCodeToRepetitionMapFromOffence(offence);
        List<Integer> penaltyAmounts = new ArrayList<>();
        offenceCodeToRepetitionMap.forEach((offenceCode, repetition) -> penaltyAmounts.add(offenceCode.getPenaltyAmount() * repetition));
        return Collections.max(penaltyAmounts);
    }

    private Map<OffenceCode, Integer> getOffenceCodeToRepetitionMapFromOffence(Offence offence) {
        Map<OffenceCode, Integer> offenceCodeToRepetitionMap = new HashMap<>();
        //variable to hold the count for an offence code
        Integer offenceCodeRepetition;

        for(OffenceCode offenceCode : offence.getOffenceCodes()){
            if(offenceCode.isOffenceRepetitionConsidered()){
                offenceCodeRepetition = repository.countOffencesByOffenderAndOffenceCodesContainsAndOffenceDateBetween(
                        offence.getOffender(),
                        offenceCode,
                        offence.getOffenceDate().minusYears(1L),
                        offence.getOffenceDate()
                );
                /* if the id is null then the offence record is new and is not in database so we need to increment the
                count since count is the value retrieved from the database alone */
                offenceCodeRepetition = (offence.getId() == null)?  (offenceCodeRepetition + 1) : offenceCodeRepetition;
            }
            else{
                offenceCodeRepetition = 1;
            }
            offenceCodeToRepetitionMap.put(offenceCode, offenceCodeRepetition);
        }
        return offenceCodeToRepetitionMap;
    }

    @Override
    public Offence save(Offence offence) {
        //calculate or recalculate penalty amount whenever an offence is created or updated
        offence.setPenaltyAmount(calculatePenaltyAmount(offence));
        return super.save(offence);
    }
}
