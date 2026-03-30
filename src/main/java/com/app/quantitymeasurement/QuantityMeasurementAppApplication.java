package com.app.quantitymeasurement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Quantity Measurement API",
        version = "1.0.0",
        description = "REST API for performing quantity measurements including " +
                      "compare, convert, add, subtract, multiply and divide " +
                      "operations across Length, Weight, Volume and Temperature units.",
        contact = @Contact(
            name = "Quantity Measurement Team",
            email = "support@quantitymeasurement.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local Development Server")
    }
)
public class QuantityMeasurementAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuantityMeasurementAppApplication.class, args);
    }
}