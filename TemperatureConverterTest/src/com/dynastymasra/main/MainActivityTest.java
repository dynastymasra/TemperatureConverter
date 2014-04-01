package com.dynastymasra.main;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.Gravity;
import android.view.View;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.dynastymasra.main.MainActivityTest \
 * com.dynastymasra.main.tests/android.test.InstrumentationTestRunner
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    private EditNumber editTextCelsius;
    private EditNumber editTextFahrenheit;

    public MainActivityTest(String name) {
        super(MainActivity.class);
        setName(name);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        assertNotNull(mainActivity);

        editTextCelsius = (EditNumber) mainActivity.findViewById(com.dynastymasra.main.R.id.editTextCelsius);
        assertNotNull(editTextCelsius);
        editTextFahrenheit = (EditNumber) mainActivity.findViewById(com.dynastymasra.main.R.id.editTextFahrenheit);
        assertNotNull(editTextFahrenheit);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testFieldOnScreen() {
        final View origin = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(origin, editTextCelsius);
        ViewAsserts.assertOnScreen(origin, editTextFahrenheit);
    }

    @SmallTest
    public void testAlignment() {
        ViewAsserts.assertRightAligned(editTextCelsius, editTextFahrenheit);
        ViewAsserts.assertLeftAligned(editTextCelsius, editTextFahrenheit);
    }

    @SmallTest
    public void testFieldStartMustEmpty() {
        assertTrue(editTextCelsius.getText().toString().equals(""));
        assertTrue(editTextFahrenheit.getText().toString().equals(""));
    }

    @SmallTest
    public void testJustification() {
        final int expected = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        assertEquals(expected, editTextCelsius.getGravity());
        assertEquals(expected, editTextFahrenheit.getGravity());
    }

    @UiThreadTest
    public void testFahrenheitToCelsiusConversion() {
        editTextCelsius.clear();
        editTextFahrenheit.clear();
        final Double f = 32.5;
        editTextFahrenheit.requestFocus();
        editTextFahrenheit.setNumber(f);
        editTextCelsius.requestFocus();
        final Double expected = TemperatureConverter.fahrenheitToCelsius(f);
        final Double actual = editTextCelsius.getNumber();
        final Double delta = Math.abs(expected - actual);
        assertTrue("delta=" + delta, delta < 0.005);
    }

    @UiThreadTest
    public void testCelsiusToFahrenheitConversion() {
        editTextCelsius.clear();
        editTextFahrenheit.clear();
        final double c = 32.5;
        editTextCelsius.requestFocus();
        editTextCelsius.setNumber(c);
        editTextFahrenheit.requestFocus();
        final double expected = TemperatureConverter.celsiusToFahrenheit(c);
        final double actual = editTextFahrenheit.getNumber();
        final double delta = Math.abs(expected - actual);
        assertTrue("delta=" + delta, delta < 0.005);
    }
}
