package com.example.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 fr1 = (fragment1) getFragmentManager().findFragmentById(R.id.fragment1);
        if(fr1 == null || ! fr1.isInLayout()){
        	FragmentTransaction ft = getFragmentManager().beginTransaction();
        	ft.add(R.id.fragment1, new fragment1());
        	ft.commit(); 
        }
        
    }
}
