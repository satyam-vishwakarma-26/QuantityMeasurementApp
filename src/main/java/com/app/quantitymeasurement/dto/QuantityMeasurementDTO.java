package com.app.quantitymeasurement.dto;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * QuantityMeasurementDTO - Data Transfer Object for QuantityMeasurementEntity.
 * This DTO is used to transfer data between the service layer and the REST
 * controllers, providing a simplified and decoupled representation of the entity.
 *
 * The DTO contains static factory methods for converting between the entity
 * and DTO representations. This provides a clear and centralized way to handle
 * the conversion logic, making it easier to maintain and ensuring consistency
 * across the application.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementDTO {

    // The value of the first quantity.
     
    private double thisValue;

    // The unit of the first quantity (this quantity).
     
    private String thisUnit;

    // The measurement type of the first quantity (this quantity).
     
    private String thisMeasurementType;

    // The value of the second quantity (that quantity).
     
    private double thatValue;

    // The unit of the second quantity (that quantity).
     
    private String thatUnit;

    // The measurement type of the second quantity (that quantity).
     
    private String thatMeasurementType;

    // The operation performed on the quantities.
     
    private String operation;

    // The numeric result of the operation. Used for operations like ADD, SUBTRACT, CONVERT, DIVIDE.
     
    private double resultValue;

    // The unit of the result. e.g., "FEET", "INCHES", "KILOGRAM", "CELSIUS"
    
    private String resultUnit;

    // The measurement type of the result.e.g., "LengthUnit", "VolumeUnit", "WeightUnit", "TemperatureUnit"
    
    private String resultMeasurementType;

    // The string result of the operation. Used for comparison results like "true" or "false".
     
    private String resultString;

    // The error message if an error occurred during the operation.
    
    private String errorMessage;

    // Flag to indicate if an error occurred during the operation.
     
    private boolean error;

    // Static Factory Methods

    /**
     * Converts a QuantityMeasurementEntity to a QuantityMeasurementDTO.
     * This method is used to convert the entity to a DTO for API responses.
     *
     * @param entity the QuantityMeasurementEntity to convert
     * @return a QuantityMeasurementDTO with the same data as the entity
     */
    public static QuantityMeasurementDTO fromEntity(QuantityMeasurementEntity entity) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(entity.getThisValue());
        dto.setThisUnit(entity.getThisUnit());
        dto.setThisMeasurementType(entity.getThisMeasurementType());
        dto.setThatValue(entity.getThatValue());
        dto.setThatUnit(entity.getThatUnit());
        dto.setThatMeasurementType(entity.getThatMeasurementType());
        dto.setOperation(entity.getOperation());
        dto.setResultValue(entity.getResultValue());
        dto.setResultUnit(entity.getResultUnit());
        dto.setResultMeasurementType(entity.getResultMeasurementType());
        dto.setResultString(entity.getResultString());
        dto.setErrorMessage(entity.getErrorMessage());
        dto.setError(entity.isError());
        return dto;
    }

    /**
     * Converts this QuantityMeasurementDTO to a QuantityMeasurementEntity.
     * This method is used when saving or updating data in the database
     * using the repository layer.
     *
     * @return a QuantityMeasurementEntity with the same data as this DTO
     */
    public QuantityMeasurementEntity toEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(this.thisValue);
        entity.setThisUnit(this.thisUnit);
        entity.setThisMeasurementType(this.thisMeasurementType);
        entity.setThatValue(this.thatValue);
        entity.setThatUnit(this.thatUnit);
        entity.setThatMeasurementType(this.thatMeasurementType);
        entity.setOperation(this.operation);
        entity.setResultValue(this.resultValue);
        entity.setResultUnit(this.resultUnit);
        entity.setResultMeasurementType(this.resultMeasurementType);
        entity.setResultString(this.resultString);
        entity.setErrorMessage(this.errorMessage);
        entity.setError(this.error);
        return entity;
    }

    /**
     * Converts a list of QuantityMeasurementEntity objects to a list of
     * QuantityMeasurementDTO objects using Java Stream API.
     *
     * @param entities the list of QuantityMeasurementEntity objects to convert
     * @return a list of QuantityMeasurementDTO objects
     */
    public static List<QuantityMeasurementDTO> fromEntityList(
            List<QuantityMeasurementEntity> entities) {
        return entities.stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of QuantityMeasurementDTO objects to a list of
     * QuantityMeasurementEntity objects using Java Stream API.
     *
     * @param dtos the list of QuantityMeasurementDTO objects to convert
     * @return a list of QuantityMeasurementEntity objects
     */
    public static List<QuantityMeasurementEntity> toEntityList(
            List<QuantityMeasurementDTO> dtos) {
        return dtos.stream()
                .map(QuantityMeasurementDTO::toEntity)
                .collect(Collectors.toList());
    }
}