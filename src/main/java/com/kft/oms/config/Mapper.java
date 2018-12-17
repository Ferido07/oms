package com.kft.oms.config;

import com.kft.oms.domain.Driver;
import com.kft.oms.domain.Offence;
import com.kft.oms.model.DriverModel;
import com.kft.oms.model.OffenceModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;


@Component
public class Mapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.registerClassMap(factory.classMap(Offence.class, OffenceModel.class)
               // .field("offender","")
                .byDefault()
                .toClassMap());

        factory.registerClassMap(factory.classMap(Driver.class, DriverModel.class)
                .field("driversLicense.licenseNo","licenseNo")
                .field("driversLicense.licenseType","licenseType")
                .byDefault()
                .toClassMap());

    }
}
