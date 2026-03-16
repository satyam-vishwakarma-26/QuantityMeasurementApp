package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    void setup() {

        repository = new QuantityMeasurementDatabaseRepository();

        // clear database before every test
        repository.deleteAll();
    }

    @Test
    void testSaveEntity() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "LENGTH",
                        "COMPARE",
                        10.0,
                        "CM",
                        0.1,
                        "M",
                        true
                );

        repository.save(entity);

        List<QuantityMeasurementEntity> list =
                repository.getAllMeasurements();

        assertEquals(1, list.size());
    }

    @Test
    void testRetrieveAllMeasurements() {

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "ADD", 10.0, "CM", 20.0, "CM", false));

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "SUBTRACT", 10.0, "CM", 5.0, "CM", false));

        List<QuantityMeasurementEntity> list =
                repository.getAllMeasurements();

        assertEquals(2, list.size());
    }

    @Test
    void testQueryByOperation() {

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "ADD", 10.0, "CM", 5.0, "CM", false));

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "SUBTRACT", 10.0, "CM", 5.0, "CM", false));

        List<QuantityMeasurementEntity> results =
                repository.getMeasurementsByOperation("ADD");

        assertEquals(1, results.size());
        assertEquals("ADD", results.get(0).getOperationType());
    }

    @Test
    void testQueryByMeasurementType() {

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "COMPARE", 10.0, "CM", 0.1, "M", true));

        repository.save(new QuantityMeasurementEntity(
                "WEIGHT", "COMPARE", 1.0, "KG", 1000.0, "GRAM", true));

        List<QuantityMeasurementEntity> results =
                repository.getMeasurementsByType("WEIGHT");

        assertEquals(1, results.size());
        assertEquals("WEIGHT", results.get(0).getMeasurementType());
    }

    @Test
    void testTotalCount() {

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "ADD", 10.0, "CM", 5.0, "CM", false));

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "SUBTRACT", 10.0, "CM", 5.0, "CM", false));

        int count = repository.getTotalCount();

        assertEquals(2, count);
    }

    @Test
    void testDeleteAll() {

        repository.save(new QuantityMeasurementEntity(
                "LENGTH", "ADD", 10.0, "CM", 5.0, "CM", false));

        repository.deleteAll();

        int count = repository.getTotalCount();

        assertEquals(0, count);
    }
}