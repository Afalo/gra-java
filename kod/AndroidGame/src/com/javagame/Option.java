package com.javagame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;

public class Option extends Activity {
	Button Add;
	Button Delete;
	Button Back;
	Switch Music;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		Add = (Button) findViewById(R.id.add);
		Delete = (Button) findViewById(R.id.delete);
		Back = (Button) findViewById(R.id.menu);

		Add.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

			}
		});

		Delete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
			}
		});
		Back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(getApplication(), JavaGame.class));
				System.gc();
				finish();
			}
		});
	}
}
