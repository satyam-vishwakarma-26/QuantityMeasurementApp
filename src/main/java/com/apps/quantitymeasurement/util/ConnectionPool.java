package com.apps.quantitymeasurement.util;

import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ConnectionPool manages a pool of reusable database connections.
 */
public class ConnectionPool {

    private static BlockingQueue<Connection> connectionPool;
    private static int poolSize;

    static {
        try {

            poolSize = ApplicationConfig.getPoolSize();
            connectionPool = new ArrayBlockingQueue<>(poolSize);

            String url = ApplicationConfig.getDatabaseUrl();
            String username = ApplicationConfig.getDatabaseUsername();
            String password = ApplicationConfig.getDatabasePassword();

            // Create connections
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                connectionPool.offer(connection);
            }

            // Run schema.sql to create tables
            Connection connection = connectionPool.peek();

            InputStream schemaStream =
                    ConnectionPool.class
                            .getClassLoader()
                            .getResourceAsStream("db/schema.sql");

            if (schemaStream != null) {
                RunScript.execute(connection, new InputStreamReader(schemaStream));
                System.out.println("Database schema loaded successfully.");
            } else {
                System.out.println("schema.sql not found!");
            }

            System.out.println("ConnectionPool initialized with size: " + poolSize);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Connection Pool", e);
        }
    }

    public static Connection getConnection() {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to acquire database connection", e);
        }
    }

    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionPool.offer(connection);
        }
    }

    public static void closeAllConnections() {

        while (!connectionPool.isEmpty()) {

            try {

                Connection connection = connectionPool.poll();

                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static String getPoolStatistics() {

        return "Available connections: " + connectionPool.size() +
                " / Total pool size: " + poolSize;
    }
}