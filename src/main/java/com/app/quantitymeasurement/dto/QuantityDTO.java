package com.app.quantitymeasurement.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuantityDTO {

    // Cannot be null
    @NotNull(message = "Value cannot be null")
    private Double value;

    // The unit of the quantity e.g. FEET, INCHES, KILOGRAM
    @NotEmpty(message = "Unit cannot be empty")
    private String unit;

    // Must be one of LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit
    @NotEmpty(message = "Measurement type cannot be empty")
    @Pattern(
            regexp = "LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit",
            message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"
    )
    private String measurementType;

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    // Custom validation - ensures unit matches the measurement type
    @AssertTrue(message = "Unit must be valid for the specified measurement type")
    public boolean isUnitValidForMeasurementType() {

        // Skip validation if fields are null or empty
        if (unit == null || measurementType == null || unit.isEmpty()) {
            return true;
        }

        // Trim and uppercase for safe comparison
        String unitUpper = unit.trim().toUpperCase();

        switch (measurementType) {
            case "LengthUnit":
                return unitUpper.equals("FEET") ||
                       unitUpper.equals("INCHES") ||
                       unitUpper.equals("CENTIMETERS") ||
                       unitUpper.equals("METERS") ||
                       unitUpper.equals("KILOMETERS") ||
                       unitUpper.equals("MILES") ||
                       unitUpper.equals("YARDS");

            case "VolumeUnit":
                return unitUpper.equals("LITERS") ||
                       unitUpper.equals("MILLILITERS") ||
                       unitUpper.equals("GALLONS") ||
                       unitUpper.equals("GALLON") ||    
                       unitUpper.equals("LITRE") ||     
                       unitUpper.equals("LITRES") ||    
                       unitUpper.equals("MILLILITRE") || 
                       unitUpper.equals("CUBIC_FEET") ||
                       unitUpper.equals("CUBIC_INCHES") ||
                       unitUpper.equals("CUBIC_METERS") ||
                       unitUpper.equals("CUBIC_CENTIMETERS") ||
                       unitUpper.equals("FLUID_OUNCES") ||
                       unitUpper.equals("CUPS") ||
                       unitUpper.equals("PINTS") ||
                       unitUpper.equals("QUARTS");

            case "WeightUnit":
                return unitUpper.equals("KILOGRAM") ||
                       unitUpper.equals("GRAMS") ||
                       unitUpper.equals("MILLIGRAMS") ||
                       unitUpper.equals("POUNDS") ||
                       unitUpper.equals("OUNCES") ||
                       unitUpper.equals("TONS");

            case "TemperatureUnit":
                return unitUpper.equals("CELSIUS") ||
                       unitUpper.equals("FAHRENHEIT") ||
                       unitUpper.equals("KELVIN");

            default:
                return false;
        }
    }
}