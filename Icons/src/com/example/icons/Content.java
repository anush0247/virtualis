package com.example.icons;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Content extends Fragment{
	
	private String[] myclasses = {"Theory","Procedure","Videos","Simulation","Quiz","Resources"};
	private TextView txt,mytitle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onCreateView(inflater, container, savedInstanceState);
		View view  = inflater.inflate(R.layout.content, container, false);
		mytitle = (TextView) view.findViewById(R.id.mytitle);
		txt = (TextView) view.findViewById(R.id.mytext);
		//Theory theory = (Theory) getChildFragmentManager().findFragmentById(R.id.theory);
		changeText(0);
		return view;
	}
	
	@SuppressLint("NewApi")
	public void changeText(int id) {
		// TODO Auto-generated method stub	
		mytitle.setText(myclasses[id]);
		mytitle.setBackground(getResources().getDrawable(R.drawable.abc_cab_background_top_holo_light));
		txt.setText(myclasses[id]);
	}
}
