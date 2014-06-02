package com.example.icons;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Theory extends Fragment{

	private TextView mytitle_theory;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view  = inflater.inflate(R.layout.content, container, false);
		mytitle_theory = (TextView) view.findViewById(R.id.mytitle_theory);
		changeText();
		return view;
	}
	
	@SuppressLint("NewApi")
	public void changeText() {
		// TODO Auto-generated method stub
		mytitle_theory.setText("This is Theory Fragment");
		mytitle_theory.setBackground(getResources().getDrawable(R.drawable.abc_cab_background_top_holo_light));
	}

}
