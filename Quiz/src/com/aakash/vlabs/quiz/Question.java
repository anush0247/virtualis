package com.aakash.vlabs.quiz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Question extends Fragment {

	int currentId = 1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.question, container, false);
		LinearLayout qun_layout = (LinearLayout) view.findViewById(R.id.myquestions_page);
		TextView mytext = new TextView(view.getContext());
		mytext.setText("Welcome to Question No. "+ currentId);
		mytext.setPadding(20, 20, 20, 20);
		mytext.setTextSize(20);
		qun_layout.addView(mytext);
		return view;
	}
	
	public void setId(int id){
		this.currentId = id;
	}
	
	


}
