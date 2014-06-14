package com.aakash.vlabs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends ArrayAdapter<String>{
	
	private String storage_status;
	private Context context;
	private ArrayList<String> values;
	static public int j = -1;
	int SubPosition = 0;
	JSONArray json = null;
	private JSONObject thisExp;
	private String dataSend = "";
	private static String url = "http://www.cse.iitb.ac.in/~aneesh14/json/subject_exp.json";
	private static String url2;
	private JSONArray vidList = null;
	private JSONObject vid;
	private String allVideos = "";
	
	private String ExpTheory;
	private String ExpProcedure;
	private String ExpSimulation;
	private String ExpQuiz;
	private String ExpResource;
	private String ExpDescription;
	
	public MyAdapter(Context context, ArrayList<String> arr, int mCurrentPosition) {
	    super(context, R.layout.custom_list, arr);
	    this.context = context;
	    this.values = arr;
	    this.SubPosition = mCurrentPosition;
	  }
	
	@Override
	  public View getView(final int position, View convertView, final ViewGroup parent) {
	    
		View rowView = convertView;
		//LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    //View rowView = inflater.inflate(R.layout.custom_list, parent, false);
	    
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.custom_list,parent, false);
        }
		
		TextView textView1 = (TextView) rowView.findViewById(R.id.headin);
		TextView textView2 = (TextView) rowView.findViewById(R.id.desc);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    Button offline = (Button) rowView.findViewById(R.id.offline);
	    textView1.setText(values.get(position));

	    offline.setVisibility(View.INVISIBLE);
	    textView2.setText(JSONdata.ExperimentsDesc.get(j).get(position));
	    
	    ///
	    
	    URL myUrl;
	    InputStream inputStream = null;
		try {
			myUrl = new URL(JSONdata.ExperimentsThumb.get(j).get(position));
			inputStream = (InputStream)myUrl.getContent();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    Drawable drawable = Drawable.createFromStream(inputStream, null);
	    imageView.setImageDrawable(drawable);
	    
	    //imageView.setImageResource(R.drawable.ic_launcher);
	    
	    ///
	    
	    final File extStore = Environment.getExternalStorageDirectory();
	    url2 = "/Android/data/com.aakash.vlabs/ExPdaTA/"+JSONdata.StudentClass+"/"+JSONdata.Subjects.get(SubPosition)+"/"+JSONdata.ExperimentsNum.get(j).get(position)+"/expData.json";
	    File myFile = new File(extStore.getAbsolutePath() + url2);
	    //Toast.makeText(parent.getContext(), url2 , Toast.LENGTH_SHORT).show();
	    
	    if(myFile.exists()) {
	    	storage_status = "yes";
	        offline.setVisibility(View.VISIBLE);
	        
	        offline.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String exp_thumb = "";
					String exp_simulation = "";
					String exp_video = "";
					String exp_resource = "";
					String exp_theory = "";
					String exp_quiz = "";
					String exp_procedure = "";
					String jString = null;
					try {

			            //File dir = Environment.getExternalStorageDirectory();
			            File yourFile = new File(extStore, url2);
			            FileInputStream stream = new FileInputStream(yourFile);
			            
			            try {
			                FileChannel fc = stream.getChannel();
			                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			                
			                jString = Charset.defaultCharset().decode(bb).toString();
			              }
			              finally {
			                stream.close();
			              }


			              thisExp = new JSONObject(jString); 
			              


			        } catch (Exception e) {e.printStackTrace();}
					
					if(thisExp == null)
						Toast.makeText(parent.getContext(), jString, Toast.LENGTH_SHORT).show();
					
					//JSONParser jParser = new JSONParser();
					//json = jParser.getJSONFromUrl("file:///mnt/sdcard"+url2);
					/*
					try {
						thisExp = json.getJSONObject(0);
						exp_thumb = thisExp.getString("thumb");
						exp_theory = thisExp.getString("theory");
						exp_simulation = thisExp.getString("simulation");
						exp_resource = thisExp.getString("resources");
						exp_video = thisExp.getString("videos");
						exp_quiz = thisExp.getString("gift");
						exp_procedure = thisExp.getString("procedure");
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Intent intent=new Intent(parent.getContext(), ShowExp.class);   
					
					intent.putExtra("saved_status", storage_status);
					intent.putExtra("view_mode", "offline");
					intent.putExtra("class_no", JSONdata.StudentClass);
					intent.putExtra("subject", JSONdata.Subjects.get(SubPosition));
					intent.putExtra("exp_name", values.get(position));
					intent.putExtra("exp_no", JSONdata.ExperimentsNum.get(j).get(position));
					intent.putExtra("exp_desc", JSONdata.ExperimentsDesc.get(j).get(position));
					intent.putExtra("exp_icon", exp_thumb);
					intent.putExtra("theory_url", exp_theory);
					intent.putExtra("simulation_url", exp_simulation);
					intent.putExtra("resource_url", exp_resource);
					intent.putExtra("video_urls", exp_video);
					intent.putExtra("quiz_url", exp_quiz);
					intent.putExtra("procedure_url", exp_procedure);
					*/
					//Toast.makeText(parent.getContext(), "Switched to OFFLINE", Toast.LENGTH_SHORT).show();
					//parent.getContext().startActivity(intent);
				}
			});
	        
	    } else {
	    	storage_status = "no";
	    }
	    
	    rowView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONParser jParser = new JSONParser(); 
				json = jParser.getJSONFromUrl(url);
				
				allVideos = "";
				
				if(json != null) {
				
					try {
						thisExp = json.getJSONObject(0);
						vidList = thisExp.getJSONArray("video");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					for (int i = 0; i < vidList.length(); i++) {
						
						try {
							vid = vidList.getJSONObject(i);
							allVideos += vid.getString("vid") + ",";
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					try {
						ExpTheory = thisExp.getString("theory");
						ExpProcedure = thisExp.getString("procedure");
						ExpSimulation = thisExp.getString("simulation");
						ExpQuiz = thisExp.getString("quiz");
						ExpResource = thisExp.getString("resource");
						ExpDescription = JSONdata.ExperimentsDesc.get(j).get(position); 
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
					Intent intent=new Intent(parent.getContext(), ShowExp.class);   
					dataSend = "";
					intent.putExtra("saved_status", storage_status);
					intent.putExtra("view_mode", "online");
					intent.putExtra("class_no", JSONdata.StudentClass);
					dataSend += "class_no: " + JSONdata.StudentClass + "\n";
					intent.putExtra("subject", JSONdata.Subjects.get(SubPosition));
					dataSend += "subject: " + JSONdata.Subjects.get(SubPosition) + "\n";
					intent.putExtra("exp_name", values.get(position));
					dataSend += "exp_name: " + values.get(position) + "\n";
					intent.putExtra("exp_no", JSONdata.ExperimentsNum.get(j).get(position));
					dataSend += "exp_no: " + JSONdata.ExperimentsNum.get(j).get(position) + "\n";
					intent.putExtra("theory_url", ExpTheory);
					dataSend += "theory: " + ExpTheory + "\n";
					intent.putExtra("procedure_url", ExpProcedure);
					dataSend += "procedure: " + ExpProcedure  + "\n";
					intent.putExtra("exp_desc", JSONdata.ExperimentsDesc.get(j).get(position));
					dataSend += "description: " + ExpDescription + "\n";
					intent.putExtra("exp_icon", JSONdata.ExperimentsThumb.get(j).get(position));
					dataSend += "thumb: " + JSONdata.ExperimentsThumb.get(j).get(position) + "\n";
					intent.putExtra("simulation_url", ExpSimulation);
					dataSend += "simulation: " + ExpSimulation + "\n";
					intent.putExtra("quiz_url", ExpQuiz);
					dataSend += "quiz: " + ExpQuiz + "\n";
					intent.putExtra("resource_url", ExpResource);
					dataSend += "resource: " + ExpResource + "\n";
					intent.putExtra("video_urls", allVideos);
					dataSend += "video: " + allVideos;
					
					Toast.makeText(parent.getContext(), dataSend, Toast.LENGTH_SHORT).show();
				
					parent.getContext().startActivity(intent);
					//START ACTIVITY
					
				} else {
					
					Toast.makeText(parent.getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	    
	    return rowView;
	  }
	
}
