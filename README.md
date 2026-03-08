# QuantityMeasurementApp

### UC14: Temperature Measurement with Selective Arithmetic Support

---

## Description

UC14 extends the **Quantity Measurement Application** to support **temperature measurements** alongside existing categories such as **length, weight, and volume**.

Unlike other measurement categories, temperature behaves differently in arithmetic operations. While **equality comparison and unit conversion are valid**, operations such as **addition, subtraction, and division are not meaningful for absolute temperature values**.

To support this behavior, UC14 refactors the **IMeasurable interface** by introducing **default methods that allow arithmetic operations to be optional**. This allows:

- Length, Weight, and Volume units to support full arithmetic operations.
- Temperature units to support only **conversion and equality comparison**.
- The system to **gracefully prevent unsupported operations** through validation.

This design demonstrates how a **generic system can be extended for categories with different operational constraints while maintaining type safety and SOLID design principles.**

---

## Preconditions

- All functionality from **UC1–UC13** is implemented and operational.
- `Quantity<U extends IMeasurable>` class supports generic measurements.
- `LengthUnit`, `WeightUnit`, and `VolumeUnit` enums implement `IMeasurable`.
- Arithmetic logic is centralized from **UC13**.
- A new enum `TemperatureUnit` will be introduced.
- Temperature conversions follow standard formulas:
  - Celsius ↔ Fahrenheit
  - Celsius ↔ Kelvin
- Cross-category comparisons remain prohibited.

---

## Main Flow

### 1. Refactor `IMeasurable` Interface

Default methods are introduced to allow **optional arithmetic support**.

Example responsibilities:

- Determine whether arithmetic is supported
- Validate operations before execution
- Allow specific categories (like temperature) to override behavior

Default behavior allows arithmetic unless overridden.

---

### 2. Functional Interface for Arithmetic Capability

A functional interface indicates whether a unit supports arithmetic operations.

```
@FunctionalInterface
public interface SupportsArithmetic {
    boolean isSupported();
}
```

Units define support using **lambda expressions**.

Example:

```
SupportsArithmetic supportsArithmetic = () -> true;
```

Temperature units override this behavior.

---

### 3. Create `TemperatureUnit` Enum

`TemperatureUnit` implements `IMeasurable` and defines the following constants:

- `CELSIUS`
- `FAHRENHEIT`
- `KELVIN`

Key characteristics:

- **Celsius acts as the base unit**
- Conversion formulas use **lambda expressions**
- Arithmetic operations are disabled
- `validateOperationSupport()` throws `UnsupportedOperationException`

Example conversion formulas:

```
F = (C × 9/5) + 32
C = (F − 32) × 5/9
K = C + 273.15
```

---

### 4. Update Quantity Class

The `Quantity` class validates operation support before performing arithmetic.

Example validation:

```
this.unit.validateOperationSupport(operation.name());
```

If the operation is unsupported, an exception is thrown.

Temperature conversions use specialized logic because they are **non-linear transformations**.

---

### 5. Demonstration in QuantityMeasurementApp

The application demonstrates:

- Temperature equality comparisons
- Temperature unit conversions
- Attempted arithmetic operations that trigger exceptions

No changes to existing methods are required.

---

## Postconditions

- `TemperatureUnit` supports **CELSIUS, FAHRENHEIT, and KELVIN**.
- Temperature measurements support:
  - Equality comparison
  - Unit conversion
- Arithmetic operations throw `UnsupportedOperationException`.
- All **UC1–UC13 tests pass without modification**.
- Cross-category comparisons remain invalid.
- The system supports categories with **different operational constraints**.

---

## Example Output

### Temperature Equality

```
new Quantity<>(0.0, CELSIUS)
.equals(new Quantity<>(32.0, FAHRENHEIT))

→ true
```

```
new Quantity<>(273.15, KELVIN)
.equals(new Quantity<>(0.0, CELSIUS))

→ true
```

---

### Temperature Conversion

```
new Quantity<>(100.0, CELSIUS)
.convertTo(FAHRENHEIT)

→ Quantity(212.0, FAHRENHEIT)
```

```
new Quantity<>(32.0, FAHRENHEIT)
.convertTo(CELSIUS)

→ Quantity(0.0, CELSIUS)
```

```
new Quantity<>(0.0, CELSIUS)
.convertTo(KELVIN)

→ Quantity(273.15, KELVIN)
```

---

### Unsupported Operations

```
new Quantity<>(100.0, CELSIUS)
.add(new Quantity<>(50.0, CELSIUS))

→ UnsupportedOperationException
```

```
new Quantity<>(100.0, CELSIUS)
.divide(new Quantity<>(50.0, CELSIUS))

→ UnsupportedOperationException
```

---

### Cross Category Prevention

```
new Quantity<>(100.0, CELSIUS)
.equals(new Quantity<>(100.0, FEET))

→ false
```

---

## Concepts Learned

### Interface Segregation Principle (ISP)

Not all measurement categories support the same operations.  
Temperature only implements relevant behaviors.

---

### Default Methods in Interfaces

Default methods allow **interface evolution without breaking existing implementations**.

---

### Functional Interfaces and Lambda Expressions

Lambda expressions simplify conversion logic and operation capability checks.

---

### Non-Linear Unit Conversions

Temperature conversions use **formulas rather than simple multiplication factors**, unlike other measurement categories.

---

### Capability-Based Design

Units can expose supported operations through **capability checks**, improving robustness.

---

### Exception Semantics

`UnsupportedOperationException` clearly communicates invalid operations.

---

## GitHub Link

```
https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement
```
