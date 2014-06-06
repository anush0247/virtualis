package com.example.flipper1;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	
	String args[] = {"One","Two","Three","Four"};
	public ViewFlipper flipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		for(String item:args){
			Button btn = new Button(MainActivity.this);
			btn.setText(item);
			flipper.addView(btn, new ViewGroup.LayoutParams(200,200));
		}
		flipper.setFlipInterval(2000);
		flipper.startFlipping();
	}
	


}
