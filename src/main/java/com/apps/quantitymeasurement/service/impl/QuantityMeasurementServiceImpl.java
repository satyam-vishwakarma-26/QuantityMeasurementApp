package com.apps.quantitymeasurement.service.impl;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        boolean result = q1.getValue() == q2.getValue();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                q1.getUnitType(),
                "COMPARE",
                q1.getValue(),
                q1.getUnit(),
                q2.getValue(),
                q2.getUnit(),
                result
        );

        repository.save(entity);

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {

        // simple placeholder logic
        double convertedValue = input.getValue();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                input.getUnitType(),
                "CONVERT",
                input.getValue(),
                input.getUnit(),
                convertedValue,
                targetUnit,
                true
        );

        repository.save(entity);

        return new QuantityDTO(convertedValue, targetUnit, input.getUnitType());
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        double resultValue = q1.getValue() + q2.getValue();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                q1.getUnitType(),
                "ADD",
                q1.getValue(),
                q1.getUnit(),
                q2.getValue(),
                q2.getUnit(),
                true
        );

        repository.save(entity);

        return new QuantityDTO(resultValue, q1.getUnit(), q1.getUnitType());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        double resultValue = q1.getValue() - q2.getValue();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                q1.getUnitType(),
                "SUBTRACT",
                q1.getValue(),
                q1.getUnit(),
                q2.getValue(),
                q2.getUnit(),
                true
        );

        repository.save(entity);

        return new QuantityDTO(resultValue, q1.getUnit(), q1.getUnitType());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        double result = q1.getValue() / q2.getValue();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                q1.getUnitType(),
                "DIVIDE",
                q1.getValue(),
                q1.getUnit(),
                q2.getValue(),
                q2.getUnit(),
                true
        );

        repository.save(entity);

        return result;
    }
}