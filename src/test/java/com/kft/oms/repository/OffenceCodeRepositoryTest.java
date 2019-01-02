package com.kft.oms.repository;

import com.kft.oms.constants.OffenderType;
import com.kft.oms.domain.OffenceCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OffenceCodeRepositoryTest {

    @Autowired
    private OffenceCodeRepository repository;

    @Test
    public void testFindAllBySectionHeaderLabelAndLevelAndPenaltyAmountWithNullValues(){
        List<OffenceCode> offenceCodes = repository.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount("9.3.1", null, null);
        assertEquals(11, offenceCodes.size());
        offenceCodes = repository.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount("9.3.1", (short)1, null);
        assertEquals(10,offenceCodes.size());
        offenceCodes = repository.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount("9.3.1", (short)1, 100);
        assertEquals(8, offenceCodes.size());
        offenceCodes = repository.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount("9.3.1", null, 80);
        assertEquals(1, offenceCodes.size());
        offenceCodes = repository.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount("9.3.2", null, 80);
        assertEquals(0, offenceCodes.size());
        offenceCodes = repository.findAllBySectionHeaderLabelAndLevelAndPenaltyAmount("9.3.1", (short)2, null);
        assertEquals(1, offenceCodes.size());
    }
}
