package com.example.icons;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
		theory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					prev.setBackgroundColor(Color.TRANSPARENT);
				}catch(Exception e){
					
				}
				Id = 0;
				buttonClicked(Id);
				theory.setBackgroundColor(mycolor);
				prev = theory;
				
			}			
		});
		
		//firstView(container);
		//firstView();
		
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
				Id = 1;
				buttonClicked(Id);
				procedure.setBackgroundColor(mycolor);
				prev = procedure;
				
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
				Id = 2;
				buttonClicked(Id);
				videos.setBackgroundColor(mycolor);
				prev = videos;
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
				Id = 3;
				buttonClicked(Id);
				simulation.setBackgroundColor(mycolor);
				prev = simulation;
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
				Id = 4;
				buttonClicked(Id);
				quiz.setBackgroundColor(mycolor);
				prev = quiz;
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
				Id = 5;
				buttonClicked(Id);
				resources.setBackgroundColor(mycolor);
				prev = resources;
			}			
		});
		
		return view;
	}

	
}
