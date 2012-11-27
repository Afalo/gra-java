package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class francuski3 extends Activity {
	Context context;
	Button frsu;
	Button fr3;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.francuski3);
		fr3 = (Button) findViewById(R.id.pointf);
											
		fr3.setOnClickListener(new OnClickListener() {
												
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