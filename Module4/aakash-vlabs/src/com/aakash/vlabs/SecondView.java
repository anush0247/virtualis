package com.aakash.vlabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.Fragment;

public class SecondView extends FragmentActivity{

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_view);
		Fragment sub_frag = (Fragment) getFragmentManager().findFragmentById(R.id.subject_list);
		
	}

}
