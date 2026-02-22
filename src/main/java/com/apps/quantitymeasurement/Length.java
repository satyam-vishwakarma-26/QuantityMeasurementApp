package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * Generic Length value object supporting:
 * - Equality comparison (cross-unit)
 * - Unit conversion
 * - Addition of two length objects (UC6)
 *
 * Base unit: INCHES
 */
public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

    /**
     * Enum representing supported length units.
     * Conversion factor is relative to base unit (INCHES).
     */
    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    private double convertFromBaseToTargetUnit(double inches, LengthUnit targetUnit) {
        return inches / targetUnit.getConversionFactor();
    }

    /**
     * Convert to another unit (UC5)
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double inches = convertToBaseUnit();
        double converted = convertFromBaseToTargetUnit(inches, targetUnit);

        return new Length(converted, targetUnit);
    }

    /**
     * UC6: Add another Length to this one.
     * Result returned in unit of first operand.
     */
    public Length add(Length thatLength) {
        if (thatLength == null) {
            throw new IllegalArgumentException("Cannot add null Length");
        }

        double thisInches = this.convertToBaseUnit();
        double thatInches = thatLength.convertToBaseUnit();

        double sumInches = thisInches + thatInches;

        double resultValue = convertFromBaseToTargetUnit(sumInches, this.unit);

        return new Length(resultValue, this.unit);
    }

    private boolean compare(Length thatLength) {
        double thisInches = this.convertToBaseUnit();
        double thatInches = thatLength.convertToBaseUnit();

        return Math.abs(thisInches - thatInches) < EPSILON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length that = (Length) o;
        return compare(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Math.round(convertToBaseUnit() / EPSILON)
        );
    }

    @Override
    public String toString() {
        return String.format("%.4f %s", value, unit);
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }
}