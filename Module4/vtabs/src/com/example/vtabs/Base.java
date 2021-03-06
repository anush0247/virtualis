package com.example.vtabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Base extends Activity{
	
	String class_no = "9";
	String subject = "Physics";
	String exp_name = "Chemical Reaction";
	String exp_no = "2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		
		Button exp_button = (Button) findViewById(R.id.exp_button);
		exp_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Base.this, Main.class);
				
				intent.putExtra("class_no", class_no);
				intent.putExtra("subject", subject);
				intent.putExtra("exp_name", exp_name);
				intent.putExtra("exp_no", exp_no);
				startActivity(intent);
			}
		});
	}

	
}
