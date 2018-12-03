package com.kft.oms.service;

import com.kft.crud.domain.OffenderEntity;
import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.domain.Offence;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.repository.OffenceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

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

        Integer maxPenaltyAmount = getMaximumPenaltyAmount(offence);
        Integer maxRepetition = getMaximumRepetitionFromListOfOffenceCodesInAnOffence(offence);

        return maxPenaltyAmount * maxRepetition;
    }

    private Integer getMaximumRepetitionFromListOfOffenceCodesInAnOffence(Offence offence) {
        //Check if the offence is a violation of only one offence code
        if(offence.getOffenceCodes().size() == 1){
            OffenceCode offenceCode = offence.getOffenceCodes().get(0);

            if(offenceCode.isOffenceRepetitionConsidered()) {
                //
                Integer count =  repository.countOffencesByOffenderAndOffenceCodesContainsAndOffenceDateBetween(
                        offence.getOffender(),
                        offenceCode,
                        offence.getOffenceDate().minusYears(1L),
                        offence.getOffenceDate()
                );
                /* if the id is null then the offence record is new and is not in database so we need to increment the count
                   since count is the value retrieved from the database alone
                 */
                return (offence.getId() == null)?  (count + 1) : count;
            }
            else
                return 1;
        }
        //if more than one offence codes are violated at a time
        else{
            //variable to hold the maximum of all the counts for each offence code violation in the previous year
            Integer maximumOffenceCodeCount = 0;
            for(OffenceCode offenceCode : offence.getOffenceCodes()){
                //variable to hold the count for an offence code
                Integer offenceCodeCount;

                if(offenceCode.isOffenceRepetitionConsidered()){
                    offenceCodeCount = repository.countOffencesByOffenderAndOffenceCodesContainsAndOffenceDateBetween(
                            offence.getOffender(),
                            offenceCode,
                            offence.getOffenceDate().minusYears(1L),
                            offence.getOffenceDate()
                    );
                }
                else{
                    offenceCodeCount = 1;
                }

                if (offenceCodeCount > maximumOffenceCodeCount)
                    maximumOffenceCodeCount = offenceCodeCount;
            }
            /* if the id is null then the offence record is new and is not in database so we need to increment the
               the maximumOffenceCount since maximumOffenceCount is the value retrieved from the database alone
            */
            return offence.getId() == null? maximumOffenceCodeCount + 1 : maximumOffenceCodeCount;
        }
    }

    /**
     * Find out the maximum penalty amount for an offence in the list of offenceCodes
     * @param offence the offence
     * @return the maximum penalty amount
     */
    private Integer getMaximumPenaltyAmount(Offence offence) {
        Integer maxPenaltyAmount = offence.getOffenceCodes().get(0).getPenaltyAmount();
        for (OffenceCode offenceCode : offence.getOffenceCodes()){
            if(offenceCode.getPenaltyAmount() > maxPenaltyAmount)
                maxPenaltyAmount = offenceCode.getPenaltyAmount();
        }
        return maxPenaltyAmount;
    }

    @Override
    public Offence save(Offence offence) {
        //calculate or recalculate penalty amount whenever an offence is created or updated
        offence.setPenaltyAmount(calculatePenaltyAmount(offence));
        return super.save(offence);
    }
}
