package com.apps.quantitymeasurement;

/**
 * UC6 – Addition Operations Between Length Measurements
 */
public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1,
                                                      double value2, Length.LengthUnit unit2) {
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value,
                                                     Length.LengthUnit fromUnit,
                                                     Length.LengthUnit toUnit) {
        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     Length.LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    /**
     * UC6: Demonstrate addition of two lengths.
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        return length1.add(length2);
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = demonstrateLengthAddition(l1, l2);

        System.out.println("Result: " + result); // Expected: 2.0 FEET
    }
}