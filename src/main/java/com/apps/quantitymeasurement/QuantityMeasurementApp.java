package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ---------------- LENGTH METHODS (UC1–UC8) ----------------
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit fromUnit,
                                                     LengthUnit toUnit) {
        return Length.convert(value, fromUnit, toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2,
                                                   LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

    // ---------------- WEIGHT METHODS (UC9) ----------------

    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        return w1.equals(w2);
    }

    public static double demonstrateWeightConversion(double value,
                                                     WeightUnit fromUnit,
                                                     WeightUnit toUnit) {
        return Weight.convert(value, fromUnit, toUnit);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
        return w1.add(w2);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2,
                                                   WeightUnit targetUnit) {
        return w1.add(w2, targetUnit);
    }

    // ---------------- MAIN METHOD (Demo) ----------------

    public static void main(String[] args) {

        System.out.println("1 kg == 1000 g ? " +
                new Weight(1.0, WeightUnit.KILOGRAM)
                        .equals(new Weight(1000.0, WeightUnit.GRAM)));

        System.out.println("1 kg to pounds = " +
                new Weight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.POUND));

        System.out.println("1 kg + 1000 g = " +
                new Weight(1.0, WeightUnit.KILOGRAM)
                        .add(new Weight(1000.0, WeightUnit.GRAM)));

        System.out.println("2 lb + 1 kg (in pounds) = " +
                new Weight(2.0, WeightUnit.POUND)
                        .add(new Weight(1.0, WeightUnit.KILOGRAM),
                                WeightUnit.POUND));
    }
}