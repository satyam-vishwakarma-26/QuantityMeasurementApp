package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {

    // Compare two quantities and return result as DTO
    QuantityMeasurementDTO compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    // Convert a quantity to target unit and return result as DTO
    QuantityMeasurementDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    // Add two quantities and return result as DTO
    QuantityMeasurementDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    // Subtract two quantities and return result as DTO
    QuantityMeasurementDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    // Multiply two quantities and return result as DTO
    QuantityMeasurementDTO multiply(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    // Divide two quantities and return result as DTO
    QuantityMeasurementDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    // Get history of all measurements by operation type e.g. "COMPARE", "ADD"
    List<QuantityMeasurementDTO> getHistoryByOperation(String operation);

    // Get history of all measurements by measurement type e.g. "LengthUnit"
    List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType);

    // Get count of successful operations by operation type
    long getCountByOperation(String operation);

    // Get history of all errored measurements
    List<QuantityMeasurementDTO> getErrorHistory();

	QuantityMeasurementDTO addWithTargetUnit(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
			QuantityDTO targetQuantityDTO);

	QuantityMeasurementDTO subtractWithTargetUnit(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
			QuantityDTO targetQuantityDTO);
}