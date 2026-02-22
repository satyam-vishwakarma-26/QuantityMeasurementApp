package com.apps.quantitymeasurement;

/**
 * UC4 – Extended Generic Quantity Length Class
 * Supports FEET, INCHES, YARDS, CENTIMETERS
 * Base unit = INCHES
 */

public class Length {

    private final double value;
    private final LengthUnit unit;

    /**
     * Enum defining supported length units
     * Conversion factor is relative to base unit (INCHES)
     */
    public enum LengthUnit {

        INCHES(1.0),            // Base unit
        FEET(12.0),             // 1 ft = 12 in
        YARDS(36.0),            // 1 yd = 36 in
        CENTIMETERS(0.393701);  // 1 cm = 0.393701 in

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        public double getToInchesFactor() {
            return toInchesFactor;
        }
    }

    /**
     * Constructor
     */
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    /**
     * Convert current length to base unit (inches)
     */
    private double toBaseUnit() {
        return this.value * this.unit.getToInchesFactor();
    }

    /**
     * Compare two Length objects
     */
    public boolean compare(Length other) {
        if (other == null) return false;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object obj) {

        // Reflexive
        if (this == obj) return true;

        // Null and type check
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.name();
    }
}