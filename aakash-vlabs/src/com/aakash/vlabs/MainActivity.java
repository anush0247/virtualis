package com.aakash.vlabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity{
	
	JSONArray json = null;
	private static String url = "http://www.cse.iitb.ac.in/~aneesh14/file2.json";
	
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
				moveOnline();
			}
		});
        
	}
	
	public void parseOnline() {
		
		JSONParser jParser = new JSONParser(); 
		
		// get JSON data from URL 
		json = jParser.getJSONFromUrl(url); 
		
		for (int i = 0; i < json.length(); i++) { 
			
			try {
				JSONObject subList = json.getJSONObject(i);
				JSONdata.setSubject(subList.getString("subject_name"));
				
				for(int j = 0; j < subList.getJSONArray("exps").length(); j++) {
					
					JSONObject subExp = subList.getJSONArray("exps").getJSONObject(j);
					JSONdata.setExperimentHead(i,subExp.getString("name"));
					JSONdata.setExperimentDesc(i,subExp.getString("descritption"));
					
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
