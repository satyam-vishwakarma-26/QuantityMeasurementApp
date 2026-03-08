# QuantityMeasurementApp

### 🗓 UC11: Volume Measurement Equality, Conversion, and Addition (Litre, Millilitre, Gallon)

---

### 🔹 Description

UC11 extends the **Quantity Measurement Application** by introducing a third measurement category: **Volume**.

Using the **generic architecture introduced in UC10 (`Quantity<U>` and `IMeasurable`)**, volume units can now perform:

- Equality comparison
- Unit conversion
- Arithmetic addition

Supported units:

- **LITRE (L)** – base unit  
- **MILLILITRE (mL)** – 1 L = 1000 mL  
- **GALLON (gal)** – 1 gal ≈ 3.78541 L  

UC11 demonstrates that the **generic design from UC10 scales easily to new categories** without modifying the `Quantity<U>` class or existing logic.

---

### 🔹 Preconditions

- Generic class `Quantity<U extends IMeasurable>` from **UC10** is operational
- `IMeasurable` interface defines unit conversion behavior
- `LengthUnit` and `WeightUnit` already implement `IMeasurable`
- A new `VolumeUnit` enum will be created implementing `IMeasurable`
- Existing functionality from **UC1–UC10 remains unchanged**

---

### 🔹 Main Flow

**1. Create VolumeUnit Enum**

```
enum VolumeUnit implements IMeasurable
```

Supported units and conversion factors:

```
LITRE        → 1.0
MILLILITRE   → 0.001
GALLON       → 3.78541
```

The enum implements:

```
getConversionFactor()
convertToBaseUnit()
convertFromBaseUnit()
getUnitName()
```

---

**2. Equality Comparison**

Two `Quantity<VolumeUnit>` objects are compared.

Steps:

- Convert both values to **litres (base unit)**
- Compare values using `equals()`

Example:

```
new Quantity<>(1.0, LITRE)
.equals(new Quantity<>(1000.0, MILLILITRE))

→ true
```

---

**3. Unit Conversion**

```
Quantity<VolumeUnit>.convertTo(targetUnit)
```

Steps:

- Convert value to **litres**
- Convert litres to **target unit**
- Return a new immutable `Quantity<VolumeUnit>` object

---

**4. Addition**

Two methods are supported:

```
add(other)
add(other, targetUnit)
```

Steps:

- Convert both operands to litres
- Add values
- Convert result to desired unit

---

### 🔹 Postconditions

- Equivalent volumes across units are considered equal  
  Example: `1 L = 1000 mL ≈ 0.264172 gal`

- Conversions are mathematically accurate within floating-point precision

- Addition returns a **new immutable object**

- Volume, length, and weight remain **separate measurement categories**

- No changes were required in:
  - `Quantity<U>`
  - `IMeasurable`
  - `QuantityMeasurementApp`

---

### 🔹 Concepts Learned

**Scalability of Generic Architecture**

Adding a new measurement category required **only one new enum (`VolumeUnit`)**.

---

**Generic Programming**

The same `Quantity<U>` class supports:

- Length
- Weight
- Volume

without code duplication.

---

**Base Unit Normalization**

All operations normalize through the **base unit (litre)** before performing calculations.

---

**Type Safety**

Generics ensure category safety:

```
Quantity<VolumeUnit> ≠ Quantity<LengthUnit>
Quantity<VolumeUnit> ≠ Quantity<WeightUnit>
```

---

**Immutability**

All quantity objects remain immutable.

Operations always return **new instances**.

---

### 🔹 Key Concepts Tested

- Equality across volume units  
- Conversion between L ↔ mL ↔ gallon  
- Addition with same units  
- Addition with mixed units  
- Addition with explicit target unit  
- Cross-category comparison prevention  
- Precision and rounding handling  
- Edge cases (zero, negative, large values)

---

### 🔹 Example Output

**Equality**

```
new Quantity<>(1.0, LITRE)
.equals(new Quantity<>(1000.0, MILLILITRE))

→ true
```

```
new Quantity<>(3.78541, LITRE)
.equals(new Quantity<>(1.0, GALLON))

→ true
```

---

**Conversions**

```
new Quantity<>(1.0, LITRE).convertTo(MILLILITRE)

→ Quantity(1000.0, MILLILITRE)
```

```
new Quantity<>(2.0, GALLON).convertTo(LITRE)

→ Quantity(~7.57082, LITRE)
```

---

**Addition**

Implicit target unit:

```
new Quantity<>(1.0, LITRE)
.add(new Quantity<>(1000.0, MILLILITRE))

→ Quantity(2.0, LITRE)
```

Explicit target unit:

```
new Quantity<>(1.0, LITRE)
.add(new Quantity<>(1000.0, MILLILITRE), MILLILITRE)

→ Quantity(2000.0, MILLILITRE)
```

---

**Category Safety**

```
new Quantity<>(1.0, LITRE)
.equals(new Quantity<>(1.0, FOOT))

→ false
```

---

### 🔗 Code Link

[Quantity Measurement – UC11](http://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC11-VolumeMeasurement)
