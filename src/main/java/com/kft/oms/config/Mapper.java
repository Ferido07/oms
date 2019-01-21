package com.kft.oms.config;

import com.kft.crud.domain.Person;
import com.kft.oms.domain.*;
import com.kft.oms.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;


@Component
public class Mapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.registerClassMap(factory.classMap(Offence.class, OffenceModel.class)
               // .field("offender","")
                // driverModel should not be mapped to driver only mapped explicitly when needed same for vehicle
                .fieldAToB("driver","driverModel")
                .fieldAToB("vehicle", "vehicleModel")
                .field("offenceCodes", "offenceCodeModels")
                .byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(OffenceCode.class, OffenceCodeModel.class)
                .byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(Driver.class, DriverModel.class)
                .field("driversLicense.licenseNo","licenseNo")
                .field("driversLicense.licenseType","licenseType")
                .byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(Person.class, PersonModel.class)
                .byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(Association.class, AssociationModel.class)
                .byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(VehicleOwnership.class, VehicleOwnershipModel.class)
                .field("vehicle.id","vehicleModelId")
                .field("personOwners","personModelOwners")
                .byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(Vehicle.class, VehicleModel.class)
                .byDefault()
                .field("association","associationModel")
                .field("vehicleOwnerships","vehicleOwnershipModels")
                .customize(new CustomMapper<Vehicle, VehicleModel>(){
                    @Override
                    public void mapAtoB(Vehicle vehicle, VehicleModel vehicleModel, MappingContext context) {
                        if(vehicle instanceof PublicTransport){
                            vehicleModel.setVehicleInstanceType(VehicleModel.VehicleInstanceType.PUBLIC_TRANSPORT);
                            vehicleModel.setSeatingCapacity(((PublicTransport) vehicle).getSeatingCapacity());
                        }else if(vehicle instanceof CargoVehicle){
                            vehicleModel.setVehicleInstanceType(VehicleModel.VehicleInstanceType.CARGO_VEHICLE);
                            vehicleModel.setLoadInQuintals(((CargoVehicle) vehicle).getLoadInQuintals());
                        }else //vehicle is any other kind like home automobile
                            vehicleModel.setVehicleInstanceType(null);
                    }

                    @Override
                    public void mapBtoA(VehicleModel vehicleModel, Vehicle vehicle, MappingContext context) {
/*                        if(vehicleModel.getVehicleInstanceType() == null){

                        }*/
                        //The following code removed because it does not work resulting in class cast exception since vehicle would not be instance of any of its children
                        /* if(vehicleModel.getVehicleInstanceType() == VehicleModel.VehicleInstanceType.PUBLIC_TRANSPORT){
                            ((PublicTransport)vehicle).setSeatingCapacity(vehicleModel.getSeatingCapacity());
                        }else if(vehicleModel.getVehicleInstanceType() == VehicleModel.VehicleInstanceType.CARGO_VEHICLE){
                            ((CargoVehicle)vehicle).setLoadInQuintals(vehicleModel.getLoadInQuintals());
                        }*/
                        vehicle.getVehicleOwnerships().forEach(vehicleOwnership -> vehicleOwnership.setVehicle(vehicle));
                    }
                })
                .toClassMap());

        factory.registerClassMap(factory.classMap(VehicleModel.class, PublicTransport.class)
                .byDefault()
                .customize(new CustomMapper<VehicleModel, PublicTransport>() {
                    @Override
                    public void mapAtoB(VehicleModel vehicleModel, PublicTransport publicTransport, MappingContext context) {
                        if(vehicleModel.getVehicleInstanceType() == VehicleModel.VehicleInstanceType.PUBLIC_TRANSPORT) {
                            publicTransport.setSeatingCapacity(vehicleModel.getSeatingCapacity());
                        }else
                            throw new RuntimeException("VehicleModel does not represent PublicTransport");
                    }
                })
                .toClassMap());

        factory.registerClassMap(factory.classMap(VehicleModel.class, CargoVehicle.class)
                .byDefault()
                .customize(new CustomMapper<VehicleModel, CargoVehicle>() {
                    @Override
                    public void mapAtoB(VehicleModel vehicleModel, CargoVehicle cargoVehicle, MappingContext context) {
                        if(vehicleModel.getVehicleInstanceType() == VehicleModel.VehicleInstanceType.CARGO_VEHICLE){
                            cargoVehicle.setLoadInQuintals(vehicleModel.getLoadInQuintals());
                        }else
                            throw  new RuntimeException("VehicleModel does not represent CargoVehicle");
                    }
                })
                .toClassMap());
    }
}
