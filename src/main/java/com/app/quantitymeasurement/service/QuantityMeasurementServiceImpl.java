package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.WeightUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    private Quantity<IMeasurable> convertDtoToQuantity(QuantityDTO dto) {
        String unit = dto.getUnit().trim().toUpperCase();
        double value = dto.getValue();

        switch (dto.getMeasurementType()) {
            case "LengthUnit":
                return new Quantity<>(value, LengthUnit.valueOf(unit));
            case "WeightUnit":
                return new Quantity<>(value, WeightUnit.valueOf(unit));
            case "VolumeUnit":
                return new Quantity<>(value, VolumeUnit.valueOf(unit));
            case "TemperatureUnit":
                return new Quantity<>(value, TemperatureUnit.valueOf(unit));
            default:
                throw new IllegalArgumentException(
                        "Unknown measurement type: " + dto.getMeasurementType());
        }
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO thisDTO, QuantityDTO thatDTO) {
        try {
            Quantity<IMeasurable> thisQty = convertDtoToQuantity(thisDTO);
            Quantity<IMeasurable> thatQty = convertDtoToQuantity(thatDTO);

            boolean result = thisQty.equals(thatQty);

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                    thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                    "compare", String.valueOf(result)
            );

            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            return saveAndReturnError(thisDTO, thatDTO, "compare", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO thisDTO, QuantityDTO targetDTO) {
        try {
            Quantity<IMeasurable> thisQty = convertDtoToQuantity(thisDTO);
            IMeasurable targetUnit = convertDtoToQuantity(targetDTO).getUnit();

            Quantity<IMeasurable> result = thisQty.convertTo(targetUnit);

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                    targetDTO.getValue(), targetDTO.getUnit(), targetDTO.getMeasurementType(),
                    "convert",
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    thisDTO.getMeasurementType()
            );

            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            return saveAndReturnError(thisDTO, targetDTO, "convert", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO thisDTO, QuantityDTO thatDTO) {
        try {
            Quantity<IMeasurable> result =
                    convertDtoToQuantity(thisDTO).add(convertDtoToQuantity(thatDTO));

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                    thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                    "add",
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    thisDTO.getMeasurementType()
            );

            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            return saveAndReturnError(thisDTO, thatDTO, "add", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO addWithTargetUnit(QuantityDTO thisDTO,
                                                    QuantityDTO thatDTO,
                                                    QuantityDTO targetDTO) {
        try {
            Quantity<IMeasurable> thisQty = convertDtoToQuantity(thisDTO);
            Quantity<IMeasurable> thatQty = convertDtoToQuantity(thatDTO);
            IMeasurable targetUnit = convertDtoToQuantity(targetDTO).getUnit();

            Quantity<IMeasurable> result = thisQty.add(thatQty);
            result = result.convertTo(targetUnit);

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                    thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                    "add",
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    thisDTO.getMeasurementType()
            );

            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            return saveAndReturnError(thisDTO, thatDTO, "add", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO thisDTO, QuantityDTO thatDTO) {
        try {
            Quantity<IMeasurable> result =
                    convertDtoToQuantity(thisDTO).subtract(convertDtoToQuantity(thatDTO));

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                    thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                    "subtract",
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    thisDTO.getMeasurementType()
            );

            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            return saveAndReturnError(thisDTO, thatDTO, "subtract", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO subtractWithTargetUnit(QuantityDTO thisDTO,
                                                         QuantityDTO thatDTO,
                                                         QuantityDTO targetDTO) {
        try {
            Quantity<IMeasurable> thisQty = convertDtoToQuantity(thisDTO);
            Quantity<IMeasurable> thatQty = convertDtoToQuantity(thatDTO);
            IMeasurable targetUnit = convertDtoToQuantity(targetDTO).getUnit();

            Quantity<IMeasurable> result = thisQty.subtract(thatQty);
            result = result.convertTo(targetUnit);

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                    thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                    "subtract",
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    thisDTO.getMeasurementType()
            );

            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            return saveAndReturnError(thisDTO, thatDTO, "subtract", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO multiply(QuantityDTO thisDTO, QuantityDTO thatDTO) {
        try {
            double result = thisDTO.getValue() * thatDTO.getValue();

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                    thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                    thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                    "multiply",
                    result,
                    thisDTO.getUnit(),
                    thisDTO.getMeasurementType()
            );

            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            return saveAndReturnError(thisDTO, thatDTO, "multiply", e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO thisDTO, QuantityDTO thatDTO) {
        Quantity<IMeasurable> thisQty = convertDtoToQuantity(thisDTO);
        Quantity<IMeasurable> thatQty = convertDtoToQuantity(thatDTO);

        double result = thisQty.divide(thatQty);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                "divide",
                result,
                thisDTO.getUnit(),
                thisDTO.getMeasurementType()
        );

        repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(entity);
    }

    private QuantityMeasurementDTO saveAndReturnError(QuantityDTO thisDTO,
                                                      QuantityDTO thatDTO,
                                                      String operation,
                                                      String errorMessage) {
        QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity(
                thisDTO.getValue(), thisDTO.getUnit(), thisDTO.getMeasurementType(),
                thatDTO.getValue(), thatDTO.getUnit(), thatDTO.getMeasurementType(),
                operation, errorMessage, true
        );

        repository.save(errorEntity);
        return QuantityMeasurementDTO.fromEntity(errorEntity);
    }

    // ✅ FIXED: All 4 methods properly implemented — no longer returning null/0

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {
        // Normalize to lowercase to match stored operation strings
        return QuantityMeasurementDTO.fromEntityList(
                repository.findByOperation(operation.toLowerCase()));
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType) {
        return QuantityMeasurementDTO.fromEntityList(
                repository.findByThisMeasurementType(measurementType));
    }

    @Override
    public long getCountByOperation(String operation) {
        // Normalize to lowercase to match stored operation strings
        return repository.countByOperationAndIsErrorFalse(operation.toLowerCase());
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        return QuantityMeasurementDTO.fromEntityList(
                repository.findByIsErrorTrue());
    }
}