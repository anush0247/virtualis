package com.aakash.vlabs.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuizPreStart extends Activity {

	Button quiz_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_pre_start);
		
		quiz_btn = (Button) findViewById(R.id.star_quiz);
		quiz_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent quiz_start= new Intent(getApplicationContext(),
						QuizStart.class);
				startActivity(quiz_start);
			}
		});
	}
}
