package com.apps.quantitymeasurement;

 // Provides small static helpers that the tests and main() can call.

public class QuantityMeasurementApp {

    //  returns true if two Length objects are equal (after conversion).
     
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        if (length1 == null || length2 == null) return false;
        return length1.equals(length2);
    }

    
     // check feet equality (both values given in feet)
     
    public static void demonstrateFeetEquality() {
        Length f1 = new Length(1.0, Length.LengthUnit.FEET);
        Length f2 = new Length(1.0, Length.LengthUnit.FEET);
        System.out.println("Feet equality (1.0 ft vs 1.0 ft): " + demonstrateLengthEquality(f1, f2));
    }

   
    // check inches equality (both values given in inches)
    
    public static void demonstrateInchesEquality() {
        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(1.0, Length.LengthUnit.INCHES);
        System.out.println("Inches equality (1.0 in vs 1.0 in): " + demonstrateLengthEquality(i1, i2));
    }

   
     // cross-unit comparison (1 foot == 12 inches)
     
    public static void demonstrateFeetInchesComparison() {
        Length f = new Length(1.0, Length.LengthUnit.FEET);
        Length i = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println("Cross-unit equality (1.0 ft vs 12.0 in): " + demonstrateLengthEquality(f, i));
    }

    
     // Main to show sample outputs for UC1/UC2/UC3.
     
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}