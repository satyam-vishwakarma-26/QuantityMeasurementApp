# QuantityMeasurementApp

### 🗓 UC10: Generic Quantity Class with Unit Interface for Multi-Category Support

---

### 🔹 Description

UC10 refactors the architecture of the **Quantity Measurement Application** to remove duplication introduced in UC9.

Instead of having separate classes such as:

- `QuantityLength`
- `QuantityWeight`

UC10 introduces a **single generic class**:

```
Quantity<U extends IMeasurable>
```

This class works with **any measurement category** (length, weight, etc.) as long as the unit enum implements the **IMeasurable interface**.

This design:

- Removes duplicate code
- Improves maintainability
- Supports unlimited measurement categories
- Preserves all functionality from **UC1–UC9**

---

### 🔹 Preconditions

- All features from **UC1–UC9** are working
- `IMeasurable` interface is created
- `LengthUnit` and `WeightUnit` implement `IMeasurable`
- Generic class `Quantity<U extends IMeasurable>` replaces previous quantity classes
- Type safety is maintained using **Java generics**

---

### 🔹 Main Flow

**1. Define IMeasurable Interface**

Standardizes unit behavior:

```
double getConversionFactor()
double convertToBaseUnit(double value)
double convertFromBaseUnit(double baseValue)
String getUnitName()
```

---

**2. Refactor Unit Enums**

Both enums now implement the interface:

```
enum LengthUnit implements IMeasurable
enum WeightUnit implements IMeasurable
```

Each enum handles its own conversion logic.

---

**3. Create Generic Quantity Class**

```
class Quantity<U extends IMeasurable>
```

Fields:

```
double value
U unit
```

Supported operations:

- `equals()` → compare quantities
- `convertTo(targetUnit)` → convert units
- `add(other)` → addition in same category
- `add(other, targetUnit)` → addition with specified unit

All operations normalize values through the **base unit**.

---

**4. Simplify QuantityMeasurementApp**

Remove category-specific methods and replace them with **generic methods**:

```
demonstrateEquality(Quantity<?> q1, Quantity<?> q2)
demonstrateConversion(Quantity<?> q)
demonstrateAddition(Quantity<?> q1, Quantity<?> q2)
```

This removes duplicate logic.

---

### 🔹 Postconditions

- A **single generic `Quantity<U>` class** replaces multiple quantity classes
- All unit enums implement **IMeasurable**
- Code duplication is removed
- Existing functionality from **UC1–UC9 remains unchanged**
- New measurement categories can be added easily

Example:

```
VolumeUnit
TemperatureUnit
TimeUnit
```

Only a new enum implementing `IMeasurable` is required.

---

### 🔹 Concepts Learned

**Generic Programming**

Java generics allow one class to work with multiple unit types while maintaining type safety.

---

**Interface-Based Design**

`IMeasurable` defines a common contract for all measurement units.

---

**DRY Principle**

Core logic for comparison, conversion, and addition is implemented **once** in `Quantity<U>`.

---

**Single Responsibility Principle**

- `IMeasurable` → defines unit behavior  
- `Quantity<U>` → handles arithmetic and comparisons  
- Unit enums → store conversion factors  
- `QuantityMeasurementApp` → demonstration logic only

---

**Open-Closed Principle**

The system is **open for extension but closed for modification**.

New measurement categories can be added without changing existing classes.

---

**Type Safety**

Generics prevent incorrect comparisons:

```
Quantity<LengthUnit> ≠ Quantity<WeightUnit>
```

---

### 🔹 Key Concepts Tested

- `IMeasurable` interface implementation  
- Generic `Quantity<U>` operations  
- Length operations using generic class  
- Weight operations using generic class  
- Cross-category comparison prevention  
- Generic conversion logic  
- Addition with implicit and explicit target units  
- Backward compatibility with UC1–UC9

---

### 🔹 Example Output

**Length Operations**

```
new Quantity<>(1.0, LengthUnit.FEET)
.equals(new Quantity<>(12.0, LengthUnit.INCHES))

→ true
```

```
new Quantity<>(1.0, LengthUnit.FEET)
.convertTo(LengthUnit.INCHES)

→ Quantity(12.0, INCHES)
```

---

**Weight Operations**

```
new Quantity<>(1.0, WeightUnit.KILOGRAM)
.equals(new Quantity<>(1000.0, WeightUnit.GRAM))

→ true
```

```
new Quantity<>(1.0, WeightUnit.KILOGRAM)
.add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM)

→ Quantity(2.0, KILOGRAM)
```

---

**Cross-Category Prevention**

```
new Quantity<>(1.0, LengthUnit.FEET)
.equals(new Quantity<>(1.0, WeightUnit.KILOGRAM))

→ false
```

---

### 🔗 Code Link

[Quantity Measurement – UC10](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC10-GenericQuantity)
