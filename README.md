# QuantityMeasurementApp

### UC15: N-Tier Architecture Refactoring for Quantity Measurement Application

---

## Description

UC15 refactors the **Quantity Measurement Application** from a monolithic structure into a **professional N-Tier architecture**. The application is reorganized into distinct layers to separate responsibilities and improve maintainability, scalability, and testability.

The architecture introduces the following layers:

- **Application Layer**
- **Controller Layer**
- **Service Layer**
- **Entity / Model Layer**
- **Repository Layer**

This restructuring ensures that **business logic, data representation, persistence, and orchestration are clearly separated**.

The refactoring follows **SOLID design principles** and prepares the application for future extensions such as:

- REST API development
- Database persistence
- Dependency injection frameworks (Spring / Guice)
- Multiple client interfaces (CLI, GUI, Web)

UC15 keeps **all functionality from UC1–UC14 unchanged**, while improving the **internal structure of the system**.

---

## Preconditions

Before implementing UC15, the following functionality must already exist:

- UC1–UC14 features are fully implemented
- Quantity comparison
- Unit conversion
- Arithmetic operations (add, subtract, divide)
- Temperature measurement support
- `Quantity<U extends IMeasurable>` generic implementation
- `IMeasurable` interface for measurement units
- Unit enums implemented:
  - LengthUnit
  - WeightUnit
  - VolumeUnit
  - TemperatureUnit
- Existing tests from UC1–UC14 must pass without modification.

---

## N-Tier Architecture Overview

The application is structured into **four major layers**.

```
Application Layer
        ↓
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Entity / Model Layer
```

Each layer has a **clear responsibility**, enabling modular development and easier maintenance.

---

## Layer Responsibilities

### Application Layer

Class: `QuantityMeasurementApp`

Responsibilities:

- Entry point of the application
- Initializes repository, service, and controller
- Coordinates application flow
- Delegates operations to the controller

The application layer contains **minimal logic** and primarily performs initialization.

---

### Controller Layer

Class: `QuantityMeasurementController`

Responsibilities:

- Handles user interaction
- Accepts input using `QuantityDTO`
- Delegates operations to the service layer
- Formats and presents results

Example operations exposed:

- compare quantities
- convert units
- add quantities
- subtract quantities
- divide quantities

The controller follows the **Facade pattern**, providing a simple interface to complex service logic.

---

### Service Layer

Class: `QuantityMeasurementServiceImpl`

Responsibilities:

- Implements all business logic
- Performs quantity comparison
- Handles unit conversion
- Executes arithmetic operations
- Validates cross-category operations
- Stores operation results in repository

Key design principles:

- **Single Responsibility Principle**
- **Open-Closed Principle**
- **Dependency Injection**

The service layer interacts with the repository through the interface:

```
IQuantityMeasurementRepository
```

---

### Repository Layer

Interface: `IQuantityMeasurementRepository`

Implementation:

```
QuantityMeasurementCacheRepository
```

Responsibilities:

- Store quantity measurement operations
- Maintain operation history
- Serialize data to disk
- Load history on application startup

The repository uses:

```
ArrayList<QuantityMeasurementEntity>
```

as an in-memory cache and persists data using **Java serialization**.

The repository follows the **Singleton design pattern**, ensuring only one instance exists.

---

### Entity / Model Layer

This layer contains **data classes used across the application**.

#### QuantityDTO

Purpose:

- Transfer quantity data between controller and service layers
- Represent external input and output

Contains:

```
value
unit
measurementType
```

---

#### QuantityModel

Purpose:

- Internal representation of quantities within the service layer
- Supports generic measurement units

Generic structure:

```
QuantityModel<U extends IMeasurable>
```

---

#### QuantityMeasurementEntity

Purpose:

- Stores complete operation history

Contains:

- operands
- operation type
- result
- error information

The entity implements **Serializable**, allowing operation history to be stored on disk.

---

## Design Patterns Used

UC15 introduces several important design patterns.

### Singleton Pattern

Used in:

```
QuantityMeasurementCacheRepository
```

Ensures a single repository instance across the application.

---

### Factory Pattern

Used in the application layer to create instances of:

- Controller
- Service

This improves flexibility and maintainability.

---

### Facade Pattern

`QuantityMeasurementController` acts as a **facade**, simplifying interaction with the service layer.

---

### Dependency Injection

Dependencies are injected through constructors.

Example:

```
QuantityMeasurementServiceImpl(repository)
```

Benefits:

- Loose coupling
- Easy testing
- Replaceable implementations

---

## Data Flow Example

Example: Addition Operation

```
User Input
   ↓
Controller (QuantityDTO)
   ↓
Service Layer
   ↓
Repository
   ↓
Entity Stored
   ↓
Result Returned to Controller
   ↓
Displayed to User
```

---

## Postconditions

After implementing UC15:

- Application follows **N-Tier architecture**
- Business logic is isolated in the service layer
- Controller handles orchestration only
- Entities standardize data exchange between layers
- Repository manages persistence
- Code becomes easier to test and maintain
- UC1–UC14 behavior remains unchanged
- Application is ready for **future database integration (UC16)**

---

## Example Output

### Length Equality

```
1 FEET == 12 INCHES
Result → true
```

---

### Temperature Unsupported Operation

```
100 CELSIUS + 50 CELSIUS
Result → UnsupportedOperationException
```

---

### Cross Category Prevention

```
1 FEET == 1 KILOGRAM
Result → false
```

---

## Concepts Learned

### Separation of Concerns

Each layer focuses on a specific responsibility.

---

### N-Tier Architecture

Improves maintainability, scalability, and modular design.

---

### Data Transfer Objects (DTO)

DTOs enable structured communication between layers.

---

### Dependency Injection

Improves flexibility and testability.

---

### SOLID Principles

UC15 follows:

- Single Responsibility Principle
- Open Closed Principle
- Liskov Substitution Principle
- Interface Segregation Principle
- Dependency Inversion Principle

---

### Serialization

Used to persist repository data across application restarts.

---

### Layered Testing

Business logic can now be tested independently of UI or application layer.

---

## GitHub Link

```
https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC15-NTierArchitectureRefactor
```
