# QuantityMeasurementApp

### 🗓 UC1: Feet Measurement Equality  


### 🔹 Description  

The `QuantityMeasurementApp` is responsible for checking the equality of two numerical values measured in feet.  
It ensures accurate comparison and handles floating-point precision and edge cases properly.

### 🔹 Preconditions  

- `QuantityMeasurementApp` class is instantiated  
- Two numerical values in feet are provided  

### 🔹 Main Flow  

1. User inputs two numerical values (in feet)  
2. Input values are validated  
3. Two `Feet` objects are created  
4. `equals()` method is invoked  
5. Result (true/false) is displayed  

### 🔹 Postconditions  

- Returns `true` if both measurements are equal  
- Returns `false` if values differ  

### 🔹 Implementation Highlights  

- Inner `Feet` class with encapsulated `private final double value`  
- Immutable design  
- Overridden `equals()` method  
- Floating-point comparison using `Double.compare()`  
- Proper null and type checking  

### 🔹 Concepts Learned  

- Object equality contract (`equals()` method)  
- Floating-point comparison best practices  
- Null safety and type safety  
- Encapsulation and immutability  
- Value-based equality design  

### 🔹 Equality Contract Rules Tested  

- Reflexive  
- Symmetric  
- Transitive  
- Consistent  
- Null handling  


### ✅ Test Cases  

1. `testEquality_SameValue()`  
2. `testEquality_DifferentValue()`  
3. `testEquality_NullComparison()`  
4. `testEquality_NonNumericInput()`  
5. `testEquality_SameReference()`  


*🔗 Code link:*  
[Quantity Measurement – UC1](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC1-FeetEquality)

---
