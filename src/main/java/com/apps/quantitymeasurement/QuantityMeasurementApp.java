package com.apps.quantitymeasurement;

// Demo and utility methods for the QuantityMeasurement (Length) API.
 
public class QuantityMeasurementApp {

    // Generic equality helper (null-safe).
     
    public static boolean demonstrateLengthEquality(Length a, Length b) {
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    // Create two Length objects from raw values+units, compare them and print the result.
     
    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1,
                                                      double value2, Length.LengthUnit unit2) {
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        boolean equal = l1.equals(l2);
        System.out.printf("%s == %s ? %s%n", l1, l2, equal);
        return equal;
    }

    // Convert numeric value from 'from' unit to 'to' unit (static API).
     
    public static double demonstrateLengthConversion(double value, Length.LengthUnit from, Length.LengthUnit to) {
        double converted = Length.convert(value, from, to);
        System.out.printf("convert(%.6f %s -> %s) = %.6f%n", value, from.name(), to.name(), converted);
        return converted;
    }

    // Convert instance using instance method (overload).
     
    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {
        // UC1 / UC2 style demos
        demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, Length.LengthUnit.INCHES, 1.0, Length.LengthUnit.INCHES);

        // UC4 / UC5 demos (yards, centimeters)
        demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 36.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARDS);

        demonstrateLengthComparison(1.0, Length.LengthUnit.CENTIMETERS, 0.393701, Length.LengthUnit.INCHES);

        // conversion demos
        demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
        demonstrateLengthConversion(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);

        // instance conversion demo
        Length yards = new Length(2.0, Length.LengthUnit.YARDS);
        Length inches = demonstrateLengthConversion(yards, Length.LengthUnit.INCHES);
        System.out.println("Converted instance: " + inches);
    }
}