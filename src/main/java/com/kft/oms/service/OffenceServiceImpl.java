package com.kft.oms.service;

import com.kft.crud.domain.OffenderEntity;
import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.config.Mapper;
import com.kft.oms.constants.OffenderType;
import com.kft.oms.domain.Driver;
import com.kft.oms.domain.Offence;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.model.OffenceModel;
import com.kft.oms.repository.DriverRepository;
import com.kft.oms.repository.OffenceRepository;
import com.kft.oms.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OffenceServiceImpl extends CrudServiceImpl<Offence,Integer,OffenceRepository> implements OffenceService {

    private final Mapper mapper;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public OffenceServiceImpl(OffenceRepository repository, Mapper mapper, DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        super(repository);
        this.mapper = mapper;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
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

    public List<OffenceModel> getAllAsOffenceModel(){
        List<Offence> offenceList = repository.findAll();
        return mapper.mapAsList(offenceList, OffenceModel.class);
    }

    public Optional<OffenceModel> findOffenceModelById(Integer id){
        Optional<Offence> offenceOptional = repository.findById(id);
        return offenceOptional.map(offence -> mapper.map(offence, OffenceModel.class));
    }

    @Override
    public OffenceModel save(OffenceModel offenceModel) {

        Offence offence;

        //check if item exists
        if(offenceModel.getId() != null){
            //check if the item exists
            Optional<Offence> offenceOptional = repository.findById(offenceModel.getId());

            //item exists and update is made to the same object
            if(offenceOptional.isPresent()) {
                offence = offenceOptional.get();
                //Note: on handling related entities in offence
                //copy everything from Model to domain but the whole system of persisting offence
                //counts on the mapper not mapping the driverModel to driver so that this part doesn't
                //overwrite data retrieved from database
                //even though driver and vehicle are retrieved separately before retrieving offence
                //when the offence is retrieved hibernate knows that it has the driver and vehicle so it
                //doesn't issue another select and just returns the pointer of the previous values and
                //hence mapping driverModel and vehicleModel in OffenceModel to driver and vehicle in
                //Offence overwrites the values in offence leading to StaleObjectStateException error
                //and also they are not supposed to be updated when updating offence.
                //the same applies to any additional relations added in future.
                mapper.map(offenceModel, offence);
            }
            else
                throw new RuntimeException("No offence with the given Id exists. Id : " + offenceModel.getId());
        }else{
            //else object is new so create a new offence object
            offence = mapper.map(offenceModel, Offence.class);
            //set the driver and vehicle resolved by helper methods
            offence.setDriver(getDriver(offenceModel));
            offence.setVehicle(getVehicle(offenceModel));
        }

        switch(determineOffender(offence)){
            case DRIVER: offence.setOffender(offence.getDriver()); break;
            case VEHICLE_OWNER: //cannot get from vehicle cuz which owner is accused is not known
                //offence.setOffender(null);break;
            case PERSON:
            case ASSOCIATION:
            case ORGANIZATION:
                throw new UnsupportedOperationException("Not yet implemented");
            default: throw new RuntimeException("Cannot determine offender: Offence Codes don't have same sectionHeaderLabel and OffenderType");
        }

        //check if offender is already registered if not save offence so that offender gets saved otherwise transientObjectException will be thrown
        if(offence.getOffender().getId() == null)
            offence = repository.save(offence);

        //calculate the penalty amount before create or update
        offence.setPenaltyAmount(calculatePenaltyAmount(offence));

        Offence savedOffence = repository.save(offence);
        //done: add a check if any of the entities that have associations with offence change id. the associations cannot change id.
        //hibernate automatically checks if associations change id so it will throw error if the association of related entities change.
        return mapper.map(savedOffence, OffenceModel.class);
    }

    private Vehicle getVehicle(OffenceModel offenceModel) {
        Vehicle vehicle;//check if Vehicle exists
        if(offenceModel.getVehicleModel().getId() != null){
            Integer vehicleId = offenceModel.getVehicleModel().getId();
            Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
            if(vehicleOptional.isPresent()){
                vehicle = vehicleOptional.get();
            }
            else{
                throw new RuntimeException("Vehicle with Id of " + vehicleId + "does not exist");
            }
        }
        else{
            Optional<Vehicle> vehicleOptional = vehicleRepository.findByPlateNo(offenceModel.getVehicleModel().getPlateNo());
            vehicle = vehicleOptional.orElseGet(() -> mapper.map(offenceModel.getVehicleModel(), Vehicle.class));
        }
        return vehicle;
    }

    private Driver getDriver(OffenceModel offenceModel) {
        Driver driver;
        /* check if driver exists or not using id or licenseNo depending on which is set
                if exists then set the driver to the one found
                else set the driver to new one from model
        */
        //check if driver exists using Id
        if(offenceModel.getDriverModel().getId() != null){
            Integer driverId = offenceModel.getDriverModel().getId();
            Optional<Driver> driverOptional = driverRepository.findById(driverId);
            if(driverOptional.isPresent()) {
                driver = driverOptional.get();
            } else{
                throw new RuntimeException("Driver with Id of " + driverId + "does not exist");
            }
        }
        //check if driver exists using Driver's licenseNo
        else{
            Optional<Driver> driverOptional = driverRepository
                    .findByDriversLicenseLicenseNo(offenceModel.getDriverModel().getLicenseNo());
            //if driver is present then return that to driver else assign driver the result of the map from driverModel
            driver = driverOptional.orElseGet(() -> mapper.map(offenceModel.getDriverModel(), Driver.class));
        }
        return driver;
    }

    @Override
    public OffenderType determineOffender(Offence offence) {
        List<String> stringList = offence.getOffenceCodes()
                .stream()
                .map(offenceCode -> offenceCode.getSectionHeaderLabel().trim() + " " + offenceCode.getOffenderType())
                .distinct()
                .collect(Collectors.toList());
        if(stringList.size() == 1){
            String sectionLabelAndOffenderType = stringList.get(0);
            String[] split = sectionLabelAndOffenderType.split(" ", 2);
            return OffenderType.valueOf(split[1]);
        }else
            return null;
    }
}
