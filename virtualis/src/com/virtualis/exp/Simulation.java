package com.virtualis.exp;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.virtualis.R;

public class Simulation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.one_simulation);
		
		TextView mytext = (TextView) findViewById(R.id.simulation_title);
		mytext.setText("Experiment Simulations");
		mytext.setPadding(10, 10, 10, 10);

	}
}