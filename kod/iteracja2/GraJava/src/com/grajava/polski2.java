package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class polski2 extends Activity {
	Context context;
	Button kwiatek;
	Button samochod;
	Button pies;
	Button siostra;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.polski2);
									  
		kwiatek = (Button)findViewById(R.id.Kwiatek); 
		samochod = (Button)findViewById(R.id.samochod); 
		pies = (Button)findViewById(R.id.pies);
		siostra = (Button)findViewById(R.id.siostra);
									
									
		kwiatek.setOnClickListener(new OnClickListener() {  
										
			public void onClick(View v){
				Intent intent = new Intent(context, polski3.class);
				startActivity(intent);
				finish();
			}
		});
											
		samochod.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, polski3a.class);
				startActivity(intent);
				finish();
			}
		});
											
		pies.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, polski3a.class);
				startActivity(intent);
				finish();
			}
		});
									
		siostra.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, polski3a.class);
				startActivity(intent);
				finish();
			}
		});
	}
}