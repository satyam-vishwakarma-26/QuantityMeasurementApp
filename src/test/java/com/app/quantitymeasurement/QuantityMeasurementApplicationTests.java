package com.app.quantitymeasurement;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;

import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.assertj.core.data.Offset;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

// Integration tests for the Quantity Measurement application using Spring Boot's testing

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuantityMeasurementApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // — Helpers —————————————————————————————————————————

    private String baseUrl() {
        return "http://localhost:" + port + "/api/v1/quantities";
    }

    /**
     * Builds a QuantityInputDTO with thisQuantity and thatQuantity only.
     * Used for compare, add, subtract, divide, convert.
     */
    private QuantityInputDTO input(
            double thisValue, String thisUnit, String thisMeasurementType,
            double thatValue, String thatUnit, String thatMeasurementType) {
        QuantityInputDTO inputDTO = new QuantityInputDTO();
        inputDTO.setThisQuantityDTO(new QuantityDTO(thisValue, thisUnit, thisMeasurementType));
        inputDTO.setThatQuantityDTO(new QuantityDTO(thatValue, thatUnit, thatMeasurementType));
        return inputDTO;
    }

    /**
     * Builds a QuantityInputDTO with thisQuantity, thatQuantity, and targetUnit.
     * Used for add-with-target-unit and subtract-with-target-unit.
     */
    private QuantityInputDTO inputWithTarget(
            double thisValue, String thisUnit, String thisMeasurementType,
            double thatValue, String thatUnit, String thatMeasurementType,
            double targetValue, String targetUnit, String targetMeasurementType) {
        QuantityInputDTO inputDTO = new QuantityInputDTO();
        inputDTO.setThisQuantityDTO(new QuantityDTO(thisValue, thisUnit, thisMeasurementType));
        inputDTO.setThatQuantityDTO(new QuantityDTO(thatValue, thatUnit, thatMeasurementType));
        inputDTO.setTargetQuantityDTO(new QuantityDTO(targetValue, targetUnit, targetMeasurementType));
        return inputDTO;
    }

    private HttpEntity<QuantityInputDTO> jsonEntity(QuantityInputDTO body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    // — 1. Context ————————————————————————————————————

    @Test
    @Order(1)
    @DisplayName("Application context loads and test server starts")
    void contextLoads() {
        assertThat(restTemplate).isNotNull();
        assertThat(port).isGreaterThan(0);
        System.out.println("✅ Test server on port: " + port);
    }

    // — 2. POST /compare ——————————————————————————————

    @Test
    @Order(2)
    @DisplayName("POST /compare - 1 foot equals 12 inches → result is true")
    void testCompare_FootEqualsInches() {
        QuantityInputDTO body = input(
                1.0, "FEET", "LengthUnit",
                12.0, "INCHES", "LengthUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResultString()).isEqualTo("true");
    }

    @Test
    @Order(3)
    @DisplayName("POST /compare - 1 foot does NOT equal 1 inch → result is false")
    void testCompare_FootNotEqualInch() {
        QuantityInputDTO body = input(
                1.0, "FEET", "LengthUnit",
                1.0, "INCHES", "LengthUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResultString()).isEqualTo("false");
    }

    @Test
    @Order(4)
    @DisplayName("POST /compare - 1 gallon equals 3.785 litres → result is true")
    void testCompare_GallonEqualsLitres() {
        QuantityInputDTO body = input(
                1.0, "GALLON", "VolumeUnit",
                3.785, "LITRE", "VolumeUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        // EPS set to 1e-2 in Quantity.java to handle floating point difference
        assertThat(response.getBody().getResultString()).isEqualTo("true");
    }

    @Test
    @Order(5)
    @DisplayName("POST /compare - 212 Fahrenheit equals 100 Celsius → result is true")
    void testCompare_FahrenheitEqualsCelsius() {
        QuantityInputDTO body = input(
                212.0, "FAHRENHEIT", "TemperatureUnit",
                100.0, "CELSIUS", "TemperatureUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResultString()).isEqualTo("true");
    }

    // — 3. POST /convert ——————————————————————————————

    @Test
    @Order(6)
    @DisplayName("POST /convert - convert 100 Celsius to Fahrenheit")
    void testConvert_CelsiusToFahrenheit() {
        QuantityInputDTO body = input(
                100.0, "CELSIUS", "TemperatureUnit",
                0.0, "FAHRENHEIT", "TemperatureUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/convert", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        // 100°C = 212°F - use isCloseTo for floating point safety
        assertThat(response.getBody().getResultValue())
                .isCloseTo(212.0, Offset.offset(0.01));
    }

    // — 4. POST /add ——————————————————————————————————

    @Test
    @Order(7)
    @DisplayName("POST /add - add 1 gallon and 3.785 litres = 2 gallons")
    void testAdd_GallonAndLitres() {
        QuantityInputDTO body = input(
                1.0, "GALLON", "VolumeUnit",
                3.785, "LITRE", "VolumeUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/add", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        // Use isCloseTo for floating point difference in gallon/litre conversion
        assertThat(response.getBody().getResultValue())
                .isCloseTo(2.0, Offset.offset(0.01));
    }

    // — 5. POST /add-with-target-unit —————————————————

    @Test
    @Order(8)
    @DisplayName("POST /add-with-target-unit - add 1 foot + 12 inches, target INCH = 24 inches")
    void testAddWithTargetUnit_FootAndInchesToInches() {
        // thisDTO=1 FEET, thatDTO=12 INCHES, targetDTO=INCHES
        // 1 FEET = 12 INCHES + 12 INCHES = 24 INCHES in target unit
        QuantityInputDTO body = inputWithTarget(
                1.0, "FEET", "LengthUnit",
                12.0, "INCHES", "LengthUnit",
                0.0, "INCHES", "LengthUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/add-with-target-unit", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResultValue())
                .isCloseTo(24.0, Offset.offset(0.01));
    }

    // — 6. POST /subtract —————————————————————————————

    @Test
    @Order(9)
    @DisplayName("POST /subtract - subtract 12 inches from 2 feet = 1 foot")
    void testSubtract_FeetMinusInches() {
        QuantityInputDTO body = input(
                2.0, "FEET", "LengthUnit",
                12.0, "INCHES", "LengthUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/subtract", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResultValue())
                .isCloseTo(1.0, Offset.offset(0.01));
    }

    // — 7. POST /subtract-with-target-unit ————————————

    @Test
    @Order(10)
    @DisplayName("POST /subtract-with-target-unit - 2 feet minus 12 inches, target INCH = 12 inches")
    void testSubtractWithTargetUnit() {
        // thisDTO=2 FEET, thatDTO=12 INCHES, targetDTO=INCHES
        // 2 FEET = 24 INCHES - 12 INCHES = 12 INCHES in target unit
        QuantityInputDTO body = inputWithTarget(
                2.0, "FEET", "LengthUnit",
                12.0, "INCHES", "LengthUnit",
                0.0, "INCHES", "LengthUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/subtract-with-target-unit", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResultValue())
                .isCloseTo(12.0, Offset.offset(0.01));
    }

    // — 8. POST /divide ———————————————————————————————

    @Test
    @Order(11)
    @DisplayName("POST /divide - 1 yard divided by 1 foot = 3.0")
    void testDivide_YardByFoot() {
        QuantityInputDTO body = input(
                1.0, "YARDS", "LengthUnit",
                1.0, "FEET", "LengthUnit");

        ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
                baseUrl() + "/divide", HttpMethod.POST,
                jsonEntity(body), QuantityMeasurementDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResultValue())
                .isCloseTo(3.0, Offset.offset(0.01));
    }

 // — 9. GET /history/operation/{operation} —————————

    @Test
    @Order(12)
    @DisplayName("GET /history/operation/CONVERT - returns list of CONVERT operations")
    void testGetHistoryByOperation_Convert() {
        // Use uppercase to match what the service stores in the DB
        ResponseEntity<QuantityMeasurementDTO[]> response = restTemplate.exchange(
                baseUrl() + "/history/operation/convert",
                HttpMethod.GET,
                null,
                QuantityMeasurementDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    // — 10. GET /history/type/{type} ——————————————————

    @Test
    @Order(13)
    @DisplayName("GET /history/type/TemperatureUnit - returns history for TemperatureUnit measurements")
    void testGetHistoryByType_Temperature() {
        ResponseEntity<QuantityMeasurementDTO[]> response = restTemplate.exchange(
                baseUrl() + "/history/type/TemperatureUnit",
                HttpMethod.GET,
                null,
                QuantityMeasurementDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    // — 11. GET /count/{operation} ————————————————————

    @Test
    @Order(14)
    @DisplayName("GET /count/DIVIDE - returns count of DIVIDE operations > 0")
    void testGetOperationCount_Divide() {
        ResponseEntity<Long> response = restTemplate.exchange(
                baseUrl() + "/count/divide",
                HttpMethod.GET,
                null,
                Long.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isGreaterThan(0L);
    }

    // — 12. CREATE and GET /history/errored ———————————

    @Test
    @Order(15)
    @DisplayName("POST /divide - 1 yard divided by 0 foot results in error, " +
                 "GET /history/errored returns that error")
    @SuppressWarnings("unchecked")
    void testDivide_YardByFeet_Error() {
        QuantityInputDTO body = input(
                1.0, "YARDS", "LengthUnit",
                0.0, "FEET", "LengthUnit");

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/divide", HttpMethod.POST,
                jsonEntity(body), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).contains("Division by zero");

        // Now check that the error was recorded in history
        ResponseEntity<List> errorHistoryResponse = restTemplate.getForEntity(
                baseUrl() + "/history/errored", List.class);
        assertThat(errorHistoryResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    // — 13. Validation - 400 on invalid fields ————————

    @Test
    @Order(16)
    @DisplayName("POST /compare - 1 foot equals 12 inches → validation fails " +
                 "Unit must be valid for the specified measurement type")
    void testCompare_FootEqualsInches_UnitValidationFails() {
        // FOOT is invalid unit - should be FEET
        QuantityInputDTO body = input(
                1.0, "FOOT", "LengthUnit",
                12.0, "INCHES", "LengthUnit");

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST,
                jsonEntity(body), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains(
                "Unit must be valid for the specified measurement type");
    }

    @Test
    @Order(17)
    @DisplayName("POST /compare - 1 foot equals 12 inches → validation fails " +
                 "Measurement type must be one of: LengthUnit, VolumeUnit, " +
                 "WeightUnit, TemperatureUnit")
    void testCompare_FootEqualsInches_TypeValidationFails() {
        // InvalidType triggers @Pattern validation on measurementType
        // Response will contain BAD_REQUEST with Quantity Measurement Error
        QuantityInputDTO body = input(
                1.0, "FEET", "InvalidType",
                12.0, "INCHES", "LengthUnit");

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/compare", HttpMethod.POST,
                jsonEntity(body), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        // Both @Pattern and @AssertTrue fire — response contains Quantity Measurement Error
        assertThat(response.getBody()).contains("Quantity Measurement Error");
    }
}