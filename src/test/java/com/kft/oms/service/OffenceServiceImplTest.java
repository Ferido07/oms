package com.kft.oms.service;

import com.kft.oms.config.Mapper;
import com.kft.oms.constants.OffenderType;
import com.kft.oms.domain.Offence;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.repository.DriverRepository;
import com.kft.oms.repository.OffenceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class OffenceServiceImplTest {

    @InjectMocks
    private OffenceServiceImpl offenceService;

    @Mock private OffenceRepository repository;
    @Mock private Mapper mapper;
    @Mock private DriverRepository driverRepository;

    @Test
    public void determineOffender() throws Exception {
        Offence offence = new Offence();

        Set<OffenceCode> offenceCodes = new HashSet<>();

        OffenceCode offenceCode1 = new OffenceCode();
        offenceCode1.setOffenderType(OffenderType.DRIVER);
        offenceCode1.setSectionHeaderLabel("9.3.1");
        offenceCodes.add(offenceCode1);

        OffenceCode offenceCode2 = new OffenceCode();
        offenceCode2.setOffenderType(OffenderType.DRIVER);
        offenceCode2.setSectionHeaderLabel("9.3.1");
        offenceCodes.add(offenceCode2);

        OffenceCode offenceCode3 = new OffenceCode();
        offenceCode3.setOffenderType(OffenderType.DRIVER);
        offenceCode3.setSectionHeaderLabel("9.3.1");
        offenceCodes.add(offenceCode3);

        offence.setOffenceCodes(offenceCodes);

        assertEquals(OffenderType.DRIVER, offenceService.determineOffender(offence));

        offenceCode3.setOffenderType(OffenderType.VEHICLE_OWNER);

        assertEquals(null, offenceService.determineOffender(offence));

        offenceCode2.setSectionHeaderLabel("9 ");

        assertEquals(null, offenceService.determineOffender(offence));

        offenceCode1.setSectionHeaderLabel("9 ");
        offenceCode3.setSectionHeaderLabel("9 ");
        offenceCode3.setOffenderType(OffenderType.DRIVER);

        assertEquals(OffenderType.DRIVER, offenceService.determineOffender(offence));
    }

}