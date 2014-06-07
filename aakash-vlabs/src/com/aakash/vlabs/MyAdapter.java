package com.aakash.vlabs;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends ArrayAdapter<String>{
	
	private Context context;
	private ArrayList<String> values;
	static public int j = -1;
	int SubPosition = 0;
	JSONArray json = null;
	private JSONObject thisExp;
	private String dataSend = "";
	private static String url = "http://www.cse.iitb.ac.in/~aneesh14/json/subject_exp.json";
	private JSONArray vidList = null;
	private JSONObject vid;
	private String allVideos = "";
	
	private String ExpTheory;
	private String ExpProcedure;
	private String ExpSimulation;
	private String ExpQuiz;
	private String ExpResource;
	
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
	    textView1.setText(values.get(position));

	    textView2.setText(JSONdata.ExperimentsDesc.get(j).get(position));
	    
	    imageView.setImageResource(R.drawable.ic_launcher);
	    
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
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
					//Intent intent=new Intent(parent.getContext(), MainActivity.class);   
					dataSend = "";
					//intent.putExtra("class_no", JSONdata.StudentClass);
					dataSend += "class_no: " + JSONdata.StudentClass + "\n";
					//intent.putExtra("subject", JSONdata.Subjects.get(SubPosition));
					dataSend += "subject: " + JSONdata.Subjects.get(SubPosition) + "\n";
					//intent.putExtra("exp_name", values.get(position));
					dataSend += "exp_name: " + values.get(position) + "\n";
					//intent.putExtra("exp_no", JSONdata.ExperimentsNum.get(j).get(position));
					dataSend += "exp_no: " + JSONdata.ExperimentsNum.get(j).get(position) + "\n";
					//intent.putExtra("theory", thisExp.getString("theory"));
					dataSend += "theory: " + ExpTheory + "\n";
					//intent.putExtra("procedure", thisExp.getString("procedure"));
					dataSend += "procedure: " + ExpProcedure + "\n";
					//intent.putExtra("simulation", thisExp.getString("simulation"));
					dataSend += "simulation: " + ExpSimulation + "\n";
					//intent.putExtra("quiz", thisExp.getString("quiz"));
					dataSend += "quiz: " + ExpQuiz + "\n";
					//intent.putExtra("resource", thisExp.getString("resource"));
					dataSend += "resource: " + ExpResource + "\n";
					//intent.putExtra("video", allVideos);
					dataSend += "video: " + allVideos;
					
					Toast.makeText(parent.getContext(), dataSend, Toast.LENGTH_SHORT).show();
				
					//parent.getContext().startActivity(intent);
				} else {
					
					Toast.makeText(parent.getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	    
	    return rowView;
	  }
	
}
