package com.app.quantitymeasurement.controller;

import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable all security filters for testing
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    @Autowired
    private ObjectMapper objectMapper;

    private QuantityInputDTO quantity1;
    private QuantityMeasurementDTO measurementResult;

    @BeforeEach
    public void setUp() {
        quantity1 = new QuantityInputDTO();
        quantity1.setThisQuantityDTO(new QuantityDTO(
                1.0, "FEET", "LengthUnit"
        ));
        quantity1.setThatQuantityDTO(new QuantityDTO(
                12.0, "INCHES", "LengthUnit"
        ));

        measurementResult = new QuantityMeasurementDTO();
        measurementResult.setThisValue(quantity1.getThisQuantityDTO().getValue());
        measurementResult.setThisUnit(quantity1.getThisQuantityDTO().getUnit());
        measurementResult.setThisMeasurementType(
                quantity1.getThisQuantityDTO().getMeasurementType()
        );
        measurementResult.setThatValue(quantity1.getThatQuantityDTO().getValue());
        measurementResult.setThatUnit(quantity1.getThatQuantityDTO().getUnit());
        measurementResult.setThatMeasurementType(
                quantity1.getThatQuantityDTO().getMeasurementType()
        );
    }

    @Test
    public void testCompareQuantities_Success() throws Exception {
        // Set up the expected result for the compare operation in the service layer

        measurementResult.setOperation("Compare");
        measurementResult.setResultString("true");
        measurementResult.setResultValue(0.0);
        measurementResult.setResultUnit(null);
        measurementResult.setResultMeasurementType(null);
        measurementResult.setError(false);

        Mockito.when(service.compare(
                quantity1.getThisQuantityDTO(),
                quantity1.getThatQuantityDTO()
        )).thenReturn(measurementResult);

        // Perform a POST request to the /compare endpoint with the input quantities and
        // verify that the response is as expected. The test checks that the HTTP status

        mockMvc.perform(
                post("/api/v1/quantities/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quantity1))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.resultString").value("true"))
        .andReturn();
    }

    @Test
    public void testAddQuantities_Success() throws Exception {
        measurementResult.setOperation("add");
        measurementResult.setResultValue(2.0);
        measurementResult.setResultUnit("FEET");
        measurementResult.setResultMeasurementType("LengthUnit");
        measurementResult.setError(false);

        Mockito.when(service.add(
                quantity1.getThisQuantityDTO(),
                quantity1.getThatQuantityDTO()
        )).thenReturn(measurementResult);

        mockMvc.perform(
                post("/api/v1/quantities/add")
                        .param("targetUnit", "FEET")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quantity1))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.resultValue").value(2.0));
    }

    @Test
    public void testGetOperationHistory_Success() throws Exception {
        // Mock the service layer to return an empty list for the operation history
        Mockito
                .when(service.getHistoryByOperation("COMPARE"))
                .thenReturn(java.util.Collections.emptyList());

        mockMvc.perform(
                get("/api/v1/quantities/history/operation/COMPARE")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0))
        .andReturn();
    }

    @Test
    public void testGetOperationCount_Success() throws Exception {
        // Mock the service layer to return a count of 0 for the operation count
        Mockito
                .when(service.getCountByOperation("COMPARE"))
                .thenReturn(0L);

        mockMvc.perform(
                get("/api/v1/quantities/count/COMPARE")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().string("0"))
        .andReturn();
    }
}