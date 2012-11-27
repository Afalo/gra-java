package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class angielski3a extends Activity {
	Context context;
	Button angsu;
	Button ang3a;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.angielski3a);
		ang3a = (Button) findViewById(R.id.wrong);
										
		ang3a.setOnClickListener(new OnClickListener() {
												
			public void onClick(View v){
				setContentView(R.layout.sumang);
				angsu = (Button) findViewById(R.id.Pusty);
				
				angsu.setOnClickListener(new OnClickListener() {
					
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

						