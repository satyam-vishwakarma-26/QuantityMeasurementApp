package com.apps.quantitymeasurement.dto;

public class QuantityDTO {

    private double value;
    private String unit;
    private String unitType;

    public QuantityDTO(double value, String unit, String unitType) {
        this.value = value;
        this.unit = unit;
        this.unitType = unitType;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getUnitType() {
        return unitType;
    }
}