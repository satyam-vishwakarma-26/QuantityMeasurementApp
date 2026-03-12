package com.apps.quantitymeasurement;

import java.util.Objects;


 // UC9 – Weight Measurement Class

public class Weight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPS = 1e-5;

    public Weight(double value, WeightUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // ---------------- Equality ----------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weight that = (Weight) o;

        double thisInKg = this.toBaseUnit();
        double thatInKg = that.toBaseUnit();

        return Math.abs(thisInKg - thatInKg) < EPS;
    }

    @Override
    public int hashCode() {
        // Must match equals() logic
        double baseValue = toBaseUnit();
        return (int) Math.round(baseValue / EPS);
    }

    // ---------------- Conversion ----------------

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(converted, targetUnit);
    }

    public static double convert(double value,
                                 WeightUnit source,
                                 WeightUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Source/Target unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    // ---------------- Addition (Implicit Target = First Operand) ----------------

    public Weight add(Weight that) {
        if (that == null)
            throw new IllegalArgumentException("Weight to add cannot be null");

        return add(that, this.unit);
    }

    // ---------------- Addition (Explicit Target Unit) ----------------

    public Weight add(Weight that, WeightUnit targetUnit) {

        if (that == null)
            throw new IllegalArgumentException("Weight to add cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase = this.toBaseUnit() + that.toBaseUnit();

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(result, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.6f, %s)", value, unit);
    }
}