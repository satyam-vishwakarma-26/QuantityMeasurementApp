package com.apps.quantitymeasurement;

// UC10 – Common Unit Interface

public interface IMeasurable  {

    // Conversion factor relative to base unit -
    double getConversionFactor();

    // Convert value in THIS unit to base unit
    double convertToBaseUnit(double value);

    // Convert value from base unit to THIS unit
    double convertFromBaseUnit(double baseValue);

    // Optional readable name
    String getUnitName();
}