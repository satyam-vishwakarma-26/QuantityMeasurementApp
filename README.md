# QuantityMeasurementApp

### 🗓 UC8: Refactoring Unit Enum to Standalone with Conversion Responsibility

---

### 🔹 Description

UC8 refactors the design introduced in **UC1–UC7** by extracting the `LengthUnit` enum from inside the `QuantityLength` class and making it a **standalone top-level enum**.

Previously, unit conversion logic was partly handled inside `QuantityLength`. This created tighter coupling and made the design harder to scale when adding new measurement categories such as **weight, volume, or temperature**.

In UC8, the `LengthUnit` enum now **owns the responsibility of converting values to and from the base unit (feet)**. The `QuantityLength` class simply **delegates conversion operations to the unit**.

This improves **code structure, maintainability, and scalability** while keeping all functionality from UC1–UC7 unchanged.

---

### 🔹 Preconditions

- `QuantityLength` class exists from previous use cases  
- `LengthUnit` enum contains:
  - `FEET`
  - `INCHES`
  - `YARDS`
  - `CENTIMETERS`
- Conversion factors are defined inside `LengthUnit`
- All previous features (equality, conversion, addition) continue to work

---

### 🔹 Main Flow

1. Extract `LengthUnit` from `QuantityLength` into a **standalone enum class**.

2. Add conversion methods inside the enum:

```
convertToBaseUnit(double value)
convertFromBaseUnit(double baseValue)
```

3. Refactor `QuantityLength` to:

- Remove internal conversion logic  
- Delegate conversions to `LengthUnit`

Example:

```
unit.convertToBaseUnit(value)
unit.convertFromBaseUnit(baseValue)
```

4. Ensure all **existing UC1–UC7 test cases pass without modification**.

---

### 🔹 Postconditions

- `LengthUnit` becomes a **standalone enum responsible for conversions**
- `QuantityLength` focuses only on **comparison and arithmetic**
- Circular dependency risk is removed
- Design becomes scalable for **future measurement types**
- Existing APIs remain unchanged

---

### 🔹 Concepts Learned

**Single Responsibility Principle (SRP)**  
- `LengthUnit` → handles conversions  
- `QuantityLength` → handles arithmetic and comparison

---

**Separation of Concerns**  
Unit logic and domain logic are separated, improving maintainability.

---

**Delegation Pattern**  
`QuantityLength` delegates conversion operations to the unit.

---

**Enum with Behavior**  
Java enums can store **data (conversion factors)** and **methods (conversion logic)**.

---

**Architectural Scalability**  
This design pattern allows future units like:

- `WeightUnit`
- `VolumeUnit`
- `TemperatureUnit`

without modifying existing code.

---

### 🔹 Key Concepts Tested

- Standalone `LengthUnit` enum accessibility  
- Correct base unit conversion methods  
- Delegation of conversion logic from `QuantityLength`  
- Backward compatibility with UC1–UC7  
- Equality, conversion, and addition operations still work  
- Enum immutability and type safety

---

### 🔹 Example Output

Input:

```
Quantity(1.0, FEET).convertTo(INCHES)
```

Output:

```
Quantity(12.0, INCHES)
```

---

Input:

```
Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET)
```

Output:

```
Quantity(2.0, FEET)
```

---

Input:

```
Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS))
```

Output:

```
true
```

---

Input:

```
LengthUnit.INCHES.convertToBaseUnit(12.0)
```

Output:

```
1.0
```

---

🔗 **Code link:**  
[Quantity Measurement – UC8](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC8-StandaloneUnit)
