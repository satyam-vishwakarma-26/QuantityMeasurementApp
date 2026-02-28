# QuantityMeasurementApp

### 🗓 UC3: Generic Quantity Class for DRY Principle  

### 🔹 Description  

UC3 refactors the separate **Feet** and **Inches** classes into a single generic `QuantityLength` class to eliminate code duplication and follow the **DRY (Don't Repeat Yourself)** principle.

Instead of maintaining identical constructors and `equals()` methods in multiple classes, a unified class handles all length units using an enum-based design. This improves scalability, maintainability, and extensibility while preserving all functionality from UC1 and UC2.

### 🔹 Preconditions  

- `QuantityMeasurementApp` is instantiated  
- Two numerical values with unit types (feet, inches, etc.) are provided  
- Conversion factors are defined as constants in an enum  

### 🔹 Main Flow  

1. User inputs two values with their unit types  
2. Inputs are validated (numeric + supported unit)  
3. Values are converted to a common base unit (feet)  
4. Converted values are compared using `equals()`  
5. Equality result is returned  

### 🔹 Postconditions  

- Returns `true` if converted values are equal  
- Returns `false` if values differ  
- UC1 and UC2 functionality remains intact  
- Code duplication is removed  

### 🔹 Implementation Highlights  

- `LengthUnit` enum with conversion factors  
- Single `QuantityLength` class  
- Encapsulated `value` and `unit`  
- Centralized conversion logic  
- Overridden `equals()` using base-unit comparison  
- Immutable and scalable design  

### 🔹 Concepts Learned  

**DRY Principle**
- Eliminates duplicate logic  
- Simplifies maintenance  

**Polymorphism**
- Single class handles multiple unit types  

**Enum Usage**
- Type-safe unit definitions  
- Avoids magic strings  

**Abstraction**
- Conversion logic hidden from client  

**Encapsulation**
- Value + Unit bundled together  

**Equality Override**
- Cross-unit comparison supported  
- Value-based equality  

**Scalability**
- Easy to add new units  

**Refactoring Best Practices**
- Consolidation without breaking existing tests  

### 🔹 Key Concepts Tested  

- Equality Contract (Reflexive, Symmetric, Transitive, Consistent)  
- Null Safety  
- Type Safety  
- Cross-Unit Equality  
- Same-Unit Equality  
- Backward Compatibility (UC1 & UC2 tests pass)  

### ✅ Test Cases  

1. `testEquality_FeetToFeet_SameValue()`  
2. `testEquality_InchToInch_SameValue()`  
3. `testEquality_InchToFeet_EquivalentValue()`  
4. `testEquality_FeetToFeet_DifferentValue()`  
5. `testEquality_InchToInch_DifferentValue()`  
6. `testEquality_InvalidUnit()`  
7. `testEquality_NullUnit()`  
8. `testEquality_SameReference()`  
9. `testEquality_NullComparison()`  

### 🔹 Example Output  

Input: Quantity(1.0, "feet") and Quantity(12.0, "inches")  
Output: Equal (true)  

Input: Quantity(1.0, "inch") and Quantity(1.0, "inch")  
Output: Equal (true)  

*🔗 Code link:*  
[Quantity Measurement – UC3](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC3-GenricLength)

---
