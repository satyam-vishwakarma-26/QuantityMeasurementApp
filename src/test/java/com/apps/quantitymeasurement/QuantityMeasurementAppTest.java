package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.LengthUnit;
public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // UC1–UC4 : Equality Tests

    @Test
    void testFeetEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    void testInchesEquality() {
        assertTrue(new Length(12.0, LengthUnit.INCHES)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testFeetInchesEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testYardEqualsFeet() {
        assertTrue(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(3.0, LengthUnit.FEET)));
    }

    @Test
    void testCentimeterEqualsInches() {
        assertTrue(new Length(100.0, LengthUnit.CENTIMETERS)
                .equals(new Length(39.3700787, LengthUnit.INCHES)));
    }

    @Test
    void testInequalitySameUnit() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    void testCrossUnitInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(10.0, LengthUnit.INCHES)));
    }

    @Test
    void testReflexiveSymmetricTransitive() {

        Length a = new Length(1.0, LengthUnit.YARDS);
        Length b = new Length(3.0, LengthUnit.FEET);
        Length c = new Length(36.0, LengthUnit.INCHES);

        // reflexive
        assertTrue(a.equals(a));

        // symmetric
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // transitive
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEqualsWithNull() {
        Length l = new Length(1.0, LengthUnit.FEET);
        assertFalse(l.equals(null));
    }

    // UC5 : Conversion Tests

    @Test
    void testStaticConversionFeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testInstanceConversionYardsToInches() {
        Length yards = new Length(2.0, LengthUnit.YARDS);
        Length inches = yards.convertTo(LengthUnit.INCHES);

        assertEquals(72.0, inches.getValue(), EPS);
    }

    @Test
    void testConvertZero() {
        assertEquals(0.0,
                Length.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConvertNegativeValue() {
        assertEquals(-12.0,
                Length.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    // UC6 : Addition (implicit target)

    @Test
    void testAdditionSameUnit() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(2.0, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdditionCrossUnit() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdditionWithZero() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(0.0, LengthUnit.INCHES));

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testAdditionNegative() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(-2.0, LengthUnit.FEET));

        assertEquals(3.0, result.getValue(), EPS);
    }

    // UC7 : Addition (explicit target)

    @Test
    void testAdditionExplicitTargetFeet() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES),
                        LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAdditionExplicitTargetInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES),
                        LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPS);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAdditionExplicitTargetYards() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), 1e-4);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAdditionCommutativityWithTarget() {

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result1 = a.add(b, LengthUnit.YARDS);
        Length result2 = b.add(a, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPS);
    }

    // Validation Tests

    @Test
    void testAddNullThrowsException() {
        Length l = new Length(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> l.add(null));
    }

    @Test
    void testAddNullTargetThrowsException() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class,
                () -> l1.add(l2, null));
    }

    @Test
    void testConstructorRejectsNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    void testConstructorRejectsNaN() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testConstructorRejectsInfinite() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }
    
    //Test Standalone Enum Conversion to Base Unit
    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPS);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPS);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPS);
    }
    
    //Test Convert From Base Unit
    
    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPS);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPS);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                1e-4);
    }
    
    //Round-Trip Conversion Test
    @Test
    void testRoundTripConversion() {

        double original = 5.0;

        double converted =
                Length.convert(original, LengthUnit.FEET, LengthUnit.INCHES);

        double roundTrip =
                Length.convert(converted, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(original, roundTrip, EPS);
    }
    
    //Test Equality Uses Refactored Enum
    
    @Test
    void testRefactoredEqualityStillWorks() {
        assertTrue(new Length(36.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }
    
    //Test HashCode Consistency
    
    @Test
    void testEqualObjectsHaveSameHashCode() {

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        assertEquals(a.hashCode(), b.hashCode());
    }
    
    //Test ConvertTo Instance Method Delegation
    
    @Test
    void testInstanceConvertToUsesEnum() {

        Length l = new Length(1.0, LengthUnit.FEET);
        Length converted = l.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, converted.getValue(), EPS);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
    }
}