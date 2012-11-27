package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class francuski2 extends Activity {
	Context context;
	Button coeur;
	Button banane;
	Button chat;
	Button ballon;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.francuski2);
									  
		coeur = (Button)findViewById(R.id.coeur); 
		banane = (Button)findViewById(R.id.banane); 
		chat = (Button)findViewById(R.id.chat);
		ballon = (Button)findViewById(R.id.ballon);
									
		coeur.setOnClickListener(new OnClickListener() {  
										
			public void onClick(View v){
					Intent intent = new Intent(context, francuski3.class);
					startActivity(intent);
					finish();
				}
			});
											
		banane.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, francuski3a.class);
				startActivity(intent);
				finish();
			}
		});
											
		chat.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, francuski3a.class);
				startActivity(intent);
				finish();
			}
		});
									
		ballon.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				Intent intent = new Intent(context, francuski3a.class);
				startActivity(intent);
				finish();
			}
		});
		}
}