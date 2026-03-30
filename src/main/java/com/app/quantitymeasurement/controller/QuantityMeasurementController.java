package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(
    name = "Quantity Measurements",
    description = "REST API for quantity measurement operations — " +
                  "compare, convert, add, subtract, multiply, divide " +
                  "across Length, Weight, Volume and Temperature units."
)
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // — POST /compare ——————————————————————————————————

    @Operation(
        summary = "Compare two quantities",
        description = "Compares two quantities and returns true if they are equal. " +
                      "Supports LengthUnit, WeightUnit, VolumeUnit, TemperatureUnit."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Comparison successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input — bad unit or measurement type",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Two quantities to compare",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 1.0, "unit": "FEET", "measurementType": "LengthUnit" },
              "thatQuantityDTO": { "value": 12.0, "unit": "INCHES", "measurementType": "LengthUnit" }
            }
            """))
    )
    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compare(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.compare(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO()));
    }

    // — POST /convert ——————————————————————————————————

    @Operation(
        summary = "Convert a quantity to another unit",
        description = "Converts thisQuantity to the unit specified in thatQuantity. " +
                      "Example: 100 CELSIUS → FAHRENHEIT = 212.0"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Conversion successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Source quantity and target unit",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 100.0, "unit": "CELSIUS", "measurementType": "TemperatureUnit" },
              "thatQuantityDTO": { "value": 0.0, "unit": "FAHRENHEIT", "measurementType": "TemperatureUnit" }
            }
            """))
    )
    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convert(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.convert(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO()));
    }

    // — POST /add ——————————————————————————————————————

    @Operation(
        summary = "Add two quantities",
        description = "Adds two quantities of the same measurement type. " +
                      "Result is in the unit of thisQuantity. " +
                      "Example: 1 GALLON + 3.785 LITRE = 2.0 GALLON"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Addition successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Two quantities to add",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 1.0, "unit": "GALLON", "measurementType": "VolumeUnit" },
              "thatQuantityDTO": { "value": 3.785, "unit": "LITRE", "measurementType": "VolumeUnit" }
            }
            """))
    )
    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> add(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.add(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO()));
    }

    // — POST /add-with-target-unit —————————————————————

    @Operation(
        summary = "Add two quantities with explicit target unit",
        description = "Adds thisQuantity + thatQuantity and converts the result " +
                      "to the targetQuantity unit. " +
                      "Example: 1 FEET + 12 INCHES → target INCHES = 24.0 INCHES"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Addition with target unit successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Two quantities to add with a target unit for the result",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 1.0, "unit": "FEET", "measurementType": "LengthUnit" },
              "thatQuantityDTO": { "value": 12.0, "unit": "INCHES", "measurementType": "LengthUnit" },
              "targetQuantityDTO": { "value": 0.0, "unit": "INCHES", "measurementType": "LengthUnit" }
            }
            """))
    )
    @PostMapping("/add-with-target-unit")
    public ResponseEntity<QuantityMeasurementDTO> addWithTargetUnit(
            @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.addWithTargetUnit(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO(),
                quantityInputDTO.getTargetQuantityDTO()));
    }

    // — POST /subtract —————————————————————————————————

    @Operation(
        summary = "Subtract two quantities",
        description = "Subtracts thatQuantity from thisQuantity. " +
                      "Result is in the unit of thisQuantity. " +
                      "Example: 2 FEET - 12 INCHES = 1.0 FEET"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subtraction successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Two quantities to subtract",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 2.0, "unit": "FEET", "measurementType": "LengthUnit" },
              "thatQuantityDTO": { "value": 12.0, "unit": "INCHES", "measurementType": "LengthUnit" }
            }
            """))
    )
    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> subtract(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.subtract(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO()));
    }

    // — POST /subtract-with-target-unit ————————————————

    @Operation(
        summary = "Subtract two quantities with explicit target unit",
        description = "Subtracts thatQuantity from thisQuantity and converts result " +
                      "to the targetQuantity unit. " +
                      "Example: 2 FEET - 12 INCHES → target INCHES = 12.0 INCHES"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subtraction with target unit successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Two quantities to subtract with a target unit for the result",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 2.0, "unit": "FEET", "measurementType": "LengthUnit" },
              "thatQuantityDTO": { "value": 12.0, "unit": "INCHES", "measurementType": "LengthUnit" },
              "targetQuantityDTO": { "value": 0.0, "unit": "INCHES", "measurementType": "LengthUnit" }
            }
            """))
    )
    @PostMapping("/subtract-with-target-unit")
    public ResponseEntity<QuantityMeasurementDTO> subtractWithTargetUnit(
            @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.subtractWithTargetUnit(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO(),
                quantityInputDTO.getTargetQuantityDTO()));
    }

    // — POST /multiply —————————————————————————————————

    @Operation(
        summary = "Multiply two quantities",
        description = "Multiplies the values of two quantities. " +
                      "Example: 3 FEET × 2 FEET = 6.0"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Multiplication successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Two quantities to multiply",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 3.0, "unit": "FEET", "measurementType": "LengthUnit" },
              "thatQuantityDTO": { "value": 2.0, "unit": "FEET", "measurementType": "LengthUnit" }
            }
            """))
    )
    @PostMapping("/multiply")
    public ResponseEntity<QuantityMeasurementDTO> multiply(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.multiply(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO()));
    }

    // — POST /divide ———————————————————————————————————

    @Operation(
        summary = "Divide two quantities",
        description = "Divides thisQuantity by thatQuantity. " +
                      "Returns 500 if thatQuantity is zero. " +
                      "Example: 1 YARDS ÷ 1 FEET = 3.0"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Division successful",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Division by zero",
            content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Two quantities to divide",
        content = @Content(examples = @ExampleObject(value = """
            {
              "thisQuantityDTO": { "value": 1.0, "unit": "YARDS", "measurementType": "LengthUnit" },
              "thatQuantityDTO": { "value": 1.0, "unit": "FEET", "measurementType": "LengthUnit" }
            }
            """))
    )
    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> divide(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {
        return ResponseEntity.ok(service.divide(
                quantityInputDTO.getThisQuantityDTO(),
                quantityInputDTO.getThatQuantityDTO()));
    }

    // — GET endpoints ——————————————————————————————————

    @Operation(
        summary = "Get history by operation type",
        description = "Returns all measurement records for a given operation. " +
                      "Valid values: compare, convert, add, subtract, multiply, divide"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "History retrieved successfully",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class)))
    })
    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(
            @Parameter(description = "Operation name e.g. compare, add, divide")
            @PathVariable String operation) {
        return ResponseEntity.ok(service.getHistoryByOperation(operation));
    }

    @Operation(
        summary = "Get history by measurement type",
        description = "Returns all measurement records for a given type. " +
                      "Valid values: LengthUnit, WeightUnit, VolumeUnit, TemperatureUnit"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "History retrieved successfully",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class)))
    })
    @GetMapping("/history/type/{measurementType}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByMeasurementType(
            @Parameter(description = "Measurement type e.g. LengthUnit, VolumeUnit")
            @PathVariable String measurementType) {
        return ResponseEntity.ok(service.getHistoryByMeasurementType(measurementType));
    }

    @Operation(
        summary = "Get count of successful operations",
        description = "Returns the count of successful (non-error) operations " +
                      "for a given operation type."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    })
    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getCountByOperation(
            @Parameter(description = "Operation name e.g. divide, add")
            @PathVariable String operation) {
        return ResponseEntity.ok(service.getCountByOperation(operation));
    }

    @Operation(
        summary = "Get all errored measurements",
        description = "Returns all measurement records that resulted in an error, " +
                      "such as division by zero or invalid conversions."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Error history retrieved successfully",
            content = @Content(schema = @Schema(implementation = QuantityMeasurementDTO.class)))
    })
    @GetMapping("/history/errored")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {
        return ResponseEntity.ok(service.getErrorHistory());
    }
}