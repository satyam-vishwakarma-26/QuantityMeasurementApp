# QuantityMeasurementApp

### 🗓 UC2: Feet and Inches Measurement Equality  

### 🔹 Description  

This use case extends UC1 to support equality checks for both **Feet** and **Inches** measurements.  
Feet and Inches are treated as separate entities and compared independently.  
The implementation ensures proper handling of floating-point precision and edge cases.

### 🔹 Preconditions  

- `QuantityMeasurementApp` class is instantiated  
- Two numerical values (in feet and inches) are provided for comparison  

### 🔹 Main Flow  

1. Main method calls static method to compare two **Feet** values  
2. Main method calls static method to compare two **Inches** values  
3. Static methods instantiate respective `Feet` and `Inches` objects  
4. `equals()` method is invoked for comparison  
5. Result (true/false) is displayed  

### 🔹 Postconditions  

- Returns `true` if both measurements are equal  
- Returns `false` if values differ  
- Supports inch-to-inch and feet-to-feet comparisons  

### 🔹 Implementation Highlights  

- Separate `Feet` and `Inches` classes  
- Encapsulated `private final double value`  
- Immutable object design  
- Overridden `equals()` method  
- Floating-point comparison using `Double.compare()`  
- Proper null and type checking  
- Reduced dependency on `main()` using static helper methods  

### 🔹 Concepts Learned  

- Object equality implementation  
- Floating-point precision handling  
- Null safety and type safety  
- Encapsulation and immutability  
- Equality contract validation  

### 🔹 Key Concepts Tested  

- Reflexive property  
- Symmetric property  
- Transitive property  
- Consistent results  
- Null handling  
- Value-based equality  

### ✅ Test Cases  

1. `testEquality_SameValue()`  
2. `testEquality_DifferentValue()`  
3. `testEquality_NullComparison()`  
4. `testEquality_NonNumericInput()`  
5. `testEquality_SameReference()`  

### ⚠️ Design Limitation  

The current implementation violates the **DRY (Don't Repeat Yourself)** principle:

- Duplicate constructor logic  
- Identical `equals()` implementation  
- Repeated value-handling code  

A better approach would be:
- Create a generic `Quantity` class  
- Use a unit parameter (Feet/Inches)  
- Centralize equality logic to avoid duplication  

*🔗 Code link:*  
[Quantity Measurement – UC2](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/feature/UC2-InchEquality)

---
