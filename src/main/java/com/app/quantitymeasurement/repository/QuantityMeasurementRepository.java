package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    // Find by operation type e.g. "compare", "add", "convert"
    List<QuantityMeasurementEntity> findByOperation(String operation);

    // Find by measurement type e.g. "LengthUnit", "WeightUnit"
    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);

    // ✅ Fixed - was findByCreatedAfter
    List<QuantityMeasurementEntity> findByCreatedAtAfter(LocalDateTime date);

    // Custom JPQL query for successful operations
    @Query("SELECT q FROM QuantityMeasurementEntity q " +
           "WHERE q.operation = :operation " +
           "AND q.isError = false")
    List<QuantityMeasurementEntity> findSuccessfulByOperation(
            @Param("operation") String operation);

    // Count successful operations by operation type
    long countByOperationAndIsErrorFalse(String operation);

    // Find all errored records
    List<QuantityMeasurementEntity> findByIsErrorTrue();
}