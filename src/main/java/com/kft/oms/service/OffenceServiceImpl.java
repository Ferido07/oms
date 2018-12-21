package com.kft.oms.service;

import com.kft.crud.domain.OffenderEntity;
import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.config.Mapper;
import com.kft.oms.domain.Offence;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.model.OffenceModel;
import com.kft.oms.repository.OffenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class OffenceServiceImpl extends CrudServiceImpl<Offence,Integer,OffenceRepository> implements OffenceService {

    private final Mapper mapper;

    @Autowired
    public OffenceServiceImpl(OffenceRepository repository, Mapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    /**
     * Get all offences committed by an offender after the given date up till now
     * @param offenderEntity the offender
     * @param startingDate the starting date after which the offences are retrieved
     */
    @Override
    public List<Offence> getAllOffencesByOffenderBetween(OffenderEntity offenderEntity, LocalDate startingDate, LocalDate endDate) {
        return repository.findAllByOffenderAndDateBetween(offenderEntity, startingDate, endDate);
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
                offenceCodeRepetition = repository.countOffencesByOffenderAndOffenceCodesContainsAndDateBetween(
                        offence.getOffender(),
                        offenceCode,
                        offence.getDate().minusYears(1L),
                        offence.getDate()
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
        //offence.setPenaltyAmount(calculatePenaltyAmount(offence));
        return super.save(offence);
    }

    public List<OffenceModel> getAllAsOffenceModel(){
        List<Offence> offenceList = repository.findAll();
        return mapper.mapAsList(offenceList, OffenceModel.class);
    }

    public Optional<OffenceModel> findOffenceModelById(Integer id){
        Optional<Offence> offenceOptional = repository.findById(id);

        Optional<OffenceModel> offenceModelOptional;
        offenceModelOptional = offenceOptional.map(offence -> mapper.map(offence, OffenceModel.class));

        return offenceModelOptional;
    }

    @Override
    public OffenceModel save(OffenceModel offenceModel) {

        Offence offence;
        //check if item is new
        if(offenceModel.getId() != null){
            //check if the item exists
            Optional<Offence> offenceOptional = repository.findById(offenceModel.getId());

            //item exists and update is made to the same object
            if(offenceOptional.isPresent()) {
                offence = offenceOptional.get();
                mapper.map(offenceModel, offence);
            }
            else
                throw new RuntimeException("No offence with the given Id exists");
        }else{
            //else object is new so create a new offence object
            offence = mapper.map(offenceModel, Offence.class);
            //add the vehicle to each vehicleOwner so that the relation between them is persisted since
            //vehicleOwner is the owner of the relationship
            offence.getVehicle().getOwners().forEach(
                    vehicleOwner -> {
                        if(vehicleOwner.getVehicles() != null)
                            vehicleOwner.getVehicles().add(offence.getVehicle());
                        else{
                            List<Vehicle> vehicles = new ArrayList<>();
                            vehicles.add(offence.getVehicle());
                            vehicleOwner.setVehicles(vehicles);
                        }
                    }
            );
        }

        offence.setOffender(offence.getDriver());
        Offence savedOffence = repository.save(offence);
//todo add a check if any of the entities that have associations with offence change id. the associations cannot change id.
        return mapper.map(savedOffence, OffenceModel.class);
    }
}
