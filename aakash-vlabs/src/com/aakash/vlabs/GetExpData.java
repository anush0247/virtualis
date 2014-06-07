package com.aakash.vlabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class GetExpData {
	
	private String SubjectName,ClassNo,ExpNo,ExpName,ExpDesc,
			TheoryUrl,ProcedureUrl,ResourceUrl,SimulatinUrl,QuizUrl;
	private String[] VideoUrls = null;
	private static String url = "http://www.cse.iitb.ac.in/~aneesh14/json/subject_exp.json";
	private JSONArray json = null;
	private boolean Data = true;
	
	public GetExpData(String subject, String class_no, String exp_no){
		this.SubjectName = subject;
		this.ClassNo = class_no;
		this.ExpNo = exp_no;
		try {
			GetJson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getData() {
		return this.Data;
	}
	
	public String getSubjectName(){
		return this.SubjectName;
	}
	
	public String getClassNo(){
		return this.ClassNo;
	}
	
	public String getExpNo(){
		return this.ExpNo;
	}
	
	public String getExpName(){
		return this.ExpName;
	}
	
	public String getExpDesc(){
		return this.ExpDesc;
	}
	
	public String getTheoryUrl(){
		return this.TheoryUrl;
	}
	
	public String getProcedureUrl(){
		return this.ProcedureUrl;
	}
	
	public String getSimulationUrl(){
		return this.SimulatinUrl;
	}
	
	public String getQuizUrl(){
		return this.ExpName;
	}
	
	public String getResourceUrl(){
		return this.ResourceUrl;
	}
	
	public String[] getVideoUrls(){
		return this.VideoUrls;
	}
	
	public void GetJson() throws Exception{
		JSONParser jParser = new JSONParser(); 
		
		json = jParser.getJSONFromUrl(url);
		
		if(json == null) {
			this.Data = false;
			Log.d("I am json", "what the hell");
			throw new Exception();
		}
		else {
			Log.d("i am not a empty json"," i am here ");
			for (int i = 0; i < json.length(); i++) { 
				try {
					JSONObject dataList = json.getJSONObject(i);
					this.TheoryUrl = dataList.getString("theory");
					this.ProcedureUrl = dataList.getString("procedure");
					this.ResourceUrl = dataList.getString("resource");
					this.QuizUrl = dataList.getString("quiz");
					this.SimulatinUrl = dataList.getString("simulation");
					
					String[] tmp = new String[dataList.getJSONArray("video").length()];
					for(int j=0;j<dataList.getJSONArray("video").length();j++){
						JSONObject dataList_video = dataList.getJSONArray("video").getJSONObject(j);
						tmp[j] = dataList_video.getString("vid");
					}
					this.VideoUrls = tmp;
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
	}
}
