package com.kft.oms.config;

import com.kft.crud.domain.Person;
import com.kft.oms.domain.*;
import com.kft.oms.model.PersonModel;
import com.kft.oms.model.VehicleModel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Autowired
    Mapper mapper;

    private static PublicTransport publicTransport;
    private static CargoVehicle cargoVehicle;
    private static VehicleModel vehicleModel;


    @BeforeClass
    public static void setup(){
        VehiclePlate vehiclePlate = new VehiclePlate();
        vehiclePlate.setPlateCode((short)3);
        vehiclePlate.setPlateNo("34567");
        vehiclePlate.setPlateRegion("AA");

        List<VehicleOwner> vehicleOwners = new ArrayList<>();

        VehicleOwner vehicleOwner1 = new VehicleOwner();
        vehicleOwner1.setId(6);
        vehicleOwner1.setFirstName("vehicleOwner1 firstName");
        vehicleOwner1.setMiddleName("vehicleOwner1 middleName");
        vehicleOwner1.setLastName("vehicleOwner1 lastName");
        vehicleOwners.add(vehicleOwner1);

        VehicleOwner vehicleOwner2 = new VehicleOwner();
        vehicleOwner2.setId(7);
        vehicleOwner2.setFirstName("vehicleOwner2 firstName");
        vehicleOwner2.setMiddleName("vehicleOwner2 middleName");
        vehicleOwner2.setLastName("vehicleOwner2 lastName");
        vehicleOwners.add(vehicleOwner2);

        VehicleOwner vehicleOwner3 = new VehicleOwner();
        vehicleOwner3.setId(8);
        vehicleOwner3.setFirstName("vehicleOwner3 firstName");
        vehicleOwner3.setMiddleName("vehicleOwner3 middleName");
        vehicleOwner3.setLastName("vehicleOwner3 lastName");
        vehicleOwners.add(vehicleOwner3);



        publicTransport = new PublicTransport();
        publicTransport.setId(1);
        publicTransport.setBolo("Public Transport Bolo");
        publicTransport.setLibre("Public Transport Libre");
        publicTransport.setType("Higer Bus");
        publicTransport.setSideNo(34);
        publicTransport.setSeatingCapacity(60);
        publicTransport.setPlate(vehiclePlate);
        publicTransport.setOwners(vehicleOwners);


        cargoVehicle = new CargoVehicle();
        cargoVehicle.setId(2);
        cargoVehicle.setBolo("Cargo Vehicle Bolo");
        cargoVehicle.setLibre("Cargo Vehicle Libre");
        cargoVehicle.setType("Sinotruk");
        cargoVehicle.setSideNo(35);
        cargoVehicle.setLoadInQuintals(100);
        cargoVehicle.setPlate(vehiclePlate);

        //initialize and set common attributes in vehicleModel
        vehicleModel = new VehicleModel();
        vehicleModel.setId(3);
        vehicleModel.setBolo("Vehicle Model Bolo");
        vehicleModel.setLibre("Vehicle Model Libre");
        vehicleModel.setType("Vehicle Model Type");
        vehicleModel.setSideNo(36);
        vehicleModel.setPlateNo("3-33332-OR");

        PersonModel ownerModel = new PersonModel();
        ownerModel.setId(9);
        ownerModel.setFirstName("OwnerModel firstName");
        ownerModel.setMiddleName("OwnerModel middleName");
        ownerModel.setLastName("OwnerModel lastName");
        vehicleModel.setOwner(ownerModel);

        PersonModel owner2Model = new PersonModel();
        owner2Model.setId(10);
        owner2Model.setFirstName("Owner2Model firstName");
        owner2Model.setMiddleName("Owner2Model middleName");
        owner2Model.setLastName("Owner2Model lastName");
        vehicleModel.setOwner2(owner2Model);
    }

    @Test
    public void testPublicTransportToVehicleModelMapping(){
        VehicleModel publicTransportModel = mapper.map(publicTransport, VehicleModel.class);

        assertEquals(publicTransport.getId(), publicTransportModel.getId());
        assertEquals(publicTransport.getBolo(), publicTransportModel.getBolo());
        assertEquals(publicTransport.getLibre(), publicTransportModel.getLibre());
        assertEquals(publicTransport.getType(), publicTransportModel.getType());
        assertEquals(publicTransport.getSideNo(), publicTransportModel.getSideNo());
        assertEquals(publicTransport.getSeatingCapacity(), publicTransportModel.getSeatingCapacity());
        assertEquals(VehicleModel.VehicleInstanceType.PUBLIC_TRANSPORT, publicTransportModel.getVehicleInstanceType());
        assertEquals(publicTransport.getPlate().toString(), publicTransportModel.getPlateNo());

        assertEquals(publicTransport.getOwners().get(0).getFirstName(), publicTransportModel.getOwner().getFirstName());
        assertEquals(publicTransport.getOwners().get(1).getFirstName(), publicTransportModel.getOwner2().getFirstName());
        assertEquals(publicTransport.getOwners().get(2).getFirstName(), publicTransportModel.getOwner3().getFirstName());
    }

    @Test
    public void testCargoVehicleToVehicleModelMapping(){
        VehicleModel cargoVehicleModel = mapper.map(cargoVehicle, VehicleModel.class);

        assertEquals(cargoVehicle.getId(), cargoVehicleModel.getId());
        assertEquals(cargoVehicle.getBolo(), cargoVehicleModel.getBolo());
        assertEquals(cargoVehicle.getLibre(), cargoVehicleModel.getLibre());
        assertEquals(cargoVehicle.getType(), cargoVehicleModel.getType());
        assertEquals(cargoVehicle.getSideNo(), cargoVehicleModel.getSideNo());
        assertEquals(cargoVehicle.getLoadInQuintals(), cargoVehicleModel.getLoadInQuintals());
        assertEquals(cargoVehicleModel.getVehicleInstanceType(), VehicleModel.VehicleInstanceType.CARGO_VEHICLE);
        assertEquals(cargoVehicleModel.getPlateNo(), cargoVehicle.getPlate().toString());
    }

    @Before
    public void clearUpSpecialAttributesInVehicleModel(){
        vehicleModel.setVehicleInstanceType(null);
        vehicleModel.setSeatingCapacity(null);
        vehicleModel.setLoadInQuintals(null);
    }

    @Test
    public void testVehicleModelToPublicTransportMapping(){
        vehicleModel.setVehicleInstanceType(VehicleModel.VehicleInstanceType.PUBLIC_TRANSPORT);
        vehicleModel.setSeatingCapacity(45);

        Vehicle vehicle = mapper.map(vehicleModel, PublicTransport.class);

        assert vehicle != null;
        assertEquals(vehicleModel.getSeatingCapacity(), ((PublicTransport) vehicle).getSeatingCapacity());

        assertEquals(vehicleModel.getId(), vehicle.getId());
        assertEquals(vehicleModel.getBolo(), vehicle.getBolo());
        assertEquals(vehicleModel.getLibre(), vehicle.getLibre());
        assertEquals(vehicleModel.getType(), vehicle.getType());
        assertEquals(vehicleModel.getSideNo(), vehicle.getSideNo());
        assertEquals(vehicleModel.getPlateNo(), vehicle.getPlate().toString());
    }

    @Test
    public void testVehicleModelToCargoVehicleMapping(){
        vehicleModel.setVehicleInstanceType(VehicleModel.VehicleInstanceType.CARGO_VEHICLE);
        vehicleModel.setLoadInQuintals(90);

        Vehicle vehicle = mapper.map(vehicleModel, CargoVehicle.class);

        assert vehicle != null;
        assertEquals(vehicleModel.getLoadInQuintals(), ((CargoVehicle) vehicle).getLoadInQuintals());

        assertEquals(vehicleModel.getId(), vehicle.getId());
        assertEquals(vehicleModel.getBolo(), vehicle.getBolo());
        assertEquals(vehicleModel.getLibre(), vehicle.getLibre());
        assertEquals(vehicleModel.getType(), vehicle.getType());
        assertEquals(vehicleModel.getSideNo(), vehicle.getSideNo());
        assertEquals(vehicleModel.getPlateNo(), vehicle.getPlate().toString());

        assertEquals(vehicleModel.getOwner().getFirstName(), vehicle.getOwners().get(0).getFirstName());
        assertEquals(vehicleModel.getOwner().getLastName(), vehicle.getOwners().get(0).getLastName());

        assertEquals(vehicleModel.getOwner2().getFirstName(), vehicle.getOwners().get(1).getFirstName());
        assertEquals(vehicleModel.getOwner2().getLastName(), vehicle.getOwners().get(1).getLastName());
    }

    @Test
    public void testPersonMapping(){
        Person person = new Person();
        person.setId(5);
        person.setFirstName("TestPerson1 firstName");
        person.setMiddleName("TestPerson1 middleName");
        person.setLastName("TestPerson1 lastName");
        person.setGender("Male");

        PersonModel personModel = mapper.map(person, PersonModel.class);

        assertEquals(personModel.getId(), person.getId());
        assertEquals(personModel.getFirstName(), person.getFirstName());
        assertEquals(personModel.getMiddleName(), person.getMiddleName());
        assertEquals(personModel.getLastName(), person.getLastName());

        Person vehicleOwner = mapper.map(personModel, VehicleOwner.class);

        assertEquals(vehicleOwner.getId(), personModel.getId());
        assertEquals(vehicleOwner.getFirstName(), personModel.getFirstName());
        assertEquals(vehicleOwner.getMiddleName(), personModel.getMiddleName());
        assertEquals(vehicleOwner.getLastName(), personModel.getLastName());


        /*Orika is weird it doesn't even create error when mapping the following it just copies
        the id since it is in both classes while there is no configuration defined for personModel
        and organization.
        Organization organization= mapper.map(personModel, Organization.class);*/
    }

    @Test
    public void testAssociationMapping(){

    }
}
