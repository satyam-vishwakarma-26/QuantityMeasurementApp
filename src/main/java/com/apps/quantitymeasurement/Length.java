package com.apps.quantitymeasurement;

import java.util.Objects;

 //UC8 – Refactored Length class
 
public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPS = 1e-6;

    public Length(double value, LengthUnit unit) {

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

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // ---------------- UC5: Conversion ----------------

    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Length(converted, targetUnit);
    }

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Source/Target unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    // ---------------- UC6: Addition (implicit target) ----------------

    public Length add(Length that) {
        return add(that, this.unit);
    }

    // ---------------- UC7: Addition (explicit target) ----------------

    public Length add(Length that, LengthUnit targetUnit) {

        if (that == null)
            throw new IllegalArgumentException("Length to add cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase =
                this.toBaseUnit() + that.toBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    }

    // ---------------- Equality ----------------

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Length that = (Length) o;

        return Math.abs(this.toBaseUnit() - that.toBaseUnit()) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit() / EPS));
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.4f, %s)", value, unit);
    }
}