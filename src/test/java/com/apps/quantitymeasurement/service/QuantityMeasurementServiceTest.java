package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.impl.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementServiceTest {

    private QuantityMeasurementServiceImpl service;
    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    void setup() {

        repository = new QuantityMeasurementDatabaseRepository();

        // clear database before every test
        repository.deleteAll();

        service = new QuantityMeasurementServiceImpl(repository);
    }

    @Test
    void testCompareOperationPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0.1, "M", "LENGTH");

        boolean result = service.compare(q1, q2);

        assertFalse(result);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testAddOperationPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "CM", "LENGTH");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(15, result.getValue());

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testSubtractOperationPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "CM", "LENGTH");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(5, result.getValue());

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testDivideOperationPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2, "CM", "LENGTH");

        double result = service.divide(q1, q2);

        assertEquals(5, result);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testConvertOperationPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");

        QuantityDTO result = service.convert(q1, "M");

        assertEquals("M", result.getUnit());

        assertEquals(1, repository.getTotalCount());
    }
}