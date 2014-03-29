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
    private EditNumber editNumberCelsius;
    private EditNumber editNumberFahrenheit;

    public MainActivityTest(String name) {
        super(MainActivity.class);
        setName(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        assertNotNull(mainActivity);

        editNumberCelsius = (EditNumber) mainActivity.findViewById(R.id.editTextCelsius);
        assertNotNull(editNumberCelsius);
        editNumberFahrenheit = (EditNumber) mainActivity.findViewById(R.id.editTextFahrenheit);
        assertNotNull(editNumberFahrenheit);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testFieldOnScreen() {
        final View origin = mainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(origin, editNumberCelsius);
        ViewAsserts.assertOnScreen(origin, editNumberFahrenheit);
    }

    @SmallTest
    public void testAlignment() {
        ViewAsserts.assertRightAligned(editNumberCelsius, editNumberFahrenheit);
        ViewAsserts.assertLeftAligned(editNumberCelsius, editNumberFahrenheit);
    }

    @SmallTest
    public void testFieldStartEmpty() {
        assertTrue(editNumberCelsius.getText().toString().equals(""));
        assertTrue(editNumberFahrenheit.getText().toString().equals(""));
    }

    @SmallTest
    public void testJustification() {
        final int expected = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

        assertEquals(expected, editNumberCelsius.getGravity());
        assertEquals(expected, editNumberFahrenheit.getGravity());
    }

    @UiThreadTest
    public void testFahrenheitToCelsiusConvertion() {
        editNumberCelsius.clear();
        editNumberFahrenheit.clear();
        final Double f = 32.5;
        editNumberFahrenheit.requestFocus();
        editNumberFahrenheit.setNumber(f);
        editNumberCelsius.requestFocus();
        final Double expected = TemperatureConverter.fahrenheitToCelsius(f);
        final Double actual = editNumberCelsius.getNumber();
        final Double delta = Math.abs(expected - actual);
        assertTrue("delta=" + delta, delta < 0.005);
    }

    @UiThreadTest
    public void testCelsiusToFahrenheit() {
        editNumberCelsius.clear();
        editNumberFahrenheit.clear();
        final Double c = 32.5;
        editNumberCelsius.requestFocus();
        editNumberCelsius.setNumber(c);
        editNumberFahrenheit.requestFocus();
        final Double expected = TemperatureConverter.celsiusToFahrenheit(c);
        final Double actual = editNumberFahrenheit.getNumber();
        final Double delta = Math.abs(expected - actual);
        assertTrue("delta=" + delta, delta < 0.005);
    }
}
