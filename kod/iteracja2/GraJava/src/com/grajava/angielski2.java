package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class angielski2 extends Activity {
	Context context;
	Button car;
	Button doll;
	Button dog;
	Button sister;

	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.angielski2);					  
		car = (Button)findViewById(R.id.car); 
		doll = (Button)findViewById(R.id.doll); 
		dog = (Button)findViewById(R.id.dog);
		sister = (Button)findViewById(R.id.sister);
									
		car.setOnClickListener(new OnClickListener() {  
										
			public void onClick(View v){
				Intent intent = new Intent(context, angielski3.class);
				startActivity(intent);
				
			}
		});
											
		doll.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, angielski3a.class);
				startActivity(intent);
				
			}
		});
											
		dog.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, angielski3a.class);
				startActivity(intent);
				
			}
		});
									
		sister.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, angielski3a.class);
				startActivity(intent);
				
			}
		});
	}
}