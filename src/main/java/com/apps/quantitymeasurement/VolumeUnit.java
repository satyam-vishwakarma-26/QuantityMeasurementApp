package com.apps.quantitymeasurement;

// UC11 – VolumeUnit Enum

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),             // Base unit
    MILLILITRE(0.001),      // 1 mL = 0.001 L
    GALLON(3.78541);        // 1 gallon ≈ 3.78541 L

    private final double toLitreFactor;

    VolumeUnit(double toLitreFactor) {
        this.toLitreFactor = toLitreFactor;
    }

    @Override
    public double getConversionFactor() {
        return toLitreFactor;
    }

    /**
     * Convert value in THIS unit to base unit (litre)
     */
    @Override
    public double convertToBaseUnit(double value) {
        return value * toLitreFactor;
    }

    /**
     * Convert value from base unit (litre) to THIS unit
     */
    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toLitreFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}