# QuantityMeasurementApp

### UC16: Database Integration with JDBC for Quantity Measurement Persistence

---

## Description

UC16 extends the **Quantity Measurement Application** by introducing **database persistence using JDBC (Java Database Connectivity)**.

In earlier versions (UC1–UC15), measurement operations were stored **only in memory using a cache repository**. This meant that data could be lost when the application stopped or restarted.

UC16 replaces the in-memory storage with a **database-backed repository** using JDBC. Measurement operations are now stored in a **relational database (H2)**, enabling:

- Persistent storage of measurement history
- Querying and filtering measurement operations
- Better scalability and reliability
- Improved testing using an isolated test database

The system continues to follow a **clean N-Tier architecture**, where the service layer remains independent of the repository implementation. This allows the application to easily switch between **cache storage and database storage** through dependency injection.

---

## Preconditions

- All functionality from **UC1–UC15** is implemented and working.
- The application follows **N-Tier architecture**:
  - Controller
  - Service
  - Repository
  - Entity
- `IQuantityMeasurementRepository` interface already exists.
- `QuantityMeasurementCacheRepository` implements in-memory storage.
- `QuantityMeasurementEntity` represents measurement data.
- Maven is installed.
- Java **17 or higher** is available.
- JDBC and SQL basics are understood.
- Database schema will be created using **H2 database**.

---

## Main Flow

### 1. Maven Project Structure

The project is organized using **standard Maven directory layout**.

```
src
 ├── main
 │   ├── java
 │   │   └── com.apps.quantitymeasurement
 │   │        ├── controller
 │   │        ├── service
 │   │        ├── repository
 │   │        ├── entity
 │   │        ├── dto
 │   │        ├── model
 │   │        ├── unit
 │   │        ├── util
 │   │        └── exception
 │
 │   └── resources
 │        ├── application.properties
 │        └── db
 │            └── schema.sql
 │
 └── test
      └── java
           └── com.apps.quantitymeasurement
                ├── controller
                ├── service
                ├── repository
                └── integrationTests
```

This structure separates **production code and test code** clearly.

---

### 2. Database Configuration

Database properties are defined in:

```
src/main/resources/application.properties

```

The application uses **H2 in-memory database** for development and testing.

---

### 3. Database Schema Creation

The database schema is defined in:

```
src/main/resources/db/schema.sql

```

The schema is automatically loaded when the application starts.

---

### 4. Connection Pool Implementation

A **ConnectionPool utility class** manages reusable database connections.

Responsibilities:

- Create multiple database connections
- Reuse connections instead of creating new ones
- Improve performance
- Handle concurrent access safely

Example:

```
Connection connection = ConnectionPool.getConnection();
ConnectionPool.releaseConnection(connection);
```

---

### 5. Database Repository Implementation

A new repository implementation is created:

```
QuantityMeasurementDatabaseRepository
```

This class uses **JDBC and SQL queries** to interact with the database.

Key operations:

- Save measurement operations
- Retrieve all stored measurements
- Filter by operation type
- Filter by measurement type
- Count stored measurements
- Delete all measurements

Example query:

```
INSERT INTO quantity_measurement_entity
(measurement_type, operation_type, value1, unit1, value2, unit2, result)
VALUES (?, ?, ?, ?, ?, ?, ?)
```

Parameterized queries prevent **SQL injection attacks**.

---

### 6. Service Layer Integration

The service layer uses **Dependency Injection** to work with either:

```
QuantityMeasurementCacheRepository
```

or

```
QuantityMeasurementDatabaseRepository
```

This allows switching storage implementations **without modifying service logic**.

---

### 7. Application Initialization

The main application initializes the appropriate repository and starts measurement operations.

Example workflow:

1. Application starts
2. Database configuration is loaded
3. Connection pool is created
4. Repository is initialized
5. Operations are executed
6. Results are stored in the database
7. Resources are closed on shutdown

---

## Postconditions

After implementing UC16:

- Measurement operations are **persisted in the database**.
- Historical data can be retrieved and analyzed.
- The project follows **Maven standard structure**.
- Database connections are managed through **connection pooling**.
- SQL queries are **parameterized and secure**.
- Unit tests and integration tests verify database behavior.
- All **UC1–UC15 functionality remains unchanged**.

---

## Example Output

### Application Startup

```
Starting QuantityMeasurementApp...
Using Database Repository
Running Quantity Measurement Operations...
```

---

### Measurement Operations

```
Compare Result: false
Addition Result: 10.1 CM
Subtraction Result: 9.9 CM
Division Result: 100.0
Conversion Result: 10.0 M
```

---

### Database Statistics

```
Total measurements stored: 5
All measurements deleted.
Application finished successfully.
```

---

## Concepts Learned

### JDBC (Java Database Connectivity)

JDBC provides a **standard API for interacting with relational databases** in Java.

---

### Connection Pooling

Maintains reusable database connections to improve **performance and scalability**.

---

### Parameterized SQL Queries

Using `PreparedStatement` prevents **SQL injection vulnerabilities**.

---

### Maven Project Structure

Maven enforces a **standard project layout**, improving maintainability and collaboration.

---

### N-Tier Architecture

Each layer has a clear responsibility:

- Controller → user interaction
- Service → business logic
- Repository → database access

---

### Database Schema Management

SQL schema files allow consistent **database initialization across environments**.

---

### Integration Testing

Integration tests verify the **complete workflow from controller to database**.

---

## GitHub Link

```
https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC16-JDBCPersistence
```
