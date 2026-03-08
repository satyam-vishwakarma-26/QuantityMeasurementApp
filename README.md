# QuantityMeasurementApp

### 🗓 UC9: Weight Measurement Equality, Conversion, and Addition (Kilogram, Gram, Pound)

---

### 🔹 Description

UC9 extends the **Quantity Measurement Application** by introducing a new measurement category: **Weight**.

Similar to length measurements implemented in UC1–UC8, the system now supports **weight operations such as equality comparison, unit conversion, and addition**.

Three weight units are supported:

- **KILOGRAM (kg)** – base unit  
- **GRAM (g)** – 1 kg = 1000 g  
- **POUND (lb)** – 1 lb ≈ 0.453592 kg  

The design mirrors the architecture used for length measurements (`LengthUnit` and `QuantityLength`) by introducing **`WeightUnit` and `QuantityWeight`** classes. This confirms that the system architecture is **scalable and reusable for multiple measurement categories**.

---

### 🔹 Preconditions

- `QuantityMeasurementApp` is running
- `WeightUnit` enum exists with:
  - `KILOGRAM`
  - `GRAM`
  - `POUND`
- Conversion factors are defined relative to **kilogram (base unit)**
- `QuantityWeight` class is implemented
- Length functionality from **UC1–UC8 remains unchanged**
- Weight and length are treated as **separate measurement categories**

---

### 🔹 Main Flow

**1. Equality Comparison**

- Two `QuantityWeight` objects are provided
- Both values are converted to **kilograms**
- Values are compared using the overridden `equals()` method
- Returns `true` if values are equivalent

---

**2. Unit Conversion**

```
QuantityWeight.convertTo(targetUnit)
```

Steps:

- Convert value to base unit (**kilogram**)
- Convert base value to **target unit**
- Return a new `QuantityWeight` object

---

**3. Addition**

Two methods are supported:

```
add(weight1, weight2)
add(weight1, weight2, targetUnit)
```

Steps:

- Convert both operands to **kilograms**
- Add the values
- Convert result to the desired unit
- Return a **new immutable object**

---

### 🔹 Postconditions

- Equivalent weights across units are considered equal  
  Example: `1 kg = 1000 g ≈ 2.20462 lb`

- Conversions produce mathematically accurate results within floating-point precision

- Addition returns a **new `QuantityWeight` object** (immutability)

- Length and weight measurements remain **independent categories**

- The architecture now supports **multiple measurement systems**

---

### 🔹 Concepts Learned

**Multiple Measurement Categories**

The system now supports both:

- Length measurements
- Weight measurements

Each category has its **own units and quantity class**.

---

**Reusable Architecture**

`WeightUnit` and `QuantityWeight` follow the same design as:

- `LengthUnit`
- `QuantityLength`

This makes the system easily extendable.

---

**Base Unit Normalization**

All weight operations normalize values through the **base unit (kilogram)** before performing comparisons or arithmetic.

---

**Category Type Safety**

Weight and length cannot be compared.

Example:

```
Quantity(1.0, KILOGRAM).equals(Quantity(1.0, FOOT)) → false
```

---

**Immutability**

All quantity objects are immutable:

- values cannot change
- operations return **new objects**

---

### 🔹 Key Concepts Tested

- Equality of same-unit weights  
- Equality of cross-unit weights  
- Weight conversion accuracy  
- Addition with same units  
- Addition with different units  
- Addition with explicit target unit  
- Commutativity of addition  
- Null and invalid input handling  
- Category type safety (weight vs length)

---

### 🔹 Example Output

**Equality**

```
Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM))
→ true
```

```
Quantity(1.0, KILOGRAM).equals(Quantity(~2.20462, POUND))
→ true
```

---

**Conversions**

```
Quantity(1.0, KILOGRAM).convertTo(GRAM)
→ Quantity(1000.0, GRAM)
```

```
Quantity(2.0, POUND).convertTo(KILOGRAM)
→ Quantity(~0.907184, KILOGRAM)
```

---

**Addition (Implicit Target Unit)**

```
Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM))
→ Quantity(2.0, KILOGRAM)
```

---

**Addition (Explicit Target Unit)**

```
Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM), GRAM)
→ Quantity(2000.0, GRAM)
```

---

### 🔗 Code Link

[Quantity Measurement – UC9](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC9-WeightMeasurement)
