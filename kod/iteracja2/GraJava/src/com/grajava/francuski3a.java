package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class francuski3a extends Activity {
	Context context;
	Button sumfr;
	Button fr3a;
	Button frsu;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.francuski3a);
		fr3a = (Button) findViewById(R.id.pointf);
											
		fr3a.setOnClickListener(new OnClickListener() {
												
			public void onClick(View v){
				setContentView(R.layout.sumang);
				frsu = (Button) findViewById(R.id.Pusty);
				
				frsu.setOnClickListener(new OnClickListener() {
					
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