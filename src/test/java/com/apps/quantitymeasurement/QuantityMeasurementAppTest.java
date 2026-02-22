package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    /* ================= YARD TESTS ================= */

    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(1.0, Length.LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertFalse(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(2.0, Length.LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    /* ================= CENTIMETER TESTS ================= */

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        assertTrue(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, Length.LengthUnit.INCHES)));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        assertFalse(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    /* ================= TRANSITIVE PROPERTY ================= */

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    /* ================= REFLEXIVE ================= */

    @Test
    void testEquality_SameReference() {
        Length yard = new Length(2.0, Length.LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    /* ================= NULL CHECK ================= */

    @Test
    void testEquality_NullComparison() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    /* ================= COMPLEX SCENARIO ================= */

    @Test
    void testEquality_AllUnits_ComplexScenario() {

        Length twoYards = new Length(2.0, Length.LengthUnit.YARDS);
        Length sixFeet = new Length(6.0, Length.LengthUnit.FEET);
        Length seventyTwoInches = new Length(72.0, Length.LengthUnit.INCHES);

        assertTrue(twoYards.equals(sixFeet));
        assertTrue(sixFeet.equals(seventyTwoInches));
        assertTrue(twoYards.equals(seventyTwoInches));
    }
}