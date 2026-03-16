package com.apps.quantitymeasurement.integrationTests;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.impl.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementController controller;
    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    void setup() {

        repository = new QuantityMeasurementDatabaseRepository();

        // clean database before every test
        repository.deleteAll();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);
    }

    @Test
    void testEndToEndComparisonPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0.1, "M", "LENGTH");

        boolean result = controller.compare(q1, q2);

        assertFalse(result);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testEndToEndAdditionPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "CM", "LENGTH");

        controller.add(q1, q2);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testEndToEndSubtractionPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2, "CM", "LENGTH");

        controller.subtract(q1, q2);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testEndToEndDivisionPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2, "CM", "LENGTH");

        controller.divide(q1, q2);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testEndToEndConversionPersisted() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");

        controller.convert(q1, "M");

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testH2DatabaseIsolationBetweenTests() {

        // database should be empty because setup() clears it
        assertEquals(0, repository.getTotalCount());
    }
}