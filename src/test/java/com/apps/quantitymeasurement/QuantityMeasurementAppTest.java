package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        Length result = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(2.0, Length.LengthUnit.FEET));

        assertTrue(result.equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Length result = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(12.0, Length.LengthUnit.INCHES));

        assertTrue(result.equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    void testAddition_CrossUnit_InchesPlusFeet() {
        Length result = new Length(12.0, Length.LengthUnit.INCHES)
                .add(new Length(1.0, Length.LengthUnit.FEET));

        assertTrue(result.equals(new Length(24.0, Length.LengthUnit.INCHES)));
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        Length result = new Length(1.0, Length.LengthUnit.YARDS)
                .add(new Length(3.0, Length.LengthUnit.FEET));

        assertTrue(result.equals(new Length(2.0, Length.LengthUnit.YARDS)));
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        Length result = new Length(2.54, Length.LengthUnit.CENTIMETERS)
                .add(new Length(1.0, Length.LengthUnit.INCHES));

        assertTrue(result.equals(new Length(5.08, Length.LengthUnit.CENTIMETERS)));
    }

    @Test
    void testAddition_WithZero() {
        Length result = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(0.0, Length.LengthUnit.INCHES));

        assertTrue(result.equals(new Length(5.0, Length.LengthUnit.FEET)));
    }

    @Test
    void testAddition_NegativeValues() {
        Length result = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(-2.0, Length.LengthUnit.FEET));

        assertTrue(result.equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    void testAddition_Commutativity() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = a.add(b);
        Length result2 = b.add(a);

        assertTrue(result1.equals(result2));
    }
}