package com.aakash.vlabs.quiz;

import android.view.View;
import android.widget.LinearLayout;

public class DrawLayout {
	
	// input : Parsed Question String array 
	// output : parse the Answer String and return a view and validation conditions 
	
	LinearLayout qun_layout;
	View view;
	
	public DrawLayout(LinearLayout qun_layout, View view){
		this.qun_layout = qun_layout;
		this.view = view;
	}
	 
	public void drawTF(LinearLayout qun_layout, View view, String trueAns, String feedback){
		
	}
}