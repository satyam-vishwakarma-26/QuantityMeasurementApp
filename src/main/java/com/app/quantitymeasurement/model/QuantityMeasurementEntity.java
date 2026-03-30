package com.app.quantitymeasurement.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name = "quantity_measurement_entity", indexes = {
        @Index(name = "idx_operation", columnList = "operation"),
        @Index(name = "idx_measurement_type", columnList = "this_measurement_type"),
        @Index(name = "idx_created_at", columnList = "created_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "this_value", nullable = false)
    public double thisValue;

    @Column(name = "this_unit", nullable = false)
    public String thisUnit;

    @Column(name = "this_measurement_type", nullable = false)
    public String thisMeasurementType;

    @Column(name = "that_value", nullable = false)
    public double thatValue;

    @Column(name = "that_unit", nullable = false)
    public String thatUnit;

    @Column(name = "that_measurement_type", nullable = false)
    public String thatMeasurementType;

    // e.g., "COMPARE", "CONVERT", "ADD", "SUBTRACT", "DIVIDE"
    @Column(name = "operation", nullable = false)
    public String operation;

    @Column(name = "result_value")
    public double resultValue;

    @Column(name = "result_unit")
    public String resultUnit;

    @Column(name = "result_measurement_type")
    public String resultMeasurementType;

    // For comparison results like "Equal" or "Not Equal"
    @Column(name = "result_string")
    public String resultString;

    // Flag to indicate if an error occurred during the operation
    @Column(name = "is_error")
    public boolean isError;

    // For capturing any error messages during operations
    @Column(name = "error_message")
    public String errorMessage;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Constructor to initialize the QuantityMeasurementEntity with the given
     * quantities, operation, and result for a single operand operation
     * (e.g., comparison and conversion).
     *
     * This constructor is used predominantly for Comparison operations where both
     * thisQuantity and thatQuantity are involved in the operation and a result
     * is produced.
     *
     * @param thisValue
     * @param thisUnit
     * @param thisMeasurementType
     * @param thatValue
     * @param thatUnit
     * @param thatMeasurementType
     * @param operation
     * @param resultString
     */
    public QuantityMeasurementEntity(double thisValue,
                                     String thisUnit,
                                     String thisMeasurementType,
                                     double thatValue,
                                     String thatUnit,
                                     String thatMeasurementType,
                                     String operation,
                                     String resultString) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.resultString = resultString;
        this.isError = false;
    }

    /**
     * Constructor to initialize the QuantityMeasurementEntity with the given
     * quantities, operation, and result for a double operand operation
     * (e.g., addition, subtraction, division).
     *
     * This constructor is used predominantly for Addition and Subtraction
     * operations where both thisQuantity and thatQuantity are involved in the
     * operation and a result is produced.
     *
     * @param thisValue
     * @param thisUnit
     * @param thisMeasurementType
     * @param thatValue
     * @param thatUnit
     * @param thatMeasurementType
     * @param operation
     * @param resultValue
     * @param resultUnit
     * @param resultMeasurementType
     */
    public QuantityMeasurementEntity(double thisValue,
                                     String thisUnit,
                                     String thisMeasurementType,
                                     double thatValue,
                                     String thatUnit,
                                     String thatMeasurementType,
                                     String operation,
                                     double resultValue,
                                     String resultUnit,
                                     String resultMeasurementType) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.resultMeasurementType = resultMeasurementType;
        this.isError = false;
    }

    /**
     * Constructor to initialize the QuantityMeasurementEntity with the given
     * quantities, operation, and result.
     *
     * This constructor is used predominantly for Comparison operations where both
     * thisQuantity and thatQuantity are involved in the operation and a result
     * is produced.
     *
     * @param thisValue
     * @param thisUnit
     * @param thisMeasurementType
     * @param thatValue
     * @param thatUnit
     * @param thatMeasurementType
     * @param operation
     * @param resultValue
     * @param resultString
     * @param resultMeasurementType
     */
    public QuantityMeasurementEntity(double thisValue,
                                     String thisUnit,
                                     String thisMeasurementType,
                                     double thatValue,
                                     String thatUnit,
                                     String thatMeasurementType,
                                     String operation,
                                     double resultValue,
                                     String resultString,
                                     String resultUnit,
                                     String resultMeasurementType) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.resultValue = resultValue;
        this.resultString = resultString;
        this.resultUnit = resultUnit;
        this.resultMeasurementType = resultMeasurementType;
        this.isError = false;
    }

    /**
     * Constructor to initialize the QuantityMeasurementEntity with the given
     * quantities, operation, and error information.
     *
     * This constructor is used predominantly for error cases where an operation fails.
     *
     * @param thisValue
     * @param thisUnit
     * @param thisMeasurementType
     * @param thatValue
     * @param thatUnit
     * @param thatMeasurementType
     * @param operation
     * @param errorMessage
     * @param isError
     */
    public QuantityMeasurementEntity(double thisValue,
                                     String thisUnit,
                                     String thisMeasurementType,
                                     double thatValue,
                                     String thatUnit,
                                     String thatMeasurementType,
                                     String operation,
                                     String errorMessage,
                                     boolean isError) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.errorMessage = errorMessage;
        this.isError = isError;
    }
}