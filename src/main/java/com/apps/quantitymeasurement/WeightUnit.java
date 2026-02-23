package com.apps.quantitymeasurement;


 //UC9 – Standalone WeightUnit Enum
 
public enum WeightUnit {

    KILOGRAM(1.0),        // Base unit
    GRAM(0.001),          // 1 g = 0.001 kg
    POUND(0.453592);      // 1 lb ≈ 0.453592 kg

    private final double toKilogramFactor;

    WeightUnit(double toKilogramFactor) {
        this.toKilogramFactor = toKilogramFactor;
    }

    // Convert value in THIS unit to base unit (kilogram)
    
    public double convertToBaseUnit(double value) {
        return value * toKilogramFactor;
    }

    // Convert value from base unit (kilogram) to THIS unit
     
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKilogramFactor;
    }

    public double getConversionFactor() {
        return toKilogramFactor;
    }
}