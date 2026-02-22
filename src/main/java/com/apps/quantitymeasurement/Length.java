package com.apps.quantitymeasurement;


public class Length {

    // Instance fields
    private final double value;
    private final LengthUnit unit;

   
    // Supported length units and their conversion factor to the base unit (inches).
     
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

        private final double toInches;

        LengthUnit(double toInches) {
            this.toInches = toInches;
        }

        public double getToInches() {
            return toInches;
        }
    }

    //constructor 
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Length unit must not be null");
        }
        this.value = value;
        this.unit = unit;
    }

    
     // Convert this length to the base unit (inches).
    
    private double toBaseInches() {
        return this.value * this.unit.getToInches();
    }

  
    public boolean compare(Length that) {
        if (that == null) return false;
        return Double.compare(this.toBaseInches(), that.toBaseInches()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        // 1. Reference check
        if (this == o) return true;

        // 2. Null & type check
        if (o == null || getClass() != o.getClass()) return false;

        // 3. Cast & compare normalized values (inches)
        Length that = (Length) o;
        return Double.compare(this.toBaseInches(), that.toBaseInches()) == 0;
    }

    @Override
    public int hashCode() {
        // Use the normalized (base) value for hash code so equal objects have same hash
        return Double.hashCode(this.toBaseInches());
    }

    @Override
    public String toString() {
        return value + " " + unit.name().toLowerCase();
    }

    // Getter methods (if needed)
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }
}