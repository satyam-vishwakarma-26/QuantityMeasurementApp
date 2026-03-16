package com.apps.quantitymeasurement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ApplicationConfig loads configuration from application.properties
 * and provides methods to access configuration values.
 */
public class ApplicationConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApplicationConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties file not found in resources folder");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    // Get repository type (cache or database)
    
    public static String getRepositoryType() {
        return properties.getProperty("repository.type", "cache");
    }

    // Get database URL
     
    public static String getDatabaseUrl() {
        return properties.getProperty("db.url");
    }

    // Get database username
    
    public static String getDatabaseUsername() {
        return properties.getProperty("db.username");
    }

    // Get database password
    
    public static String getDatabasePassword() {
        return properties.getProperty("db.password");
    }

    // Get connection pool size
     
    public static int getPoolSize() {
        return Integer.parseInt(properties.getProperty("db.pool.size", "5"));
    }

    // Get connection timeout
     
    public static long getPoolTimeout() {
        return Long.parseLong(properties.getProperty("db.pool.timeout", "30000"));
    }
}