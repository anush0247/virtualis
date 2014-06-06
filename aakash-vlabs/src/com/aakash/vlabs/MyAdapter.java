package com.aakash.vlabs;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends ArrayAdapter<String>{
	
	private Context context;
	private ArrayList<String> values;
	static public int j = -1;
	int SubPosition = 0;

	public MyAdapter(Context context, ArrayList<String> arr, int mCurrentPosition) {
	    super(context, R.layout.custom_list, arr);
	    this.context = context;
	    this.values = arr;
	    this.SubPosition = mCurrentPosition;
	  }
	
	@Override
	  public View getView(final int position, View convertView, final ViewGroup parent) {
	    
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
	    textView1.setText(values.get(position));

	    textView2.setText(JSONdata.ExperimentsDesc.get(j).get(position));
	    
	    imageView.setImageResource(R.drawable.ic_launcher);
	    
	    rowView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Intent intent=new Intent(parent.getContext(), MainActivity.class);   
				
				//intent.putExtra("class_no", );
				//intent.putExtra("subject", JSONdata.Subjects.get(SubPosition));
				//intent.putExtra("exp_name", values.get(position));
				//intent.putExtra("exp_no", exp_no);
				Toast.makeText(parent.getContext(), "view clicked: "+ JSONdata.Subjects.get(SubPosition) + " , " + values.get(position), Toast.LENGTH_SHORT).show();

				//parent.getContext().startActivity(intent);
				
			}
		});
	    
	    return rowView;
	  }

}
