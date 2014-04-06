package com.dynastymasra.main;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class MainActivity extends Activity {
    public static final String FAHRENHEIT_KEY = "com.dynastymasra.main.Fahrenheit";
    public static final String CELSIUS_KEY = "com.dynastymasra.main.Celsius";
    private static final String TAG = "MainActivity";
    private static final boolean DEBUG = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment {
        private static final String TAG = "PlaceholderFragment in MainActivity";
        private EditNumber mCelsius;
        private EditNumber mFahrenheit;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mCelsius = (EditNumber) rootView.findViewById(R.id.editTextCelsius);
            mFahrenheit = (EditNumber) rootView.findViewById(R.id.editTextFahrenheit);

            mCelsius.addTextChangedListener(new TemperatureChangeWatcher(mCelsius, mFahrenheit) {

                @Override
                protected Double convert(Double tmp) {
                    return TemperatureConverter.celsiusToFahrenheit(tmp);
                }
            });

            mFahrenheit.addTextChangedListener(new TemperatureChangeWatcher(mCelsius, mFahrenheit) {
                @Override
                protected Double convert(Double tmp) {
                    return TemperatureConverter.fahrenheitToCelsius(tmp);
                }
            });

            if (savedInstanceState != null) {
                if (savedInstanceState.containsKey(CELSIUS_KEY)) {
                    final Double c = savedInstanceState.getDouble(CELSIUS_KEY);
                    final Double f = TemperatureConverter.celsiusToFahrenheit(c);
                    if (DEBUG) {
                        Log.d(TAG, "onCreate: replace celsius: " + c);
                    }
                    mCelsius.setNumber(c);
                    mFahrenheit.setNumber(f);
                } else if (savedInstanceState.containsKey(FAHRENHEIT_KEY)) {
                    final Double f = savedInstanceState.getDouble(FAHRENHEIT_KEY);
                    final Double c = TemperatureConverter.fahrenheitToCelsius(f);
                    if (DEBUG) {
                        Log.d(TAG, "onCreate: replace fahrenheit: " + f);
                    }
                    mFahrenheit.setNumber(f);
                    mCelsius.setNumber(c);
                }
            }

            mCelsius.setOnFocusChangeListener(onFocusChangeListener);
            mFahrenheit.setOnFocusChangeListener(onFocusChangeListener);

			return rootView;
		}

        private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    return;
                }

                final Double f = mFahrenheit.getNumber();
                final Double c = mCelsius.getNumber();

                if (view == mCelsius && !Double.isNaN(f)) {
                    mCelsius.setNumber(TemperatureConverter.fahrenheitToCelsius(f));
                } else if (view == mFahrenheit && !Double.isNaN(c)) {
                    mFahrenheit.setNumber(TemperatureConverter.celsiusToFahrenheit(c));
                }
            }
        };
	}

    public abstract class TemperatureChangeWatcher implements TextWatcher {
        private static final String TAG = "TemperatureChangeWatcher";
        private EditNumber mSource;
        private EditNumber mDest;

        public TemperatureChangeWatcher(EditNumber mSource, EditNumber mDest) {
            this.mSource = mSource;
            this.mDest = mDest;
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (DEBUG) {
                Log.d(TAG, "onTextChanged(" + charSequence + ", " + start + ", " + before + ")");
            }
            if (mDest.hasWindowFocus() || mDest.hasFocus() || charSequence == null) {
                return;
            }
            final String str = charSequence.toString();
            if (str.equals("")) {
                mDest.setText("");
                return;
            }

            try {
                Log.v(TAG, "converting temp=" + str + "{" + Double.parseDouble(str) + "}");
                final Double result = convert(Double.parseDouble(str));
                Log.v("TemperatureChangeWatcher", "result=" + result);
                mDest.setNumber(result);
            } catch(NumberFormatException ex) {
                Log.e(TAG, ex.getMessage());
            } catch(Exception ex) {
                Log.e(TAG,  "ERROR", ex);
                mSource.setError("ERROR: " + ex.getLocalizedMessage());
            }
        }

        protected abstract Double convert(Double tmp);

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }
    }
}
