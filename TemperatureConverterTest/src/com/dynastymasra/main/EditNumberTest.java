package com.dynastymasra.main;

import android.test.AndroidTestCase;

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class EditNumberTest extends AndroidTestCase {
    private EditNumber editNumber;

    public EditNumberTest(String name) {
        setName(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        editNumber = new EditNumber(mContext);
        editNumber.setFocusable(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testEditNumberContext() {
        assertNotNull(editNumber);
    }

    public void testClear() {
        final String value = "123.45";
        editNumber.setText(value);
        editNumber.clear();
        final String expected = "";
        final String actual = editNumber.getText().toString();
        assertEquals(expected, actual);
    }

    public void testSetNumber() throws Exception {
        final Double d = 123.45;
        editNumber.setNumber(d);
        final String expected = Double.toString(d);
        final String actual = editNumber.getText().toString();
        assertEquals(expected, actual);
    }

    public void testGetNumber() throws Exception {
        final Double expected = 123.45;
        editNumber.setNumber(expected);
        final Double actual = editNumber.getNumber();
        assertEquals(expected, actual);
    }
}
