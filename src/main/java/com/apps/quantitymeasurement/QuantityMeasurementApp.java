package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1,
                                                      double v2, LengthUnit u2) {
        return demonstrateLengthEquality(new Length(v1, u1),
                                         new Length(v2, u2));
    }

    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit fromUnit,
                                                     LengthUnit toUnit) {
        return Length.convert(value, fromUnit, toUnit);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2,
                                                   LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {

        System.out.println(
                new Length(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES));

        System.out.println(
                new Length(1.0, LengthUnit.FEET)
                        .add(new Length(12.0, LengthUnit.INCHES),
                             LengthUnit.FEET));

        System.out.println(
                new Length(36.0, LengthUnit.INCHES)
                        .equals(new Length(1.0, LengthUnit.YARDS)));

        System.out.println(
                new Length(2.54, LengthUnit.CENTIMETERS)
                        .convertTo(LengthUnit.INCHES));
    }
}