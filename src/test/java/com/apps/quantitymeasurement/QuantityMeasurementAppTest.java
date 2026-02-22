package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    /* ================= Equality Tests ================= */

    @Test
    void testFeetEquality() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(a.equals(b));
    }

    @Test
    void testInchesEquality() {
        Length a = new Length(1.0, Length.LengthUnit.INCHES);
        Length b = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
    }

    @Test
    void testFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
        assertTrue(inches.equals(feet));
    }

    @Test
    void testFeetInequality() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(a.equals(b));
    }

    @Test
    void testInchesInequality() {
        Length a = new Length(1.0, Length.LengthUnit.INCHES);
        Length b = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse(a.equals(b));
    }

    @Test
    void testCrossUnitInequality() {
        // 1 foot != 11 inches
        assertFalse(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(11.0, Length.LengthUnit.INCHES)));
    }

    @Test
    void testReferenceEqualitySameObject() {
        Length l = new Length(3.0, Length.LengthUnit.YARDS);
        assertTrue(l.equals(l));
    }

    @Test
    void equalsReturnsFalseForNull() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l.equals(null));
    }

    @Test
    void reflexiveSymmetricTransitiveProperty() {
        Length a = new Length(1.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);
        Length c = new Length(36.0, Length.LengthUnit.INCHES);
        // reflexive
        assertTrue(a.equals(a));
        // symmetric
        assertTrue(a.equals(b) && b.equals(a));
        // transitive
        assertTrue(a.equals(b) && b.equals(c) && a.equals(c));
    }

    /* ================= Yard & Centimeter Tests ================= */

    @Test
    void yardEquals36Inches() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    void centimeterEquals39Point3701Inches() {
        assertTrue(new Length(100.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(39.3701, Length.LengthUnit.INCHES)));
    }

    @Test
    void threeFeetEqualsOneYard() {
        assertTrue(new Length(3.0, Length.LengthUnit.FEET).equals(new Length(1.0, Length.LengthUnit.YARDS)));
    }

    @Test
    void thirtyPoint48CmEqualsOneFoot() {
        assertTrue(new Length(30.48, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    void yardNotEqualToInches() {
        assertFalse(new Length(1.5, Length.LengthUnit.YARDS)
                .equals(new Length(50.0, Length.LengthUnit.INCHES)));
    }

    /* ================= Conversion Tests ================= */

    @Test
    void convertFeetToInches() {
        double converted = Length.convert(3.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(36.0, converted, EPS);
    }

    @Test
    void convertYardsToInchesUsingOverloadedMethod() {
        Length lengthInYards = new Length(2.0, Length.LengthUnit.YARDS);
        Length lengthInInches = lengthInYards.convertTo(Length.LengthUnit.INCHES);
        assertEquals(72.0, lengthInInches.getValue(), EPS);
    }

    @Test
    void convertInchesToFeet() {
        assertEquals(2.0, Length.convert(24.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET), EPS);
    }

    @Test
    void convertCentimetersToInches() {
        double result = Length.convert(2.54, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);
        // 2.54 cm ~ 1.0 in (within small epsilon)
        assertEquals(1.0, result, 1e-4);
    }

    @Test
    void roundTripConversionPreservesValue() {
        double v = 5.0;
        double aToB = Length.convert(v, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        double back = Length.convert(aToB, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        assertEquals(v, back, EPS);
    }

    @Test
    void zeroValueConversion() {
        assertEquals(0.0, Length.convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES), EPS);
    }

    @Test
    void negativeValueConversion() {
        assertEquals(-12.0, Length.convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES), EPS);
    }

    @Test
    void largeValueConversion() {
        double val = 1e9;
        double conv = Length.convert(val, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(val * 12.0, conv, 1e-2); // allow larger tolerance for huge values
    }

    /* ================= Validation Tests ================= */

    @Test
    void invalidUnitThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Length(1.0, null));
        assertThrows(IllegalArgumentException.class, () -> Length.convert(1.0, null, Length.LengthUnit.INCHES));
        assertThrows(IllegalArgumentException.class, () -> Length.convert(1.0, Length.LengthUnit.FEET, null));
    }

    @Test
    void nanOrInfiniteValueThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Length(Double.NaN, Length.LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () -> new Length(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () -> Length.convert(Double.NaN, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
    }

    /* ================= HashCode Contract Test ================= */

    @Test
    void hashCodeConsistencyForEquivalentQuantities() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertEquals(a.hashCode(), b.hashCode());
    }
}