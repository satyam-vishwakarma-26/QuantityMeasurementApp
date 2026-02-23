package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Generic Equality
    public static <U extends IMeasurable>
    boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        return q1.equals(q2);
    }

    // Generic Conversion
    public static <U extends IMeasurable>
    Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        return quantity.convertTo(targetUnit);
    }

    // Generic Addition
    public static <U extends IMeasurable>
    Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {

        // Length Example
        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Equality: " +
                demonstrateEquality(length1, length2));

        System.out.println("Length Conversion: " +
                demonstrateConversion(length1, LengthUnit.INCHES));

        System.out.println("Length Addition: " +
                demonstrateAddition(length1, length2, LengthUnit.FEET));

        // Weight Example
        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " +
                demonstrateEquality(weight1, weight2));

        System.out.println("Weight Conversion: " +
                demonstrateConversion(weight1, WeightUnit.GRAM));

        System.out.println("Weight Addition: " +
                demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM));
        
     // ==========================
        // VOLUME OPERATIONS (UC11)
        // ==========================

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> v3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        // Equality
        System.out.println("1 L == 1000 mL ? " + v1.equals(v2));

        // Conversion
        System.out.println("1 Gallon in Litre: " +
                v3.convertTo(VolumeUnit.LITRE));

        // Addition (implicit)
        System.out.println("1 L + 1000 mL = " +
                v1.add(v2));

        // Addition (explicit target)
        System.out.println("1 L + 1 Gallon (in mL) = " +
                v1.add(v3, VolumeUnit.MILLILITRE));
    }
}