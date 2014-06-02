package com.example.icons;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Menu extends Fragment{

	int Id,flag=0;
	OnIconClick icon;
	
	int mycolor = Color.BLACK;
	private Button theory,procedure,videos,simulation,quiz,resources;
	private Button prev = null;
	int prev_ind = -1;
	
	interface OnIconClick{
		public void changeView(int id);
	}
	
	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);
	        try {
	            icon = (OnIconClick) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement ToolbarListener");
	      }
	 }
	 
	 public void buttonClicked (int id) {
		   icon.changeView(id);
	   }
	 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onCreateView(inflater, container, savedInstanceState);
		
		View view  = inflater.inflate(R.layout.menu, container, false);
		
		theory = (Button) view.findViewById(R.id.theory);
		theory.setBackgroundColor(mycolor);
		prev = theory;
		prev_ind = 0;
		theory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					prev.setBackgroundColor(Color.TRANSPARENT);
				}catch(Exception e){
					
				}
				buttonClicked(0);
				theory.setBackgroundColor(mycolor);
				prev = theory;
				prev_ind = 0;
			}			
		});
		
		procedure = (Button) view.findViewById(R.id.procedure);
		procedure.setBackgroundColor(Color.TRANSPARENT);
		procedure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					prev.setBackgroundColor(Color.TRANSPARENT);
				}catch(Exception e){
					
				}
				buttonClicked(1);
				procedure.setBackgroundColor(mycolor);
				prev = procedure;
				prev_ind = 1;
			}			
		});
		
		videos = (Button) view.findViewById(R.id.videos);
		videos.setBackgroundColor(Color.TRANSPARENT);
		videos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					prev.setBackgroundColor(Color.TRANSPARENT);
				}catch(Exception e){
					
				}
				buttonClicked(2);
				videos.setBackgroundColor(mycolor);
				prev = videos;
				prev_ind = 2;
			}			
		});
		
		simulation = (Button) view.findViewById(R.id.simulation);
		simulation.setBackgroundColor(Color.TRANSPARENT);
		simulation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					prev.setBackgroundColor(Color.TRANSPARENT);
				}catch(Exception e){
					
				}
				buttonClicked(3);
				simulation.setBackgroundColor(mycolor);
				prev = simulation;
				prev_ind = 3;
			}			
		});
		
		quiz = (Button) view.findViewById(R.id.quiz);
		quiz.setBackgroundColor(Color.TRANSPARENT);
		quiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					prev.setBackgroundColor(Color.TRANSPARENT);
				}catch(Exception e){
					
				}
				buttonClicked(4);
				quiz.setBackgroundColor(mycolor);
				prev = quiz;
				prev_ind = 4;
			}			
		});
		
		resources = (Button) view.findViewById(R.id.resources);
		resources.setBackgroundColor(Color.TRANSPARENT);
		resources.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					prev.setBackgroundColor(Color.TRANSPARENT);
				}catch(Exception e){
					
				}
				buttonClicked(5);
				resources.setBackgroundColor(mycolor);
				prev = resources;
				prev_ind = 5;
			}			
		});
		//Log.w("text", "I am at create view");
		return view;
	}

	
}
