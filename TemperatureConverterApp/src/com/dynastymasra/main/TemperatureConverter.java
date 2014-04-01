package com.dynastymasra.main;

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class TemperatureConverter {

    public static Double fahrenheitToCelsius(Double f) {
        return (f-32) * 5/9.0;
    }

    public static Double celsiusToFahrenheit(Double c) {
        return 9/5.0 * c + 32;
    }

}
