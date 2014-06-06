package com.aakash.vlabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	JSONArray json = null;
	private static String url = "http://www.cse.iitb.ac.in/~aneesh14/file2.json";
	JSONObject classSubList;
	JSONArray json1 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        setContentView(R.layout.main_activity);
        
        Button onlineButton = (Button) findViewById(R.id.online);
        
        onlineButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				parseOnline();
				
			}
		});
        
	}
	
	public void parseOnline() {
		
		JSONParser jParser = new JSONParser(); 
		
		// get JSON data from URL 
		json = jParser.getJSONFromUrl(url);
		
		if(json == null) {
			Context context = getApplicationContext();
			CharSequence text = "No Internet Connection";
			int duration = Toast.LENGTH_SHORT;

			Toast.makeText(context, text, duration).show();
			
		} else {
		
		try {
			classSubList = json.getJSONObject(0);
			JSONdata.setStudentClass(classSubList.getString("class_no"));
			json1 = classSubList.getJSONArray("subject");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		JSONdata.Subjects.clear();
		JSONdata.ExperimentsHead.clear();
		JSONdata.ExperimentsDesc.clear();
		JSONdata.SubExpHead.clear();
		JSONdata.SubExpDesc.clear();
		JSONdata.ExperimentNo = -1;
		JSONdata.subjectNo = -1;
		
		for (int i = 0; i < json1.length(); i++) { 
			
			try {
				JSONObject subList = json1.getJSONObject(i);
				JSONdata.setSubject(subList.getString("subject_name"));
				
				for(int j = 0; j < subList.getJSONArray("exps").length(); j++) {
					
					JSONObject subExp = subList.getJSONArray("exps").getJSONObject(j);
					JSONdata.setExperimentHead(i,subExp.getString("name"));
					JSONdata.setExperimentDesc(i,subExp.getString("description"));
					JSONdata.setExperimentNum(i,subExp.getString("exp_no"));
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		moveOnline();
		}
		
	}
	
	public void moveOnline() {
		Class ourClass;
		try {
			ourClass = Class.forName("com.aakash.vlabs.ChooseExperiment");
			Intent intent = new Intent(MainActivity.this,ourClass);
	        startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
