package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.impl.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementControllerTest {

    private QuantityMeasurementController controller;
    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    void setup() {

        repository = new QuantityMeasurementDatabaseRepository();

        // clear database before every test
        repository.deleteAll();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);
    }

    @Test
    void testControllerCompare() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0.1, "M", "LENGTH");

        boolean result = controller.compare(q1, q2);

        assertFalse(result);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testControllerAdd() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "CM", "LENGTH");

        QuantityDTO result = controller.add(q1, q2);

        assertEquals(15, result.getValue());

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testControllerSubtract() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "CM", "LENGTH");

        QuantityDTO result = controller.subtract(q1, q2);

        assertEquals(5, result.getValue());

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testControllerDivide() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(2, "CM", "LENGTH");

        double result = controller.divide(q1, q2);

        assertEquals(5, result);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void testControllerConvert() {

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");

        QuantityDTO result = controller.convert(q1, "M");

        assertEquals("M", result.getUnit());

        assertEquals(1, repository.getTotalCount());
    }
}