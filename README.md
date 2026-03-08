# QuantityMeasurementApp

### 🗓 UC4: Extended Unit Support  

---

### 🔹 Description  

UC4 extends the **generic QuantityLength design from UC3** by introducing **additional length units: Yards and Centimeters**.

The system now supports seamless comparisons across **feet, inches, yards, and centimeters** using a centralized conversion mechanism defined in the `LengthUnit` enum.

Because of the **generic design**, adding new units requires only updating the enum without modifying the core class logic. This demonstrates the scalability and maintainability of the DRY-based architecture.

---

### 🔹 Preconditions  

- `QuantityMeasurementApp` uses the **QuantityLength class from UC3**
- Two numerical values with units (feet, inches, yards, centimeters) are provided
- Conversion factors are defined in the `LengthUnit` enum  

Conversions used:
- **1 yard = 3 feet = 36 inches**
- **1 cm = 0.393701 inches**

---

### 🔹 Main Flow  

1. User inputs two values with their unit types  
2. System validates numeric values and supported units  
3. Values are converted to a common base unit  
4. Converted values are compared using `equals()`  
5. Equality result is returned to the user  

---

### 🔹 Postconditions  

- Returns **true** if converted values are equal  
- Returns **false** if values differ  
- UC1, UC2, and UC3 functionality remains unchanged  
- Supports comparisons between:
  - yard ↔ feet  
  - yard ↔ inches  
  - cm ↔ inches  
  - cm ↔ feet  
  - cm ↔ yard  

---

### 🔹 Implementation Highlights  

- Added new constants to `LengthUnit` enum:
  - `YARDS`
  - `CENTIMETERS`
- Conversion factors centralized in enum
- No modification required in `QuantityLength` class
- Equality logic automatically supports new units

---

### 🔹 Concepts Learned  

**Scalability of Generic Design**

- New units added without modifying class logic

**Conversion Factor Management**

- Centralized conversion logic in enum

**Unit Relationships**

- 1 yard = 3 feet = 36 inches  
- 1 cm = 0.393701 inches  

**Enum Extensibility**

- Safe and structured unit management

**Mathematical Accuracy**

- Correct cross-unit conversions

**DRY Principle Validation**

- No duplicate unit classes required

**Backward Compatibility**

- UC1–UC3 test cases remain valid

---

### 🔹 Key Concepts Tested  

- Yard-to-Yard Equality  
- CM-to-CM Equality  
- Cross-unit conversions  
- Symmetric equality (A=B and B=A)  
- Transitive property of equality  
- Unit validation  
- Precision in floating-point comparison  

---

### ✅ Test Cases  

1. `testEquality_YardToYard_SameValue()`  
2. `testEquality_YardToYard_DifferentValue()`  
3. `testEquality_YardToFeet_EquivalentValue()`  
4. `testEquality_FeetToYard_EquivalentValue()`  
5. `testEquality_YardToInches_EquivalentValue()`  
6. `testEquality_InchesToYard_EquivalentValue()`  
7. `testEquality_YardToFeet_NonEquivalentValue()`  
8. `testEquality_CentimetersToInches_EquivalentValue()`  
9. `testEquality_CentimetersToFeet_NonEquivalentValue()`  
10. `testEquality_MultiUnit_TransitiveProperty()`  
11. `testEquality_YardWithNullUnit()`  
12. `testEquality_YardSameReference()`  
13. `testEquality_YardNullComparison()`  
14. `testEquality_CentimetersWithNullUnit()`  
15. `testEquality_CentimetersSameReference()`  
16. `testEquality_CentimetersNullComparison()`  
17. `testEquality_AllUnits_ComplexScenario()`  

---

### 🔹 Example Output  

Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)  
Output: Equal (true)  

Input: Quantity(1.0, YARDS) and Quantity(36.0, INCHES)  
Output: Equal (true)  

Input: Quantity(2.0, YARDS) and Quantity(2.0, YARDS)  
Output: Equal (true)  

Input: Quantity(2.0, CENTIMETERS) and Quantity(2.0, CENTIMETERS)  
Output: Equal (true)  

Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCHES)  
Output: Equal (true)  

---

*🔗 Code link:*  
[Quantity Measurement – UC4](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC4-YardEquality)
