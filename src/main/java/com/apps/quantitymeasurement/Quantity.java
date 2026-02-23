package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPS = 1e-5;

    public Quantity(double value, U unit) {
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

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private void validateOther(Quantity<U> other) {
        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");
    }

    // ===================================================
    // EQUALITY
    // ===================================================

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = this.toBaseUnit();
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPS;
    }

    @Override
    public int hashCode() {
        // Normalize to base unit for consistent hashing
        long normalized = Math.round(toBaseUnit() / EPS);
        return Objects.hash(unit.getClass(), normalized);
    }

    // ===================================================
    // CONVERSION
    // ===================================================

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    // ===================================================
    // ADDITION
    // ===================================================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateOther(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    // ===================================================
    // SUBTRACTION (UC12)
    // ===================================================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateOther(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double resultBase = this.toBaseUnit() - other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    // ===================================================
    // DIVISION (UC12)
    // ===================================================

    public double divide(Quantity<U> other) {
        validateOther(other);

        double divisor = other.toBaseUnit();

        if (Math.abs(divisor) < EPS)
            throw new ArithmeticException("Division by zero");

        return this.toBaseUnit() / divisor;
    }

    // ===================================================
    // TO STRING
    // ===================================================

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}