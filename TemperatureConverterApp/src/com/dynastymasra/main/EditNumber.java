package com.dynastymasra.main;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class EditNumber extends EditText {
    private static final String TAG = "EditNumber";

    public EditNumber(Context context) {
        super(context);
    }

    public EditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void clear() {
        setText("");
    }

    public void setNumber(Double f) {
        Log.d(TAG, "setNumber setting f=" + f + " => " + Double.toString(f));
        setText(Double.toString(f));
    }

    public Double getNumber() {
        final String s= getText().toString();
        Log.d(TAG, "getNumber converting " + s);
        if (s.equals("")) {
            return Double.NaN;
        }

        return Double.valueOf(s);
    }
}
