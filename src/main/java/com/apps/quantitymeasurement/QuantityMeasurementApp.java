package com.apps.quantitymeasurement;

public class QuantityMeasurementApp{

	/* ===================== FEET CLASS ===================== */
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            // 1. Reference check
            if (this == obj) {
            	return true;
            }

            // 2. Null & type check
            if (obj == null || getClass() != obj.getClass()) {
            	return false;
            }

            // 3. Cast and compare values
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }
    
    /* ===================== INCHES CLASS (NEW - UC2) ===================== */

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            // 1. Reference check
            if (this == obj) {
                return true;
            }

            // 2. Null & type check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // 3. Cast and compare values
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }
    
    
    /* ===================== STATIC METHODS ===================== */
    
    // Feet Equality
    public static boolean checkFeetEquality(double val1, double val2) {
        Feet f1 = new Feet(val1);
        Feet f2 = new Feet(val2);
        return f1.equals(f2);
    }

    // Inches Equality
    public static boolean checkInchEquality(double val1, double val2) {
        Inches i1 = new Inches(val1);
        Inches i2 = new Inches(val2);
        return i1.equals(i2);
    }
    
    /* ===================== MAIN METHOD ===================== */

    public static void main(String[] args) {

        System.out.println("Feet Equality: " +
                checkFeetEquality(1.0, 1.0));

        System.out.println("Inch Equality: " +
                checkInchEquality(1.0, 1.0));
    }

 
}