package com.aakash.vlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ExpandExp extends Activity {

	String class_no = "9";
	String subject = "Physics";
	String exp_name = "Chemical Reaction";
	String exp_no = "2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.expand_exp);
		
		Button exp_button = (Button) findViewById(R.id.exp_button);
		exp_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ExpandExp.this, ShowExp.class);
				
				intent.putExtra("class_no", class_no);
				intent.putExtra("subject", subject);
				intent.putExtra("exp_name", exp_name);
				intent.putExtra("exp_no", exp_no);
				startActivity(intent);
			}
		});
	}
}
