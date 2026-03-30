package com.app.quantitymeasurement.dto;

/**
 * OperationType - Enum representing the different types of operations
 * that can be performed in the Quantity Measurement application.
 * This enum provides a type-safe way to represent operations throughout
 * the application, ensuring that only valid operation types are used.
*/
public enum OperationType {

    // ADD operation - Adds two quantities together.  e.g., 1 FEET + 12 INCHES = 2 FEET
   
    ADD,

    // SUBTRACT operation - Subtracts one quantity from another.  e.g., 2 FEET - 12 INCHES = 1 FEET
    
    SUBTRACT,

    // MULTIPLY operation - Multiplies two quantities together. e.g., 2 FEET * 2 = 4 FEET
     
    MULTIPLY,

    // DIVIDE operation - Divides one quantity by another. e.g., 4 FEET / 2 = 2 FEET
     
    DIVIDE,

    // COMPARE operation - Compares two quantities.  e.g., 1 FEET == 12 INCHES = true
    
    COMPARE,

    // CONVERT operation - Converts a quantity from one unit to another. e.g., 1 FEET = 12 INCHES
     
    CONVERT;

    /**
     * Returns a list of all valid operation type names as strings.
     * Useful for validation and documentation purposes.
     *
     * @return array of valid operation type names
     */
    public static String[] getValidOperationTypes() {
        return new String[]{
                ADD.name(),
                SUBTRACT.name(),
                MULTIPLY.name(),
                DIVIDE.name(),
                COMPARE.name(),
                CONVERT.name()
        };
    }

    /**
     * Converts a string to an OperationType enum value.
     * Returns null if the string does not match any operation type.
     * Case-insensitive comparison.
     *
     * @param operation the string to convert
     * @return the matching OperationType or null if not found
     */
    public static OperationType fromString(String operation) {
        if (operation == null || operation.isEmpty()) {
            return null;
        }
        try {
            return OperationType.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Checks if the given string is a valid operation type.
     * Case-insensitive comparison.
     *
     * @param operation the string to check
     * @return true if the string is a valid operation type
     */
    public static boolean isValid(String operation) {
        return fromString(operation) != null;
    }
}