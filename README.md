# QuantityMeasurementApp

### 🗓 UC13: Centralized Arithmetic Logic to Enforce DRY in Quantity Operations

---

### 🔹 Description

UC13 refactors the arithmetic operations introduced in **UC12 (addition, subtraction, division)** to eliminate duplicated logic and enforce the **DRY (Don't Repeat Yourself) principle**.

In UC12, each arithmetic method contained repeated steps such as:

- Operand validation  
- Category compatibility checks  
- Base unit conversion  
- Arithmetic computation  

UC13 introduces a **centralized private helper method** that performs all shared logic. Public methods like `add()`, `subtract()`, and `divide()` now delegate to this helper.

This refactoring **does not change the public API**. All functionality and results remain identical to UC12, but the internal implementation becomes cleaner, more maintainable, and scalable.

---

### 🔹 Preconditions

- Generic class `Quantity<U extends IMeasurable>` from **UC10** is implemented
- Arithmetic operations from **UC12** (add, subtract, divide) are working
- All unit enums (`LengthUnit`, `WeightUnit`, `VolumeUnit`) implement `IMeasurable`
- Existing UC12 tests pass before refactoring
- Refactoring affects **only internal implementation**

---

### 🔹 Main Flow

### 1️⃣ ArithmeticOperation Enum

A private enum defines supported arithmetic operations:

```
ADD
SUBTRACT
DIVIDE
```

Each enum constant defines how the arithmetic is performed on **base unit values**.

Example:

```
ADD.compute(a, b) → a + b
SUBTRACT.compute(a, b) → a - b
DIVIDE.compute(a, b) → a / b
```

This allows **clean operation dispatch** without if-else or switch statements.

---

### 2️⃣ Centralized Validation Helper

A private method validates operands before performing arithmetic.

Responsibilities:

- Check for **null operands**
- Verify **same measurement category**
- Ensure **finite numeric values**
- Validate **target unit (if required)**

Example helper:

```
validateArithmeticOperands(other, targetUnit, targetUnitRequired)
```

---

### 3️⃣ Core Arithmetic Helper

A centralized method performs the actual arithmetic on **base unit values**.

```
performBaseArithmetic(other, operation)
```

Steps:

1. Convert both quantities to **base units**
2. Execute arithmetic via `ArithmeticOperation`
3. Return the result in base units

---

### 4️⃣ Refactored Public Methods

Public methods delegate to the centralized helper.

**Addition**

```
add(other)
add(other, targetUnit)
```

**Subtraction**

```
subtract(other)
subtract(other, targetUnit)
```

**Division**

```
divide(other)
```

For add/subtract:

- Convert result to target unit
- Return new `Quantity<U>`

For division:

- Return **dimensionless double**

---

### 🔹 Postconditions

- Arithmetic logic is **centralized in helper methods**
- Code duplication across operations is removed
- Public method signatures remain unchanged
- All UC12 test cases pass without modification
- Error handling becomes consistent across operations
- Future operations (multiply, modulo, etc.) can reuse the same helper

---

### 🔹 Concepts Learned

**DRY Principle**

Common validation and conversion logic is implemented **once** instead of repeated across methods.

---

**Enum-Based Operation Dispatch**

Using an enum with computation logic eliminates complex conditional structures.

---

**Separation of Concerns**

- Public methods handle API behavior  
- Helper methods handle validation and computation

---

**Lambda Expressions**

Arithmetic operations can be implemented using lambda expressions with `DoubleBinaryOperator`.

---

**Functional Interfaces**

`DoubleBinaryOperator` enables passing arithmetic behavior as functional logic.

---

**Improved Maintainability**

Future changes to validation or conversion logic affect **all arithmetic operations automatically**.

---

### 🔹 Key Concepts Tested

- Validation consistency across operations  
- Helper method delegation from public APIs  
- Enum-based arithmetic execution  
- Backward compatibility with UC12 behavior  
- Correct base-unit conversion before arithmetic  
- Cross-category operation prevention  
- Consistent rounding for add/subtract  
- Immutability preservation  
- Explicit vs implicit target unit handling  
- DRY principle enforcement

---

### 🔹 Example Output

**Addition (unchanged behavior)**

```
new Quantity<>(1.0, FEET)
.add(new Quantity<>(12.0, INCHES))

→ Quantity(2.0, FEET)
```

---

**Subtraction**

```
new Quantity<>(10.0, FEET)
.subtract(new Quantity<>(6.0, INCHES))

→ Quantity(9.5, FEET)
```

---

**Division**

```
new Quantity<>(24.0, INCHES)
.divide(new Quantity<>(2.0, FEET))

→ 1.0
```

---

**Error Handling**

```
new Quantity<>(10.0, FEET).add(null)
→ IllegalArgumentException
```

```
new Quantity<>(10.0, FEET)
.divide(new Quantity<>(0.0, FEET))

→ ArithmeticException
```

---

### 🔗 Code Link

[Quantity Measurement – UC13](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmeticLogic)
