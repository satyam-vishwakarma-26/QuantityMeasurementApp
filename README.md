# QuantityMeasurementApp

### 🗓 UC6: Addition of Two Length Units (Same Category)

---

### 🔹 Description

UC6 extends **UC5 by introducing arithmetic addition between length measurements**.

This use case allows the **Quantity Length API to add two measurements that may belong to different units but the same measurement category (length)**.

The system automatically performs **unit conversion before addition** and returns the result in the **unit of the first operand (target unit)**.

For example:

- `1 foot + 12 inches = 2 feet`
- `12 inches + 1 foot = 24 inches`

The implementation relies on **conversion factors defined in the `LengthUnit` enum**, ensuring consistent and mathematically accurate calculations.

---

### 🔹 Preconditions

- `QuantityLength` class exists (from UC3, UC4, UC5)
- `LengthUnit` enum includes:
  - `FEET`
  - `INCHES`
  - `YARDS`
  - `CENTIMETERS`
- Each `LengthUnit` has a defined `conversionFactor`
- Two `QuantityLength` objects or values with units are provided
- The **target unit is the unit of the first operand**
- All units belong to the **same measurement category (length)**

---

### 🔹 Main Flow

1. Client calls the addition method

```
QuantityLength.add(length1, length2)
```

or

```
length1.add(length2)
```

2. The method validates:
   - Both operands are not null
   - Units are valid
   - Values are finite numbers

3. Both measurements are converted to a **common base unit (feet)**

4. The converted values are **added together**

5. The result is **converted back to the unit of the first operand**

6. A **new QuantityLength object** is returned containing the result

---

### 🔹 Postconditions

- A new `QuantityLength` object is returned
- The result is expressed in the **unit of the first operand**
- Original objects remain **unchanged (immutability)**
- Invalid inputs throw exceptions such as:
  - `IllegalArgumentException`
  - `NullPointerException`
- Results remain mathematically accurate within floating-point precision

---

### 🔹 Concepts Learned

**Arithmetic Operations on Value Objects**

- Value objects can perform domain-specific operations
- Addition demonstrates combining measurements with implicit conversion

---

**Immutability**

- Original objects remain unchanged
- New result object is returned

---

**Unit Conversion Reusability**

- Reuses the conversion logic created in **UC5**
- Promotes code reuse and modular design

---

**Normalization to Base Unit**

- All calculations are performed in a **common base unit**
- Prevents incorrect arithmetic across units

---

**Precision Handling**

- Floating point operations may introduce rounding errors
- Epsilon comparison ensures reliable precision

---

**Type Safety**

- Only valid `LengthUnit` values are allowed
- Prevents invalid measurement combinations

---

**Mathematical Properties**

- Addition respects the **commutative property**

```
A + B = B + A
```

---

**Factory Pattern (Optional)**

- The `add()` method behaves like a factory
- Creates and returns a new `QuantityLength` object

---

**Method Overloading**

Possible overloads:

```
add(QuantityLength, QuantityLength)

add(double value1, LengthUnit unit1,
    double value2, LengthUnit unit2)
```

---

**Error Handling**

Handles edge cases including:

- null operands
- invalid units
- NaN values
- infinite numbers

---

### 🔹 Key Concepts Tested

**Same Unit Addition**

Example:

```
1 foot + 2 feet = 3 feet
```

---

**Cross Unit Addition**

Example:

```
1 foot + 12 inches = 2 feet
```

---

**Result Precision**

Floating-point accuracy maintained within tolerance.

---

**Commutativity**

```
add(A, B) == add(B, A)
```

---

**Identity Element**

Adding zero does not change the value.

Example:

```
5 feet + 0 inches = 5 feet
```

---

**Unit Consistency**

The result is **always expressed in the unit of the first operand**.

---

**Invalid Input Handling**

- null operands
- unsupported units
- NaN values

---

**Large and Small Values**

Works correctly with:

- very large measurements
- very small measurements

---

### ✅ Test Cases

1. `testAddition_SameUnit_FeetPlusFeet()`  
2. `testAddition_SameUnit_InchPlusInch()`  
3. `testAddition_CrossUnit_FeetPlusInches()`  
4. `testAddition_CrossUnit_InchPlusFeet()`  
5. `testAddition_CrossUnit_YardPlusFeet()`  
6. `testAddition_CrossUnit_CentimeterPlusInch()`  
7. `testAddition_Commutativity()`  
8. `testAddition_WithZero()`  
9. `testAddition_NegativeValues()`  
10. `testAddition_NullSecondOperand()`  
11. `testAddition_LargeValues()`  
12. `testAddition_SmallValues()`  

---

### 🔹 Example Output

Input:

```
add(Quantity(1.0, FEET), Quantity(2.0, FEET))
```

Output:

```
Quantity(3.0, FEET)
```

---

Input:

```
add(Quantity(1.0, FEET), Quantity(12.0, INCHES))
```

Output:

```
Quantity(2.0, FEET)
```

---

Input:

```
add(Quantity(12.0, INCHES), Quantity(1.0, FEET))
```

Output:

```
Quantity(24.0, INCHES)
```

---

Input:

```
add(Quantity(1.0, YARDS), Quantity(3.0, FEET))
```

Output:

```
Quantity(2.0, YARDS)
```

---

Input:

```
add(Quantity(36.0, INCHES), Quantity(1.0, YARDS))
```

Output:

```
Quantity(72.0, INCHES)
```

---

Input:

```
add(Quantity(2.54, CENTIMETERS), Quantity(1.0, INCHES))
```

Output:

```
Quantity(~5.08, CENTIMETERS)
```

---

Input:

```
add(Quantity(5.0, FEET), Quantity(0.0, INCHES))
```

Output:

```
Quantity(5.0, FEET)
```

---

Input:

```
add(Quantity(5.0, FEET), Quantity(-2.0, FEET))
```

Output:

```
Quantity(3.0, FEET)
```

---

🔗 **Code link:**  
[Quantity Measurement – UC6](http://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC6-LengthAddition)
