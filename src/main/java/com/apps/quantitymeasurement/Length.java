package com.apps.quantitymeasurement;


// Generic immutable Length (Quantity) class.

public final class Length {

    private static final double EPSILON = 1e-4;

    private final double value;
    private final LengthUnit unit;

    // Unit enum: conversion factor is "how many inches equals 1 unit".
     
    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(1.0/2.54); // 1 cm = 0.393701 inches

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        public double toInchesFactor() {
            return toInchesFactor;
        }
    }

    // Construct an immutable Length object.
   
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("unit must not be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("value must be a finite number");
        }
        this.value = value;
        this.unit = unit;
    }

   // Returns numeric value.
     
    public double getValue() {
        return value;
    }

    // Returns unit.
     
    public LengthUnit getUnit() {
        return unit;
    }
    // Convert this length to base unit (inches).
   
    private double toBaseInches() {
        return this.value * this.unit.toInchesFactor();
    }

    // Instance conversion: returns a NEW Length expressed in targetUnit.
    
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
        double baseInches = toBaseInches();
        double targetValue = baseInches / targetUnit.toInchesFactor();
        return new Length(targetValue, targetUnit);
    }

    // Static helper: convert numeric value from source unit to target unit and return numeric result.

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("source and target units must not be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("value must be a finite number");
        }
        double baseInches = value * source.toInchesFactor();
        return baseInches / target.toInchesFactor();
    }

  //Compare Two Length 
    public boolean compare(Length other) {
        if (other == null) return false;
        return Math.abs(this.toBaseInches() - other.toBaseInches()) < EPSILON;
    }

    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass()) return false;
        Length that = (Length) o;
        return Math.abs(this.toBaseInches() - that.toBaseInches()) < EPSILON;
    }

    @Override
    public int hashCode() {
        double normalized = Math.round(this.toBaseInches() * 1e6) / 1e6;
        return Double.hashCode(normalized);
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit.name());
    }
}