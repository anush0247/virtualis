package com.aakash.vlabs;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
        Button offlineButton = (Button) findViewById(R.id.offline);
        
        onlineButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				parseOnline();
				
			}
		});
        
        offlineButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				parseOffline();
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
		JSONdata.ExperimentsThumb.clear();
		JSONdata.SubExpThumb.clear();
		JSONdata.SubExpHead.clear();
		JSONdata.SubExpDesc.clear();
		JSONdata.ExperimentNo = -1;
		JSONdata.subjectNo = -1;
		JSONdata.fullOffline = 0;
		
		for (int i = 0; i < json1.length(); i++) { 
			
			try {
				JSONObject subList = json1.getJSONObject(i);
				JSONdata.setSubject(subList.getString("subject_name"));
				
				for(int j = 0; j < subList.getJSONArray("exps").length(); j++) {
					
					JSONObject subExp = subList.getJSONArray("exps").getJSONObject(j);
					JSONdata.setExperimentHead(i,subExp.getString("name"));
					JSONdata.setExperimentDesc(i,subExp.getString("description"));
					JSONdata.setExperimentNum(i,subExp.getString("exp_no"));
					JSONdata.setExperimentThumb(i,subExp.getString("thumb"));
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		moveOnline();
		}
		
	}
	
	public void parseOffline() {
		
		JSONdata.Subjects.clear();
		JSONdata.ExperimentsHead.clear();
		JSONdata.ExperimentsDesc.clear();
		JSONdata.ExperimentsThumb.clear();
		JSONdata.SubExpThumb.clear();
		JSONdata.SubExpHead.clear();
		JSONdata.SubExpDesc.clear();
		JSONdata.ExperimentNo = -1;
		JSONdata.subjectNo = -1;
		JSONdata.fullOffline = 0;
		
		JSONdata.fullOffline = 1;
		JSONdata.setStudentClass("9");
		String pathAppend = "/Android/data/com.aakash.vlabs/ExPdaTA/" + JSONdata.StudentClass + "/";
		String pathAppend1 = "";
		String pathAppend2 = "";
		File extStore = Environment.getExternalStorageDirectory();
		File myFile1 = new File(extStore.getAbsolutePath() + pathAppend);
		
		File list1[] = myFile1.listFiles();
		
		for(int i=0; i<list1.length; i++) {
			
			JSONdata.setSubject(list1[i].getName());
			pathAppend1 = JSONdata.Subjects.get(i) + "/";
			File myFile2 = new File(extStore.getAbsolutePath() + pathAppend + pathAppend1);
			File list2[] = myFile2.listFiles();
			
			for(int j=0; j<list2.length; j++) {
				
				JSONdata.setExperimentNum(i,list2[j].getName());
				pathAppend2 = list2[j].getName() + "/expData.json";
				
				/*JSONParser jParser = new JSONParser(); 
				json = jParser.getJSONFromUrl(extStore.getAbsolutePath() + pathAppend + pathAppend1 + pathAppend2);
				
				JSONObject subExp;
				try {
					subExp = json.getJSONObject(j);
					JSONdata.setExperimentHead(i,subExp.getString("exp_name"));
					JSONdata.setExperimentDesc(i,subExp.getString("exp_desc"));
					JSONdata.setExperimentThumb(i,subExp.getString("thumb"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				String exp_thumb = "";
				String exp_simulation = "";
				String exp_video = "";
				String exp_resource = "";
				String exp_theory = "";
				String exp_quiz = "";
				String exp_procedure = "";
				
				String exp_class = "";
				String exp_subject = "";
				String exp_name = "";
				String exp_no = "";
				String exp_desc = "";
				String jString = null;
				
				try {

		            //File dir = Environment.getExternalStorageDirectory();
		            File yourFile = new File(extStore, pathAppend + pathAppend1 + pathAppend2);
		            FileInputStream stream = new FileInputStream(yourFile);
		            
		            try {
		                FileChannel fc = stream.getChannel();
		                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		                
		                jString = Charset.defaultCharset().decode(bb).toString();
		              }
		              finally {
		                stream.close();
		              }

		        } catch (Exception e) {e.printStackTrace();}
				
				int var = 0;
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_class += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_subject += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_name += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_no += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_desc += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_thumb += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_simulation += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_resource += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_video += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_theory += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!=',';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_procedure += jString.charAt(var);
				var+=2;
				
				for(;jString.charAt(var)!='}';var++);var-=2;
				for(;jString.charAt(var)!='"';var--);var++;
				for(;jString.charAt(var)!='"';var++)
					exp_quiz += jString.charAt(var);
				
				JSONdata.setExperimentHead(i,exp_name);
				JSONdata.setExperimentDesc(i,exp_desc);
				JSONdata.setExperimentThumb(i,exp_thumb);
				
			}
			
			
		}
		
		moveOffline();
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
	
	public void moveOffline() {
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
