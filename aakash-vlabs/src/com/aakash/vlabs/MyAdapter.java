package com.aakash.vlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String>{
	
	private Context context;
	private String[] values;

	public MyAdapter(Context context, String[] values) {
	    super(context, R.layout.custom_list, values);
	    this.context = context;
	    this.values = values;
	  }
	
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    
		View rowView = convertView;
		//LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    //View rowView = inflater.inflate(R.layout.custom_list, parent, false);
	    
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.custom_list,parent, false);
        }
		
		TextView textView1 = (TextView) rowView.findViewById(R.id.headin);
		TextView textView2 = (TextView) rowView.findViewById(R.id.desc);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    textView1.setText(values[position]);
	    textView2.setText(values[position]);
	    
	    //String s = values[position];
//	    if (s.startsWith("Windows7") || s.startsWith("iPhone")
//	        || s.startsWith("Solaris")) {
//	      imageView.setImageResource(R.drawable.ic_launcher);
//	    } else {
	    imageView.setImageResource(R.drawable.ic_launcher);
//	    }

	    return rowView;
	  }

}
