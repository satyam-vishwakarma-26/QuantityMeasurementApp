# QuantityMeasurementApp

### 🗓 UC12: Subtraction and Division Operations on Quantity Measurements

---

### 🔹 Description

UC12 extends the **Quantity Measurement Application** by introducing two new arithmetic operations to the generic `Quantity<U>` class:

- **Subtraction**
- **Division**

Previously, the system supported **equality comparison, conversion, and addition** (UC1–UC11).  
UC12 completes the arithmetic capabilities by enabling users to calculate:

- The **difference between two quantities** (subtraction)
- The **ratio between two quantities** (division)

Examples:

```
5 LITRE - 2 LITRE = 3 LITRE
10 FEET - 6 INCHES ≈ 9.5 FEET
10 KILOGRAM ÷ 5 KILOGRAM = 2.0
```

The implementation follows the same architecture established earlier:

- Cross-unit arithmetic within the same category
- Immutable quantity objects
- Explicit or implicit target units
- Type-safe operations using generics

---

### 🔹 Preconditions

- Generic class `Quantity<U extends IMeasurable>` from **UC10** is implemented
- `LengthUnit`, `WeightUnit`, and `VolumeUnit` implement `IMeasurable`
- Equality, conversion, and addition from **UC1–UC11** are fully functional
- New methods `subtract()` and `divide()` are added to the `Quantity<U>` class
- Cross-category arithmetic is prevented using type checks

---

### 🔹 Main Flow

### 1️⃣ Subtraction Operation

Method signatures:

```
subtract(Quantity<U> other)
subtract(Quantity<U> other, U targetUnit)
```

Steps:

1. Validate inputs (non-null, valid units, finite values)
2. Convert both quantities to the **base unit**
3. Subtract the base values
4. Convert the result to the target unit
5. Return a **new immutable Quantity<U> object**

Example:

```
new Quantity<>(10.0, FEET)
.subtract(new Quantity<>(6.0, INCHES))

→ Quantity(9.5, FEET)
```

---

### 2️⃣ Division Operation

Method signature:

```
divide(Quantity<U> other)
```

Steps:

1. Validate inputs
2. Convert both quantities to base unit
3. Divide the base values
4. Return a **dimensionless double result**

Example:

```
new Quantity<>(10.0, FEET)
.divide(new Quantity<>(2.0, FEET))

→ 5.0
```

Division produces a **scalar ratio** rather than a quantity object.

---

### 🔹 Postconditions

- Subtraction returns a **new Quantity<U> object**
- Division returns a **dimensionless double value**
- Original quantities remain unchanged (**immutability**)
- Cross-category arithmetic is prevented
- Arithmetic operations now include:
  - Addition
  - Subtraction
  - Division

All previous features from **UC1–UC11 remain unaffected**.

---

### 🔹 Concepts Learned

**Extended Arithmetic Operations**

The quantity system now supports full arithmetic operations beyond simple comparisons.

---

**Immutability**

All operations return **new objects**, ensuring safe and predictable behavior.

---

**Non-Commutative Operations**

Unlike addition:

```
A.subtract(B) ≠ B.subtract(A)
A.divide(B) ≠ B.divide(A)
```

Order of operands matters.

---

**Dimensionless Ratios**

Division produces a **pure scalar value** representing how many times one quantity fits into another.

---

**Type Safety**

Generics ensure that subtraction and division only occur within the **same measurement category**.

---

### 🔹 Key Concepts Tested

- Subtraction with same units  
- Subtraction with different units  
- Subtraction with explicit target unit  
- Negative subtraction results  
- Subtraction resulting in zero  
- Division with same units  
- Division with cross-unit values  
- Division ratios (>1, <1, =1)  
- Division by zero prevention  
- Cross-category arithmetic prevention  
- Immutability after operations  
- Precision and rounding behavior  

---

### 🔹 Example Output

**Subtraction (Implicit Target Unit)**

```
new Quantity<>(10.0, FEET)
.subtract(new Quantity<>(6.0, INCHES))

→ Quantity(9.5, FEET)
```

```
new Quantity<>(10.0, KILOGRAM)
.subtract(new Quantity<>(5000.0, GRAM))

→ Quantity(5.0, KILOGRAM)
```

---

**Subtraction (Explicit Target Unit)**

```
new Quantity<>(10.0, FEET)
.subtract(new Quantity<>(6.0, INCHES), INCHES)

→ Quantity(114.0, INCHES)
```

---

**Division**

```
new Quantity<>(10.0, FEET)
.divide(new Quantity<>(2.0, FEET))

→ 5.0
```

```
new Quantity<>(24.0, INCHES)
.divide(new Quantity<>(2.0, FEET))

→ 1.0
```

---

**Error Cases**

```
new Quantity<>(10.0, FEET).subtract(null)
→ IllegalArgumentException
```

```
new Quantity<>(10.0, FEET)
.divide(new Quantity<>(0.0, FEET))

→ ArithmeticException
```

---

### 🔗 Code Link

[Quantity Measurement – UC12](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC12-SubtractionAndDivision)
