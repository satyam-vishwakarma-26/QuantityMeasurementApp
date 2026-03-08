# QuantityMeasurementApp

### 🗓 UC5: Unit-to-Unit Conversion  

---

### 🔹 Description  

UC5 extends the **QuantityLength system introduced in UC3 and UC4** by adding **explicit unit conversion functionality**.

Earlier use cases focused on equality comparisons between different units such as feet, inches, yards, and centimeters. UC5 introduces a **conversion API** that allows converting a measurement from one unit to another (for example: feet → inches, yards → feet, centimeters → inches).

The conversion logic relies on centralized **conversion factors defined in the `LengthUnit` enum**, ensuring consistency, accuracy, and maintainability across all supported units.

---

### 🔹 Preconditions  

- `QuantityLength` class exists from UC3/UC4  
- `LengthUnit` enum includes:
  - `FEET`
  - `INCHES`
  - `YARDS`
  - `CENTIMETERS`
- Conversion factors for each unit are defined relative to a base unit  

---

### 🔹 Main Flow  

1. User calls the conversion method with value, source unit, and target unit  
2. The method validates that the value is numeric and units are valid  
3. The input value is converted to a common base unit  
4. The base value is converted to the target unit  
5. The converted numeric result is returned to the user  

---

### 🔹 Postconditions  

- Returns the converted value in the requested unit  
- Invalid inputs (null units, NaN, infinite values) throw exceptions  
- Conversion maintains mathematical equivalence within floating-point precision  

---

### 🔹 Implementation Highlights  

- Static conversion API method  
- Conversion based on enum conversion factors  
- Uses base-unit normalization for accuracy  
- Supports conversions between all units  
- Works seamlessly with existing equality logic  

---

### 🔹 Concepts Learned  

**Enum with Conversion Factors**
- Units and their conversion factors are centralized in the enum  
- Makes the system easier to extend with new units  

**Immutability**
- Enum constants remain immutable and thread-safe  

**Encapsulation**
- Conversion logic handled through helper methods inside the class  

**API Design**
- Clean static method interface for conversions  

**Method Overriding**
- `equals()` overridden for value comparison  
- `toString()` overridden for readable output  

**Method Overloading**
- Demonstration methods support different parameter inputs  

---

### 🔹 Key Concepts Tested  

- Basic unit conversions  
- Cross-unit conversions  
- Bidirectional conversions  
- Base unit normalization  
- Conversion factor accuracy  
- Floating-point precision handling  
- Zero value conversion  
- Negative value conversion  
- Same-unit conversion  
- Invalid input validation  

---

### ✅ Test Cases  

1. `testConversion_FeetToInches()`  
2. `testConversion_InchesToFeet()`  
3. `testConversion_YardsToInches()`  
4. `testConversion_InchesToYards()`  
5. `testConversion_CentimetersToInches()`  
6. `testConversion_FeetToYards()`  
7. `testConversion_RoundTrip_PreservesValue()`  
8. `testConversion_ZeroValue()`  
9. `testConversion_NegativeValue()`  
10. `testConversion_InvalidUnit_Throws()`  
11. `testConversion_NaNOrInfinite_Throws()`  
12. `testConversion_PrecisionTolerance()`  

---

### 🔹 Example Output  

Input: `convert(1.0, FEET, INCHES)`  
Output: **12.0**

Input: `convert(3.0, YARDS, FEET)`  
Output: **9.0**

Input: `convert(36.0, INCHES, YARDS)`  
Output: **1.0**

Input: `convert(1.0, CENTIMETERS, INCHES)`  
Output: **0.393701**

Input: `convert(0.0, FEET, INCHES)`  
Output: **0.0**

---

🔗 **Code link:**  
[Quantity Measurement – UC5](http://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC5-UnitConversion)
