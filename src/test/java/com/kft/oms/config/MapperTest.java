package com.kft.oms.config;

import com.kft.oms.domain.CargoVehicle;
import com.kft.oms.domain.PublicTransport;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.domain.VehiclePlate;
import com.kft.oms.model.VehicleModel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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


        publicTransport = new PublicTransport();
        publicTransport.setId(1);
        publicTransport.setBolo("Public Transport Bolo");
        publicTransport.setLibre("Public Transport Libre");
        publicTransport.setType("Higer Bus");
        publicTransport.setSideNo(34);
        publicTransport.setSeatingCapacity(60);
        publicTransport.setPlate(vehiclePlate);


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
        assertEquals(publicTransportModel.getVehicleInstanceType(), VehicleModel.VehicleInstanceType.PUBLIC_TRANSPORT);
        assertEquals(publicTransportModel.getPlateNo(), publicTransport.getPlate().toString());
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

    }
}
