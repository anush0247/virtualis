package com.aakash.vlabs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ExpandExp extends Activity {

	String 	class_no = "9",
			subject = "physics",
			exp_name = "Chemical Reaction",
			exp_no = "2",exp_icon = "http://aakashlabs.org/static/images/favicon.ico",
			TheoryUrl = "http://www.cse.iitb.ac.in/~aneesh14/html/theory.html",
			ExpDesc = "my first Experiment",
			ProcedureUrl = "http://www.cse.iitb.ac.in/~aneesh14/html/procedure.html",
			ResourceUrl = "http://www.rgukt.in/pdftnp/Advertisement_ONGC.pdf",
			SimulatinUrl = "http://www.cse.iitb.ac.in/~aneesh14/html/simulation.html",
			QuizUrl = "http://www.cse.iitb.ac.in/~aneesh14/html/procedure.html",
			VideoUrls = "http://www.cse.iitb.ac.in/~aneesh14/html/video1.mp4,http://www.cse.iitb.ac.in/~aneesh14/html/video2.mp4," ;

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
				intent.putExtra("exp_icon", exp_icon);
				intent.putExtra("view_mode", "offline");
				intent.putExtra("saved_status", "yes");
				
				intent.putExtra("theory_url",TheoryUrl);
				intent.putExtra("exp_desc",ExpDesc);
				intent.putExtra("procedure_url",ProcedureUrl );
				intent.putExtra("simulation_url", SimulatinUrl);
				intent.putExtra("quiz_url", QuizUrl);
				intent.putExtra("resource_url", ResourceUrl);
				intent.putExtra("video_urls",VideoUrls);
				startActivity(intent);
			}
		});
	}
	
	
}
