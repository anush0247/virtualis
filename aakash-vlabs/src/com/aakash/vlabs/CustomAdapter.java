package com.aakash.vlabs;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	
	private ArrayList<ExperimentDetails> theData;
    Context theCont;
    
    CustomAdapter (ArrayList<ExperimentDetails> data, Context c){
        theData = data;
        theCont = c;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return theData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return theData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
        if (v == null)
        {
        	LayoutInflater vi = (LayoutInflater)theCont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.custom_list, null);
        }

          	ImageView icon = (ImageView) v.findViewById(R.id.icon);
            TextView desc = (TextView) v.findViewById(R.id.desc);
            ImageView p1 = (ImageView) v.findViewById(R.id.p1);
            ImageView p2 = (ImageView) v.findViewById(R.id.p2);
            ImageView p3 = (ImageView) v.findViewById(R.id.p3);
            ImageView p4 = (ImageView) v.findViewById(R.id.p4);
            ImageView p5 = (ImageView) v.findViewById(R.id.p5);

            ExperimentDetails exp = theData.get(position);
            icon.setImageResource(exp.icon);
            desc.setText(exp.desc);
            p1.setImageResource(exp.p1);
            p2.setImageResource(exp.p2);
            p3.setImageResource(exp.p3);
            p4.setImageResource(exp.p4);
            p4.setImageResource(exp.p5);                        
                       
            return v;
	}

}
