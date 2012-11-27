package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class polski3a extends Activity {
	Context context;
	Button polsu;
	Button pol3a;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
		setContentView(R.layout.polski3a);
		pol3a = (Button) findViewById(R.id.punkt);
											
		pol3a.setOnClickListener(new OnClickListener() {
												
		public void onClick(View v){
			setContentView(R.layout.sumpol);
			polsu = (Button) findViewById(R.id.Pusty);
			
			polsu.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v){
					Intent intent = new Intent(context, GraJava.class);
					startActivity(intent);
					finish();
				}
			});
		}
		});
	}
}
			