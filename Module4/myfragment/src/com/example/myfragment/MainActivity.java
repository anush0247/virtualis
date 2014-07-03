package com.example.myfragment;



import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements Titles.OnTitleSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(findViewById(R.id.titles_fragment) != null){
			if(savedInstanceState != null) {
				return;
			}
			
			Titles One = new Titles();
			One.setListener(this);
			One.setArguments(getIntent().getExtras());
			getSupportFragmentManager().beginTransaction()
            .add(R.id.titles_fragment,One).commit();
		}
		
	}

	@Override
	public void onTitleSelectedListener(int position) {
		// TODO Auto-generated method stub
		Content Two = (Content) getSupportFragmentManager().findFragmentById(R.id.content_fragment);
		if(Two != null){
			Two.getUpdate(position);
		}
		else {
			Content Two_Frag =  new Content();
			Bundle args = new Bundle();
			args.putInt(Two_Frag.POSITION, position);
			Two_Frag.setArguments(args);
			android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_fragment, Two_Frag);
			
			transaction.addToBackStack(null);
			transaction.commit();
		}
		
	}
}
