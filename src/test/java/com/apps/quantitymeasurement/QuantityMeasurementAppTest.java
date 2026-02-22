package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(i1, i2));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(feet, inches));
        // Symmetry: reversed order should also be true
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(inches, feet));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(i1, i2));
    }

    @Test
    void testEquality_NullComparison() {
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l.equals(null));
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(null, l));
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l, null));
    }

    @Test
    void testEquality_SameReference() {
        Length l = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue(l.equals(l));
    }

    @Test
    void testCrossUnitInequality() {
        // 1 foot vs 11 inches -> not equal
        Length f = new Length(1.0, Length.LengthUnit.FEET);
        Length i = new Length(11.0, Length.LengthUnit.INCHES);
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(f, i));
    }

    @Test
    void testEquality_InvalidUnit_throwsException() {
        // constructor rejects null unit -> IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Length(1.0, null));
    }

    @Test
    void testHashCode_consistencyForEquivalentQuantities() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertEquals(a.hashCode(), b.hashCode());
    }
}