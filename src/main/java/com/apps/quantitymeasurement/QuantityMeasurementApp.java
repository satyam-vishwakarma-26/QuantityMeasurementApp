package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ================================
    // GENERIC DEMONSTRATION METHODS
    // ================================

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

    // Subtraction (UC12)
    public static <U extends IMeasurable>
    Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.subtract(q2, targetUnit);
    }

    // Division (UC12)
    public static <U extends IMeasurable>
    double demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
        return q1.divide(q2);
    }

    // ================================
    // MAIN METHOD
    // ================================

    public static void main(String[] args) {

        // =================================
        // LENGTH OPERATIONS
        // =================================

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Length Equality: " +
                demonstrateEquality(length1,
                        new Quantity<>(120.0, LengthUnit.INCHES)));

        System.out.println("Length Conversion: " +
                demonstrateConversion(length1, LengthUnit.INCHES));

        System.out.println("Length Addition: " +
                demonstrateAddition(length1, length2, LengthUnit.FEET));

        System.out.println("Length Subtraction: " +
                demonstrateSubtraction(length1, length2, LengthUnit.FEET));

        System.out.println("Length Division: " +
                demonstrateDivision(length1,
                        new Quantity<>(2.0, LengthUnit.FEET)));

        // =================================
        // WEIGHT OPERATIONS
        // =================================

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("\nWeight Equality: " +
                demonstrateEquality(weight1,
                        new Quantity<>(10000.0, WeightUnit.GRAM)));

        System.out.println("Weight Conversion: " +
                demonstrateConversion(weight1, WeightUnit.GRAM));

        System.out.println("Weight Addition: " +
                demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM));

        System.out.println("Weight Subtraction: " +
                demonstrateSubtraction(weight1, weight2, WeightUnit.KILOGRAM));

        System.out.println("Weight Division: " +
                demonstrateDivision(weight1,
                        new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        // =================================
        // VOLUME OPERATIONS (UC11 + UC12)
        // =================================

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("\nVolume Equality: " +
                demonstrateEquality(volume1,
                        new Quantity<>(5000.0, VolumeUnit.MILLILITRE)));

        System.out.println("Volume Conversion: " +
                demonstrateConversion(volume3, VolumeUnit.LITRE));

        System.out.println("Volume Addition: " +
                demonstrateAddition(volume1, volume2, VolumeUnit.LITRE));

        System.out.println("Volume Subtraction: " +
                demonstrateSubtraction(volume1, volume2, VolumeUnit.LITRE));

        System.out.println("Volume Division: " +
                demonstrateDivision(volume1,
                        new Quantity<>(2.5, VolumeUnit.LITRE)));
    }
}