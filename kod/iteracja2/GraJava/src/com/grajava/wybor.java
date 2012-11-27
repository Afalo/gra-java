package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class wybor extends Activity {
	Context context;
	Button polski;
	Button angielski;
	Button francuski;
	Button cofnij;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
		setContentView(R.layout.wybor);
		polski = (Button) findViewById(R.id.polski);
		angielski = (Button) findViewById(R.id.angielski);
		francuski = (Button) findViewById(R.id.francuski);
		cofnij = (Button) findViewById(R.id.cofnij);
				
		polski.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
				Intent intent = new Intent(context, polski1.class);
				startActivity(intent);
				
			}
		});
			
		angielski.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
				Intent intent = new Intent(context, angielski1.class);
				startActivity(intent);
				
			}
		});
			
		francuski.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
				Intent intent = new Intent(context, francuski1.class);
				startActivity(intent);
				
			}
		});
				
		cofnij.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
						Intent intent = new Intent(context, GraJava.class); 
						startActivity(intent);
						finish();
					}
				});
	}
}

