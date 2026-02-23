package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // =====================================
    // LENGTH TESTS (Generic Quantity)
    // =====================================

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

    // =====================================
    // WEIGHT TESTS (Generic Quantity)
    // =====================================

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

    // =====================================
    // CROSS CATEGORY SAFETY
    // =====================================

    @Test
    void testLengthAndWeightNotEqual() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // =====================================
    // EQUALITY CONTRACT TESTS
    // =====================================

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

    // =====================================
    // VALIDATION TESTS
    // =====================================

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
    
 // =====================================
 // VOLUME TESTS (Generic Quantity) - UC11
 // =====================================

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
}