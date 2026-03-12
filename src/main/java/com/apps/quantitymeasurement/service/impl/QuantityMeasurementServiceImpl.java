package com.apps.quantitymeasurement.service.impl;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return false;
    }

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {
        return null;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return null;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return null;
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        return 0;
    }
}