package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.repository.impl.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.impl.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.util.ApplicationConfig;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.util.logging.Logger;

public class QuantityMeasurementApp {

    private static final Logger logger =
            Logger.getLogger(QuantityMeasurementApp.class.getName());

    private final IQuantityMeasurementRepository repository;
    private final QuantityMeasurementController controller;

    public QuantityMeasurementApp() {

        logger.info("Initializing QuantityMeasurementApp...");

        // Determine repository type from configuration
        String repositoryType = ApplicationConfig.getRepositoryType();

        if ("database".equalsIgnoreCase(repositoryType)) {
            logger.info("Using Database Repository");
            repository = new QuantityMeasurementDatabaseRepository();
        } else {
            logger.info("Using Cache Repository");
            repository = QuantityMeasurementCacheRepository.getInstance();
        }

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);
    }

    public void run() {

        logger.info("Running Quantity Measurement Operations...");

        QuantityDTO q1 = new QuantityDTO(10, "CM", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(0.1, "M", "LENGTH");

        boolean compareResult = controller.compare(q1, q2);
        logger.info("Compare Result: " + compareResult);

        QuantityDTO addResult = controller.add(q1, q2);
        logger.info("Addition Result: " + addResult.getValue() + " " + addResult.getUnit());

        QuantityDTO subtractResult = controller.subtract(q1, q2);
        logger.info("Subtraction Result: " + subtractResult.getValue() + " " + subtractResult.getUnit());

        double divideResult = controller.divide(q1, q2);
        logger.info("Division Result: " + divideResult);

        QuantityDTO convertResult = controller.convert(q1, "M");
        logger.info("Conversion Result: " + convertResult.getValue() + " " + convertResult.getUnit());

        // Report statistics
        logger.info("Total measurements stored: " + repository.getTotalCount());

        // Delete measurements (for testing cleanup)
        repository.deleteAll();
        logger.info("All measurements deleted.");

        // Close database resources
        closeResources();
    }

    public void closeResources() {
        logger.info("Closing connection pool...");
        ConnectionPool.closeAllConnections();
    }

    public static void main(String[] args) {

        logger.info("Starting QuantityMeasurementApp...");

        QuantityMeasurementApp app = new QuantityMeasurementApp();
        app.run();

        logger.info("Application finished successfully.");
    }
}