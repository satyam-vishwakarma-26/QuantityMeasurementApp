package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // GENERIC DEMONSTRATION METHODS

    // Equality
    public static <U extends IMeasurable>
    boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        return q1.equals(q2);
    }

    // Conversion
    public static <U extends IMeasurable>
    Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        return quantity.convertTo(targetUnit);
    }

    // Addition
    public static <U extends IMeasurable>
    Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.add(q2, targetUnit);
    }

    // Subtraction (UC12 + UC13 DRY)
    public static <U extends IMeasurable>
    Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.subtract(q2, targetUnit);
    }

    // Division (UC12 + UC13 DRY)
    public static <U extends IMeasurable>
    double demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
        return q1.divide(q2);
    }

    // MAIN METHOD

    public static void main(String[] args) {

        // LENGTH OPERATIONS

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("===== LENGTH OPERATIONS =====");

        System.out.println("Equality: " +
                demonstrateEquality(length1,
                        new Quantity<>(120.0, LengthUnit.INCHES)));

        System.out.println("Conversion (Feet → Inches): " +
                demonstrateConversion(length1, LengthUnit.INCHES));

        System.out.println("Addition: " +
                demonstrateAddition(length1, length2, LengthUnit.FEET));

        System.out.println("Subtraction: " +
                demonstrateSubtraction(length1, length2, LengthUnit.FEET));

        System.out.println("Division: " +
                demonstrateDivision(length1,
                        new Quantity<>(2.0, LengthUnit.FEET)));



        // WEIGHT OPERATIONS

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("\n===== WEIGHT OPERATIONS =====");

        System.out.println("Equality: " +
                demonstrateEquality(weight1,
                        new Quantity<>(10000.0, WeightUnit.GRAM)));

        System.out.println("Conversion (Kg → Gram): " +
                demonstrateConversion(weight1, WeightUnit.GRAM));

        System.out.println("Addition: " +
                demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM));

        System.out.println("Subtraction: " +
                demonstrateSubtraction(weight1, weight2, WeightUnit.KILOGRAM));

        System.out.println("Division: " +
                demonstrateDivision(weight1,
                        new Quantity<>(5.0, WeightUnit.KILOGRAM)));



        // VOLUME OPERATIONS

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("\n===== VOLUME OPERATIONS =====");

        System.out.println("Equality: " +
                demonstrateEquality(volume1,
                        new Quantity<>(5000.0, VolumeUnit.MILLILITRE)));

        System.out.println("Conversion (Gallon → Litre): " +
                demonstrateConversion(volume3, VolumeUnit.LITRE));

        System.out.println("Addition: " +
                demonstrateAddition(volume1, volume2, VolumeUnit.LITRE));

        System.out.println("Subtraction: " +
                demonstrateSubtraction(volume1, volume2, VolumeUnit.LITRE));

        System.out.println("Division: " +
                demonstrateDivision(volume1,
                        new Quantity<>(2.5, VolumeUnit.LITRE)));
    }
}