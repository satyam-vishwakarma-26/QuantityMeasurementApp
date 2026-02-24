package com.apps.quantitymeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;


 //UC13: Centralized arithmetic logic (DRY enforced)


public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPS = 1e-5;

    // CONSTRUCTOR

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

    // ENUM FOR ARITHMETIC OPERATIONS (DRY)

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPS)
                throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    // CENTRALIZED VALIDATION (DRY)

    private void validateArithmeticOperands(Quantity<U> other,
                                            U targetUnit,
                                            boolean targetRequired) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Value must be finite");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // CORE BASE ARITHMETIC (DRY)

    private double performBaseArithmetic(Quantity<U> other,
                                         ArithmeticOperation operation) {

        double baseThis = this.toBaseUnit();
        double baseOther = other.toBaseUnit();

        return operation.compute(baseThis, baseOther);
    }

    // EQUALITY

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        double baseThis = this.toBaseUnit();
        double baseOther = other.unit.convertToBaseUnit(other.value);

        return Math.abs(baseThis - baseOther) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), toBaseUnit());
    }

    // CONVERSION

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        // ❌ No rounding
        return new Quantity<>(converted, targetUnit);
    }

    // ADDITION

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double finalValue =
                targetUnit.convertFromBaseUnit(baseResult);

        // ❌ No rounding
        return new Quantity<>(finalValue, targetUnit);
    }

    // SUBTRACTION

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double finalValue =
                targetUnit.convertFromBaseUnit(baseResult);

        // ❌ No rounding
        return new Quantity<>(finalValue, targetUnit);
    }

    // DIVISION

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // TO STRING

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}