package com.example.myfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Titles extends ListFragment{
	
	public interface OnTitleSelectedListener{
		public void onTitleSelectedListener(int position);
	}

	OnTitleSelectedListener title_selected;
	String mylinks[] = {"Link1", "Link2", "Link3"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mylinks));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try{
			title_selected = (OnTitleSelectedListener) activity;
		}
		catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + ": Must Implement OnTitleSelectedListener ");
		}
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		getListView().setItemChecked(position, true);
	}

	public void setListener(OnTitleSelectedListener MyListener) {
		// TODO Auto-generated method stub
		this.title_selected = MyListener;
		
		
	}

}
