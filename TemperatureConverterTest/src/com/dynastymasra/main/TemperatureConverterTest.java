package com.dynastymasra.main;

import junit.framework.TestCase;

import java.util.HashMap;

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class TemperatureConverterTest extends TestCase {

    private static final HashMap<Double, Double> sConversionTableDouble = new HashMap<Double, Double>();

    static {
        sConversionTableDouble.put(0.0, 32.0);
        sConversionTableDouble.put(100.0, 212.0);
        sConversionTableDouble.put(-1.0, 30.20);
        sConversionTableDouble.put(-100.0, -148.0);
        sConversionTableDouble.put(32.0, 89.60);
        sConversionTableDouble.put(-40.0, -40.0);
        sConversionTableDouble.put(-273.0, -459.40);
    }

    public TemperatureConverterTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFahrenheitToCelsius() {
        for (Double c : sConversionTableDouble.keySet()) {
            final Double f = sConversionTableDouble.get(c);
            final Double ca = TemperatureConverter.fahrenheitToCelsius(f);
            final Double delta = Math.abs(ca - c);
            assertTrue("delta=" + delta + " for c=" + c + " ca=" + ca, delta < 0.005);
        }
    }

    public void testCelsiusToFahrenheit() {
        for (double c: sConversionTableDouble.keySet()) {
            final double f = sConversionTableDouble.get(c);
            final double fa = TemperatureConverter.celsiusToFahrenheit(c);
            final double delta = Math.abs(fa - f);
            assertTrue("delta=" + delta + " for f=" + f + " fa=" + fa, delta < 0.005);
        }
    }
}
