package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository implementation for database persistence using JDBC.
 */
public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurement_entity " +
            "(measurement_type, operation_type, value1, unit1, value2, unit2, result) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM quantity_measurement_entity";

    private static final String SELECT_BY_OPERATION_SQL =
            "SELECT * FROM quantity_measurement_entity WHERE operation_type = ?";

    private static final String SELECT_BY_TYPE_SQL =
            "SELECT * FROM quantity_measurement_entity WHERE measurement_type = ?";

    private static final String COUNT_SQL =
            "SELECT COUNT(*) FROM quantity_measurement_entity";

    private static final String DELETE_ALL_SQL =
            "DELETE FROM quantity_measurement_entity";


    /**
     * Save measurement entity to database
     */
    @Override
    public void save(QuantityMeasurementEntity entity) {

        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_SQL);

            statement.setString(1, entity.getMeasurementType());
            statement.setString(2, entity.getOperationType());
            statement.setDouble(3, entity.getValue1());
            statement.setString(4, entity.getUnit1());
            statement.setDouble(5, entity.getValue2());
            statement.setString(6, entity.getUnit2());
            statement.setBoolean(7, entity.isResult());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error saving measurement", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }


    /**
     * Get all measurements
     */
    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);

            while (resultSet.next()) {

                QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

                entity.setId(resultSet.getLong("id"));
                entity.setMeasurementType(resultSet.getString("measurement_type"));
                entity.setOperationType(resultSet.getString("operation_type"));
                entity.setValue1(resultSet.getDouble("value1"));
                entity.setUnit1(resultSet.getString("unit1"));
                entity.setValue2(resultSet.getDouble("value2"));
                entity.setUnit2(resultSet.getString("unit2"));
                entity.setResult(resultSet.getBoolean("result"));

                list.add(entity);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching measurements", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return list;
    }


    /**
     * Get measurements by operation type
     */
    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operationType) {

        List<QuantityMeasurementEntity> list = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(SELECT_BY_OPERATION_SQL);
            statement.setString(1, operationType);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

                entity.setId(resultSet.getLong("id"));
                entity.setMeasurementType(resultSet.getString("measurement_type"));
                entity.setOperationType(resultSet.getString("operation_type"));
                entity.setValue1(resultSet.getDouble("value1"));
                entity.setUnit1(resultSet.getString("unit1"));
                entity.setValue2(resultSet.getDouble("value2"));
                entity.setUnit2(resultSet.getString("unit2"));
                entity.setResult(resultSet.getBoolean("result"));

                list.add(entity);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching by operation type", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return list;
    }


    /**
     * Get measurements by measurement type
     */
    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {

        List<QuantityMeasurementEntity> list = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();

            PreparedStatement statement = connection.prepareStatement(SELECT_BY_TYPE_SQL);
            statement.setString(1, measurementType);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

                entity.setId(resultSet.getLong("id"));
                entity.setMeasurementType(resultSet.getString("measurement_type"));
                entity.setOperationType(resultSet.getString("operation_type"));
                entity.setValue1(resultSet.getDouble("value1"));
                entity.setUnit1(resultSet.getString("unit1"));
                entity.setValue2(resultSet.getDouble("value2"));
                entity.setUnit2(resultSet.getString("unit2"));
                entity.setResult(resultSet.getBoolean("result"));

                list.add(entity);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching by measurement type", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return list;
    }


    /**
     * Get total count of measurements
     */
    @Override
    public int getTotalCount() {

        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_SQL);

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error counting measurements", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return 0;
    }


    /**
     * Delete all measurements
     */
    @Override
    public void deleteAll() {

        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();

            Statement statement = connection.createStatement();
            statement.executeUpdate(DELETE_ALL_SQL);

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting measurements", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }
}