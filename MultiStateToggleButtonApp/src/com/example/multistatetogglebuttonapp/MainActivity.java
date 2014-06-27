package com.example.multistatetogglebuttonapp;
import org.Mondhrez.multistatetogglebutton.IMultiStateButton;
import org.Mondhrez.multistatetogglebutton.MultiStateButton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MultiStateButton button3 = (MultiStateButton) this
				.findViewById(R.id.muti_state_btn);
		button3.setOnValueChangedListener(new IMultiStateButton.OnValueChangedListener() {

			@Override
			public void onValueChanged(int value) {
				Log.d(TAG, "Value: " + value);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
