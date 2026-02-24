package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // LENGTH TESTS (Generic Quantity)

    @Test
    void testLengthEquality() {
        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> converted =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, converted.getValue(), EPS);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
    }

    @Test
    void testLengthAdditionImplicitTarget() {
        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testLengthAdditionExplicitTarget() {
        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                q1.add(q2, LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), 1e-4);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // WEIGHT TESTS (Generic Quantity)

    @Test
    void testWeightEquality() {
        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testWeightKilogramToPoundEquality() {
        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(2.20462, WeightUnit.POUND);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testWeightConversion() {
        Quantity<WeightUnit> q =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> converted =
                q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, converted.getValue(), EPS);
        assertEquals(WeightUnit.GRAM, converted.getUnit());
    }

    @Test
    void testWeightAdditionImplicitTarget() {
        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testWeightAdditionExplicitTarget() {
        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                q1.add(q2, WeightUnit.POUND);

        assertEquals(4.40924, result.getValue(), 1e-4);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    // CROSS CATEGORY SAFETY

    @Test
    void testLengthAndWeightNotEqual() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // EQUALITY CONTRACT TESTS

    @Test
    void testReflexiveSymmetricTransitive() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> b =
                new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> c =
                new Quantity<>(36.0, LengthUnit.INCHES);

        // Reflexive
        assertTrue(a.equals(a));

        // Symmetric
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // Transitive
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEqualObjectsHaveSameHashCode() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(a.hashCode(), b.hashCode());
    }

    // VALIDATION TESTS

    @Test
    void testConstructorRejectsNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testConstructorRejectsNaN() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testAddNullThrowsException() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.add(null));
    }
    
 // VOLUME TESTS (Generic Quantity) - UC11

 @Test
 void testVolumeEquality_LitreToMillilitre() {

     Quantity<VolumeUnit> v1 =
             new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<VolumeUnit> v2 =
             new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

     assertTrue(v1.equals(v2));
 }

 @Test
 void testVolumeEquality_LitreToGallon() {

     Quantity<VolumeUnit> v1 =
             new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<VolumeUnit> v2 =
             new Quantity<>(0.264172, VolumeUnit.GALLON);

     assertTrue(v1.equals(v2));
 }

 @Test
 void testVolumeConversion() {

     Quantity<VolumeUnit> v =
             new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<VolumeUnit> converted =
             v.convertTo(VolumeUnit.MILLILITRE);

     assertEquals(1000.0, converted.getValue(), EPS);
     assertEquals(VolumeUnit.MILLILITRE, converted.getUnit());
 }

 @Test
 void testVolumeConversion_GallonToLitre() {

     Quantity<VolumeUnit> v =
             new Quantity<>(1.0, VolumeUnit.GALLON);

     Quantity<VolumeUnit> converted =
             v.convertTo(VolumeUnit.LITRE);

     assertEquals(3.78541, converted.getValue(), 1e-4);
 }

 @Test
 void testVolumeAdditionImplicitTarget() {

     Quantity<VolumeUnit> v1 =
             new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<VolumeUnit> v2 =
             new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

     Quantity<VolumeUnit> result = v1.add(v2);

     assertEquals(2.0, result.getValue(), EPS);
     assertEquals(VolumeUnit.LITRE, result.getUnit());
 }

 @Test
 void testVolumeAdditionExplicitTarget() {

     Quantity<VolumeUnit> v1 =
             new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<VolumeUnit> v2 =
             new Quantity<>(1.0, VolumeUnit.GALLON);

     Quantity<VolumeUnit> result =
             v1.add(v2, VolumeUnit.MILLILITRE);

     assertEquals(4785.41, result.getValue(), 1e-2);
     assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
 }
 
 @Test
 void testVolumeAndLengthNotEqual() {

     Quantity<VolumeUnit> volume =
             new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<LengthUnit> length =
             new Quantity<>(1.0, LengthUnit.FEET);

     assertFalse(volume.equals(length));
 }

 @Test
 void testVolumeAndWeightNotEqual() {

     Quantity<VolumeUnit> volume =
             new Quantity<>(1.0, VolumeUnit.LITRE);

     Quantity<WeightUnit> weight =
             new Quantity<>(1.0, WeightUnit.KILOGRAM);

     assertFalse(volume.equals(weight));
 }
//SUBTRACTION TESTS (UC12)

@Test
void testSubtractionSameUnit_Length() {

  Quantity<LengthUnit> q1 =
          new Quantity<>(10.0, LengthUnit.FEET);

  Quantity<LengthUnit> q2 =
          new Quantity<>(5.0, LengthUnit.FEET);

  Quantity<LengthUnit> result = q1.subtract(q2);

  assertEquals(5.0, result.getValue(), EPS);
  assertEquals(LengthUnit.FEET, result.getUnit());
}

@Test
void testSubtractionCrossUnit_Length() {

  Quantity<LengthUnit> q1 =
          new Quantity<>(10.0, LengthUnit.FEET);

  Quantity<LengthUnit> q2 =
          new Quantity<>(6.0, LengthUnit.INCHES);

  Quantity<LengthUnit> result = q1.subtract(q2);

  assertEquals(9.5, result.getValue(), EPS);
}

@Test
void testSubtractionExplicitTarget_Volume() {

  Quantity<VolumeUnit> v1 =
          new Quantity<>(5.0, VolumeUnit.LITRE);

  Quantity<VolumeUnit> v2 =
          new Quantity<>(2.0, VolumeUnit.LITRE);

  Quantity<VolumeUnit> result =
          v1.subtract(v2, VolumeUnit.MILLILITRE);

  assertEquals(3000.0, result.getValue(), EPS);
  assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
}

@Test
void testSubtractionNegativeResult() {

  Quantity<LengthUnit> q1 =
          new Quantity<>(5.0, LengthUnit.FEET);

  Quantity<LengthUnit> q2 =
          new Quantity<>(10.0, LengthUnit.FEET);

  Quantity<LengthUnit> result = q1.subtract(q2);

  assertEquals(-5.0, result.getValue(), EPS);
}

@Test
void testSubtractionZeroResult() {

  Quantity<LengthUnit> q1 =
          new Quantity<>(10.0, LengthUnit.FEET);

  Quantity<LengthUnit> q2 =
          new Quantity<>(120.0, LengthUnit.INCHES);

  Quantity<LengthUnit> result = q1.subtract(q2);

  assertEquals(0.0, result.getValue(), EPS);
}

@Test
void testSubtractionNullThrowsException() {

  Quantity<LengthUnit> q =
          new Quantity<>(10.0, LengthUnit.FEET);

  assertThrows(IllegalArgumentException.class,
          () -> q.subtract(null));
}


//DIVISION TESTS (UC12)

@Test
void testDivisionSameUnit_Length() {

  Quantity<LengthUnit> q1 =
          new Quantity<>(10.0, LengthUnit.FEET);

  Quantity<LengthUnit> q2 =
          new Quantity<>(2.0, LengthUnit.FEET);

  double result = q1.divide(q2);

  assertEquals(5.0, result, EPS);
}

@Test
void testDivisionCrossUnit_Length() {

  Quantity<LengthUnit> q1 =
          new Quantity<>(24.0, LengthUnit.INCHES);

  Quantity<LengthUnit> q2 =
          new Quantity<>(2.0, LengthUnit.FEET);

  double result = q1.divide(q2);

  assertEquals(1.0, result, EPS);
}

@Test
void testDivisionRatioLessThanOne() {

  Quantity<VolumeUnit> v1 =
          new Quantity<>(5.0, VolumeUnit.LITRE);

  Quantity<VolumeUnit> v2 =
          new Quantity<>(10.0, VolumeUnit.LITRE);

  double result = v1.divide(v2);

  assertEquals(0.5, result, EPS);
}

@Test
void testDivisionByZeroThrowsException() {

  Quantity<WeightUnit> w1 =
          new Quantity<>(10.0, WeightUnit.KILOGRAM);

  Quantity<WeightUnit> w2 =
          new Quantity<>(0.0, WeightUnit.KILOGRAM);

  assertThrows(ArithmeticException.class,
          () -> w1.divide(w2));
}

@Test
void testDivisionCrossCategoryThrowsException() {

  Quantity<LengthUnit> length =
          new Quantity<>(10.0, LengthUnit.FEET);

  Quantity<WeightUnit> weight =
          new Quantity<>(5.0, WeightUnit.KILOGRAM);

  assertThrows(IllegalArgumentException.class,
          () -> length.divide((Quantity) weight));
}

//Explicit Target Null Validation

@Test
void testSubtractNullTargetUnitThrowsException() {

    Quantity<LengthUnit> q1 =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> q2 =
            new Quantity<>(5.0, LengthUnit.FEET);

    assertThrows(IllegalArgumentException.class,
            () -> q1.subtract(q2, null));
}
    
// Divide Null Operand

@Test
void testDivisionNullOperandThrowsException() {

    Quantity<LengthUnit> q =
            new Quantity<>(10.0, LengthUnit.FEET);

    assertThrows(IllegalArgumentException.class,
            () -> q.divide(null));
}
// Subtraction Non-Commutative
@Test
void testSubtractionNonCommutative() {

    Quantity<LengthUnit> q1 =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> q2 =
            new Quantity<>(5.0, LengthUnit.FEET);

    assertNotEquals(
            q1.subtract(q2).getValue(),
            q2.subtract(q1).getValue()
    );
}
//    Division Non-Commutative
@Test
void testDivisionNonCommutative() {

    Quantity<LengthUnit> q1 =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> q2 =
            new Quantity<>(5.0, LengthUnit.FEET);

    assertNotEquals(
            q1.divide(q2),
            q2.divide(q1)
    );
}
// Immutability After Subtraction
@Test
void testImmutabilityAfterSubtraction() {

    Quantity<LengthUnit> original =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> other =
            new Quantity<>(5.0, LengthUnit.FEET);

    original.subtract(other);

    assertEquals(10.0, original.getValue(), EPS);
}
// Immutability After Division
@Test
void testImmutabilityAfterDivision() {

    Quantity<LengthUnit> original =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> other =
            new Quantity<>(2.0, LengthUnit.FEET);

    original.divide(other);

    assertEquals(10.0, original.getValue(), EPS);
}
// Add–Subtract Inverse Property
@Test
void testAdditionSubtractionInverse() {

    Quantity<LengthUnit> a =
            new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> b =
            new Quantity<>(5.0, LengthUnit.FEET);

    Quantity<LengthUnit> result =
            a.add(b).subtract(b);

    assertTrue(result.equals(a));
}
// Chained Operations
@Test
void testChainedOperations() {

    Quantity<LengthUnit> result =
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                    .subtract(new Quantity<>(1.0, LengthUnit.FEET));

    assertEquals(7.0, result.getValue(), EPS);
}


//TEMPERATURE EQUALITY TESTS (UC14)

@Test
void testTemperatureEquality_CelsiusToFahrenheit() {

 Quantity<TemperatureUnit> c =
         new Quantity<>(0.0, TemperatureUnit.CELSIUS);

 Quantity<TemperatureUnit> f =
         new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

 assertTrue(c.equals(f));
}

@Test
void testTemperatureEquality_CelsiusToKelvin() {

 Quantity<TemperatureUnit> c =
         new Quantity<>(0.0, TemperatureUnit.CELSIUS);

 Quantity<TemperatureUnit> k =
         new Quantity<>(273.15, TemperatureUnit.KELVIN);

 assertTrue(c.equals(k));
}

@Test
void testTemperatureEquality_Negative40() {

 Quantity<TemperatureUnit> c =
         new Quantity<>(-40.0, TemperatureUnit.CELSIUS);

 Quantity<TemperatureUnit> f =
         new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

 assertTrue(c.equals(f));
}

//TEMPERATURE CONVERSION TESTS

@Test
void testTemperatureConversion_CelsiusToFahrenheit() {

 Quantity<TemperatureUnit> c =
         new Quantity<>(100.0, TemperatureUnit.CELSIUS);

 Quantity<TemperatureUnit> f =
         c.convertTo(TemperatureUnit.FAHRENHEIT);

 assertEquals(212.0, f.getValue(), 1e-4);
}

@Test
void testTemperatureConversion_FahrenheitToCelsius() {

 Quantity<TemperatureUnit> f =
         new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

 Quantity<TemperatureUnit> c =
         f.convertTo(TemperatureUnit.CELSIUS);

 assertEquals(0.0, c.getValue(), 1e-4);
}

@Test
void testTemperatureConversion_KelvinToCelsius() {

 Quantity<TemperatureUnit> k =
         new Quantity<>(273.15, TemperatureUnit.KELVIN);

 Quantity<TemperatureUnit> c =
         k.convertTo(TemperatureUnit.CELSIUS);

 assertEquals(0.0, c.getValue(), 1e-4);
}


//TEMPERATURE UNSUPPORTED OPERATIONS

@Test
void testTemperatureAdditionUnsupported() {

 Quantity<TemperatureUnit> t1 =
         new Quantity<>(100.0, TemperatureUnit.CELSIUS);

 Quantity<TemperatureUnit> t2 =
         new Quantity<>(50.0, TemperatureUnit.CELSIUS);

 assertThrows(UnsupportedOperationException.class,
         () -> t1.add(t2));
}

@Test
void testTemperatureSubtractionUnsupported() {

 Quantity<TemperatureUnit> t1 =
         new Quantity<>(100.0, TemperatureUnit.CELSIUS);

 Quantity<TemperatureUnit> t2 =
         new Quantity<>(50.0, TemperatureUnit.CELSIUS);

 assertThrows(UnsupportedOperationException.class,
         () -> t1.subtract(t2));
}

@Test
void testTemperatureDivisionUnsupported() {

 Quantity<TemperatureUnit> t1 =
         new Quantity<>(100.0, TemperatureUnit.CELSIUS);

 Quantity<TemperatureUnit> t2 =
         new Quantity<>(50.0, TemperatureUnit.CELSIUS);

 assertThrows(UnsupportedOperationException.class,
         () -> t1.divide(t2));
}
//TEMPERATURE CROSS CATEGORY SAFETY

@Test
void testTemperatureAndLengthNotEqual() {

 Quantity<TemperatureUnit> temp =
         new Quantity<>(100.0, TemperatureUnit.CELSIUS);

 Quantity<LengthUnit> length =
         new Quantity<>(100.0, LengthUnit.FEET);

 assertFalse(temp.equals(length));
}

@Test
void testTemperatureAndWeightNotEqual() {

 Quantity<TemperatureUnit> temp =
         new Quantity<>(100.0, TemperatureUnit.CELSIUS);

 Quantity<WeightUnit> weight =
         new Quantity<>(100.0, WeightUnit.KILOGRAM);

 assertFalse(temp.equals(weight));
}
}