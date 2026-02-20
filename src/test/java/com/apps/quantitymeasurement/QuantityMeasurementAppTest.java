package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

	 /* ==============================
    FEET TEST CASES (UC1)
    ============================== */
    @Test
    void testFeetEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
    }

    @Test
    void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    void testFeetEquality_NullComparison() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null), "Feet object should not be equal to null");
    }

    @Test
    void testFeetEquality_DifferentClass() {
        Feet f1 = new Feet(1.0);
        String other = "1.0";

        assertFalse(f1.equals(other), "Feet object should not be equal to object of different class");
    }

    @Test
    void testFeetEquality_SameReference() {
        Feet f1 = new Feet(1.0);

        assertTrue(f1.equals(f1), "Object should be equal to itself (reflexive property)");
    }
    
    /* ==============================
    INCHES TEST CASES (UC2)
    ============================== */

    @Test
    void testInchesEquality_SameValue() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(12.0);

        assertTrue(i1.equals(i2), "12 in should be equal to 12 in");
    }

    @Test
    void testInchesEquality_DifferentValue() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(10.0);

        assertFalse(i1.equals(i2), "12 in should not be equal to 10 in");
    }

    @Test
    void testInchesEquality_NullComparison() {
        Inches i1 = new Inches(12.0);

        assertFalse(i1.equals(null),
                "Inches object should not be equal to null");
    }

    @Test
    void testInchesEquality_DifferentClass() {
        Inches i1 = new Inches(12.0);
        Feet feet = new Feet(1.0);

        assertFalse(i1.equals(feet),
                "Inches object should not be equal to Feet object");
    }

    @Test
    void testInchesEquality_SameReference() {
        Inches i1 = new Inches(12.0);

        assertTrue(i1.equals(i1),
                "Object should be equal to itself");
    }
}