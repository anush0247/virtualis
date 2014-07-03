package com.example.myfragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Content extends Fragment{

	final String POSITION = "position";
	int MY_POSITON = -1; 
	
	String array[] = {"my text 1", "My text 2", "My text3"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(savedInstanceState != null){
			 MY_POSITON = savedInstanceState.getInt(POSITION);
		}
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.content, container);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt(POSITION, MY_POSITON);
	}

	public void getUpdate(int position2) {
		// TODO Auto-generated method stub
		
		EditText edit = (EditText)getActivity().findViewById(R.id.mytext);
		edit.setText(array[position2]);
		MY_POSITON = position2;
		
	}
	
	@Override
	public void onStart() {
	    super.onStart();
	
	    Bundle args = getArguments();
	    if (args != null) {
	        getUpdate(args.getInt(POSITION));
	    } else if (MY_POSITON != -1) {
	        // Set article based on saved instance state defined during onCreateView
	        
	    }
	}

}
