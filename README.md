# QuantityMeasurementApp

### 🗓 UC7: Addition with Target Unit Specification

---

### 🔹 Description

UC7 extends **UC6 by introducing flexibility in specifying the unit of the result**.

In UC6, the result of addition was always returned in the **unit of the first operand**. UC7 enhances this functionality by allowing the caller to **explicitly define the target unit for the result**, regardless of the units of the operands.

This provides greater flexibility when applications require results in a specific unit.

Example:

- `1 foot + 12 inches → 2 feet`
- If target unit is **yards**, the result becomes  
  `≈ 0.667 yards`

The system internally performs conversion using the **conversion factors defined in the `LengthUnit` enum**, ensuring consistent and accurate results.

---

### 🔹 Preconditions

- `QuantityLength` class exists (from UC3, UC4, UC5, UC6)
- `LengthUnit` enum includes:
  - `FEET`
  - `INCHES`
  - `YARDS`
  - `CENTIMETERS`
- Conversion factors are defined relative to a **common base unit**
- Two `QuantityLength` values are provided
- A **target unit is explicitly specified**
- All units belong to the **same measurement category (length)**

---

### 🔹 Main Flow

1. Client calls the addition method with explicit target unit

```
QuantityLength.add(length1, length2, targetUnit)
```

2. The method validates:

- `length1` and `length2` are not null  
- `targetUnit` is valid and not null  
- All numeric values are finite  

3. Both operands are converted into the **base unit (feet)**

4. The converted values are **added together**

5. The sum is converted from the **base unit to the specified targetUnit**

6. A **new QuantityLength object** is returned containing the result

---

### 🔹 Postconditions

- A new `QuantityLength` object is returned
- The result is expressed in the **explicitly specified target unit**
- Original objects remain **unchanged (immutability)**
- Invalid inputs throw exceptions such as:
  - `IllegalArgumentException`
  - `NullPointerException`
- Mathematical accuracy maintained within floating-point precision
- Addition remains **commutative**

```
add(A, B, targetUnit) = add(B, A, targetUnit)
```

---

### 🔹 Concepts Learned

**Method Overloading**

UC7 demonstrates **method overloading** by allowing two versions of `add()`:

```
add(QuantityLength, QuantityLength)

add(QuantityLength, QuantityLength, LengthUnit targetUnit)
```

This maintains **backward compatibility with UC6** while adding flexibility.

---

**Private Utility Method**

A private helper method handles:

- Base unit conversion
- Arithmetic addition
- Conversion to target unit

This ensures:

- **DRY principle**
- Consistent rounding
- Code maintainability

---

**API Consistency**

The API now supports:

- Implicit target unit (UC6)
- Explicit target unit (UC7)

Both approaches coexist without breaking earlier implementations.

---

**Explicit Parameter Passing**

Caller has **direct control over the result unit**, improving code clarity and removing ambiguity.

---

**Flexibility in Result Representation**

The same arithmetic operation can produce results in **different units based on caller needs**.

Example:

```
1 foot + 12 inches
```

Result options:

```
2 feet
24 inches
0.667 yards
```

---

**Unit Independence in Arithmetic**

Arithmetic is independent of unit representation.

Units are converted only at **input and output boundaries**.

---

**Precision Handling**

Conversion across large or small unit scales may introduce rounding differences.

Epsilon comparison ensures precision consistency.

---

**Immutability and Thread Safety**

- Original objects remain unchanged
- New result object returned
- Safe for concurrent execution

---

**Functional Programming Principle**

The method behaves like a **pure function**:

- Same inputs always produce the same output
- No internal state modification

---

### 🔹 Key Concepts Tested

**Explicit Target Unit Same as First Operand**

Example:

```
add(1 FEET, 12 INCHES, FEET) → 2 FEET
```

---

**Explicit Target Unit Same as Second Operand**

Example:

```
add(1 FEET, 12 INCHES, INCHES) → 24 INCHES
```

---

**Explicit Target Unit Different from Both Operands**

Example:

```
add(1 FEET, 12 INCHES, YARDS) → 0.667 YARDS
```

---

**Target Unit Consistency**

The returned `QuantityLength` object always uses the **specified target unit**.

---

**Commutativity**

```
add(A, B, targetUnit) == add(B, A, targetUnit)
```

---

**Null Target Unit Handling**

Passing `null` as target unit throws `IllegalArgumentException`.

---

**Invalid Unit Handling**

Unsupported units are rejected by validation logic.

---

**Mathematical Correctness Across Units**

Equivalent values remain consistent across unit conversions.

Example:

```
2 FEET = 24 INCHES
```

---

**Edge Case Handling**

- zero values
- negative values
- very large values
- very small values

---

### ✅ Test Cases

1. `testAddition_ExplicitTargetUnit_Feet()`  
2. `testAddition_ExplicitTargetUnit_Inches()`  
3. `testAddition_ExplicitTargetUnit_Yards()`  
4. `testAddition_ExplicitTargetUnit_Centimeters()`  
5. `testAddition_ExplicitTargetUnit_SameAsFirstOperand()`  
6. `testAddition_ExplicitTargetUnit_SameAsSecondOperand()`  
7. `testAddition_ExplicitTargetUnit_Commutativity()`  
8. `testAddition_ExplicitTargetUnit_WithZero()`  
9. `testAddition_ExplicitTargetUnit_NegativeValues()`  
10. `testAddition_ExplicitTargetUnit_NullTargetUnit()`  
11. `testAddition_ExplicitTargetUnit_LargeToSmallScale()`  
12. `testAddition_ExplicitTargetUnit_SmallToLargeScale()`  
13. `testAddition_ExplicitTargetUnit_AllUnitCombinations()`  
14. `testAddition_ExplicitTargetUnit_PrecisionTolerance()`  

---

### 🔹 Example Output

Input:

```
add(Quantity(1.0, FEET), Quantity(12.0, INCHES), FEET)
```

Output:

```
Quantity(2.0, FEET)
```

---

Input:

```
add(Quantity(1.0, FEET), Quantity(12.0, INCHES), INCHES)
```

Output:

```
Quantity(24.0, INCHES)
```

---

Input:

```
add(Quantity(1.0, FEET), Quantity(12.0, INCHES), YARDS)
```

Output:

```
Quantity(~0.667, YARDS)
```

---

Input:

```
add(Quantity(1.0, YARDS), Quantity(3.0, FEET), YARDS)
```

Output:

```
Quantity(2.0, YARDS)
```

---

Input:

```
add(Quantity(36.0, INCHES), Quantity(1.0, YARDS), FEET)
```

Output:

```
Quantity(6.0, FEET)
```

---

Input:

```
add(Quantity(2.54, CENTIMETERS), Quantity(1.0, INCHES), CENTIMETERS)
```

Output:

```
Quantity(~5.08, CENTIMETERS)
```

---

Input:

```
add(Quantity(5.0, FEET), Quantity(0.0, INCHES), YARDS)
```

Output:

```
Quantity(~1.667, YARDS)
```

---

Input:

```
add(Quantity(5.0, FEET), Quantity(-2.0, FEET), INCHES)
```

Output:

```
Quantity(36.0, INCHES)
```

---

🔗 **Code link:**  
[Quantity Measurement – UC7](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition)
