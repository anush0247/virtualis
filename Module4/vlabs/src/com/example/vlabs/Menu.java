package com.example.vlabs;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends ListFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	int max = 5;
	TextView TextViewSet[] = new TextView[max];
	String links[] = {"Theory","Procedure","Videos","Quiz","Resources"};
	String data[] = {
		"Theory will be displayed here",
		"Your Procedure will be displayed here",
		"Videos will be displayed here",
		"Quiz will be loaded here",
		"Resources will be loded here"
	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//ListView mylist = (ListView) getActivity().findViewById(R.id.listView1);
		for(int i=0;i<max;i++){
			TextViewSet[i] = new TextView(getActivity());
			TextViewSet[i].setText(links[i]);
			TextViewSet[i].setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.btn_star, 0, 0, 0);
		}
		ArrayAdapter<TextView> adapter = new ArrayAdapter<TextView>(getActivity(),android.R.layout.simple_list_item_1,TextViewSet);
		setListAdapter(adapter);
		
	
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Content content_frag = (Content) getFragmentManager().findFragmentById(R.id.content);
		if(content_frag != null && content_frag.isInLayout()){
			content_frag.updateText(data[position]);
		}
	}

	
	
}
