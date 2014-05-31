package com.example.icons;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Main extends FragmentActivity implements Menu.OnIconClick  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void changeView(int id) {
		// TODO Auto-generated method stub
		Content Content = (Content) getSupportFragmentManager().findFragmentById(R.id.content);
		Content.changeText(id);
	}
}
