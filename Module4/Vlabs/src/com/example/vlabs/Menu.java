package com.example.vlabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	int max = 5;
	private TextView TextViewSet[] = new TextView[max];
	String links[] = {"Theory","Procedure","Videos","Quiz","Resources"};
	String data[] = {
		"Theory will be displayed here",
		"Your Procedure will be displayed here",
		"Videos will be displayed here",
		"Quiz will be loaded here",
		"Resources will be loded here"
	};
	
	public void putText(){
		for(int i=0;i<max;i++){
			TextViewSet[i] = new TextView(getActivity());
			//TextViewSet[i].setText(links[i]);
			TextViewSet[i].setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.btn_star, 0, 0, 0);
		}
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.menu, container, false);
		ImageButton img1 = (ImageButton) view.findViewById(R.id.ImageButton01);
		return view;
	}

	

	
	
}
