package com.apps.quantitymeasurement;

public interface IMeasurable {

    // Mandatory conversion methods
    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    double getConversionFactor();

    // Arithmetic Support (UC14)

    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // Default: allow all operations
    }
}

@FunctionalInterface
 interface SupportsArithmetic {
    boolean isSupported();
}