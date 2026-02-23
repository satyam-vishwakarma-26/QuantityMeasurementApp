package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

    // UC1–UC4: Equality
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1,
                                                      double v2, LengthUnit u2) {
        return demonstrateLengthEquality(new Length(v1, u1), new Length(v2, u2));
    }

    // UC5: Conversion
    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit fromUnit,
                                                     LengthUnit toUnit) {
        return Length.convert(value, fromUnit, toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    // UC6
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    // UC7
    public static Length demonstrateLengthAddition(Length l1, Length l2,
                                                   LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {

        System.out.println("1 ft == 12 in ? " +
                demonstrateLengthComparison(1.0, LengthUnit.FEET,
                        12.0, LengthUnit.INCHES));

        System.out.println("convert(3 yards -> feet) = " +
                demonstrateLengthConversion(3.0,
                        LengthUnit.YARDS,
                        LengthUnit.FEET));

        Length sum = demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES));

        System.out.println("1 ft + 12 in = " + sum);

        Length sumYards = demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        System.out.println("1 ft + 12 in (yards) = " + sumYards);
    }
}