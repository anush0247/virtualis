package com.aakash.vlabs.quiz;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Question extends Fragment {
	
	int currentId = 1;
	
	String Gift_qn = "";
	String[] parts = {};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.question, container, false);
		LinearLayout qun_layout = (LinearLayout) view.findViewById(R.id.myquestions_page);
		TextView mytext = new TextView(view.getContext());
		makeparts();
		mytext.setText("Question "+ currentId + ". "+parts[0] +"\n" 
				+ parts[1] + "\n"
				+ parts[2] + "\n"
				+ parts[3] + "\n");
		mytext.setPadding(10, 5, 10, 5);
		mytext.setTextSize(18);
		qun_layout.addView(mytext);
		
		if(parts[3].equals("Multiple") || parts[3].equals("Multiple_many")){
			Log.d("I found :", parts[2]);
			ArrayList<McqOpts> list = parseMCQ(parts[2]); 
			for(int i = 0;i<list.size();i++){
				Log.d(""+i,list.get(i).toString());
			}
		}
		
		
		return view;
	}
	
	public void setId(int id){
		this.currentId = id;
	}
	
	public void setGift_qn(String str){
		this.Gift_qn = str;
	}
	

	public void makeparts() {
		String s = this.Gift_qn;
		int v = s.indexOf('{');
		int v1 = s.indexOf('}');
		int v2 = s.indexOf(':', 2);
		String title = "";
		String question = (String) s.subSequence(0, v);
		String answerpart = (String) s.subSequence(v + 1, v1);
		if (s.startsWith("::")) {
			title = (String) s.subSequence(2, v2);
			v2 = v2 + 2;
			question = (String) s.subSequence(v2, v);
		}
		v1++;
		if (v1 != s.length() && s.charAt(v1) != '.') {
			question += "__________";
			question += s.subSequence(v1, s.length());
		}
		parts = new String[4];
		parts[0] = title;
		parts[1] = question;
		parts[2] = answerpart.replaceAll("^[ \t]+", "") ;
		parts[3] = getType(parts[2]);
		
	}

	private String getType(String q) {
		// TODO Auto-generated method stub
		char v2 = q.charAt(0);
		switch (v2) {
			case '#':
				return "Numeric";
			case '~':
				if (q.contains("=")) {
					return "Multiple";
				} else {
					return "Multiple_many";
				}
			case '}':
				return "Essay";
			case 't':
			case 'T':
			case 'F':
			case 'f':
				return "True_false";
			case '=':
			case '\n':
				if (q.contains("~")) {
					if (q.contains("=")) {
						return "Multiple";
					} else {
						return "Multiple_many";
					}
				} else if (q.contains("->")) {
					return "Matching";
				} else {
					return "Short_Answer";
				}
			default:
				return "Missing_word";
		}
	}
	
	public ArrayList<McqOpts> parseMCQ(String ans){
		
		ArrayList<McqOpts> list = new ArrayList<McqOpts>(); 
		Pattern p = Pattern.compile("([~=][^~=]+)");
		Matcher m = p.matcher(ans);
		while(m.find()){

			
			boolean isAns = (m.group(0).contains("~"))?false:true;
			int weight = 100;
			String feedback = "";
			String value = "";
			
			String[] a = m.group(0).split("%",3);
			//Log.d("Option ",a.length);

			if(a.length == 1){
				String[] b = a[0].split("#");
				value = b[0].substring(1);
				if(b.length != 1)feedback = b[1];
				else feedback = "No Feedback Provided.";
			}
			else {
				
				
				weight = Integer.parseInt(a[1]);
				String[] b = a[2].split("#");
				value = b[0].substring(1);
				if(b.length != 1){
					feedback = b[1];
				}
				else feedback = "No Feedback Provided.";
			}
			list.add(new McqOpts(weight, value, feedback, isAns));
			//Log.d("Option ","Found : "+ m.group(0));
			//Log.d("Option ","Feedback : " + feedback + " |  Weight :"+ weight + " Value : " + value);
			//Log.d("Option "," ---------------------------------------------------------------------");
			
		}
		return list;
		
	}
	
	
	@SuppressWarnings("unused")
	private class McqOpts {
		int weight;
		String value,feedback;
		boolean isAns;
		
		public McqOpts(int w, String v, String f, boolean ans){
			this.weight = w;
			this.value = v;
			this.feedback = f;
			this.isAns = ans;
		}
		
		public String toString(){
			return ("Option : "+ this.value + " Feedback : "+this.feedback + " Weight :"+this.weight);
		}
	}


}
