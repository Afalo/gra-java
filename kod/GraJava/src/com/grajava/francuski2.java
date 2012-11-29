package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class francuski2 extends Activity {
	Button coeur;
	Button banane;
	Button chat;
	Button ballon;
	ImageButton frsu;
	TextView tekst;
	int a, i=1;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
		setContentView(R.layout.francuski2);
								  
		coeur = (Button)findViewById(R.id.coeur); 
		banane = (Button)findViewById(R.id.banane); 
		chat = (Button)findViewById(R.id.chat);
		ballon = (Button)findViewById(R.id.ballon);
									
		coeur.setOnClickListener(new OnClickListener() {  
										
			public void onClick(View v){
				if(i<=10){
					startActivity(
						new Intent(getApplication(), francuski3.class));
					a++;
					i++;
					if(i>10){
						setContentView(R.layout.sumfr);
						frsu = (ImageButton) findViewById(R.id.ibfs);
						tekst = (TextView)findViewById(R.id.tvfs);
						
						tekst.setText("suma - fr = "+a+"!!!");
						
						frsu.setOnClickListener(new OnClickListener() {
							
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								francuski2.this.finish();
							}
						});
					}
				}
			}
		});
											
		banane.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				
				if(i<=10){
					startActivity(
							new Intent(getApplication(), francuski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumang);
						frsu = (ImageButton) findViewById(R.id.ibfs);
						tekst = (TextView)findViewById(R.id.tvfs);
			
						tekst.setText("suma - fr = "+a+"!!!");
			
						frsu.setOnClickListener(new OnClickListener() {
				
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								francuski2.this.finish();
							}
						});
					}
				}
			}
		});
											
		chat.setOnClickListener(new OnClickListener() {
										  		
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), francuski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumang);
						frsu = (ImageButton) findViewById(R.id.ibfs);
						tekst = (TextView)findViewById(R.id.tvfs);
		
						tekst.setText("suma - fr = "+a+"!!!");
		
						frsu.setOnClickListener(new OnClickListener() {
			
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								francuski2.this.finish();
							}
						});
					}
				}
			}
		});
									
		ballon.setOnClickListener(new OnClickListener() {
										  			
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), francuski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumang);
						frsu = (ImageButton) findViewById(R.id.ibfs);
						tekst = (TextView)findViewById(R.id.tvfs);
		
						tekst.setText("suma - fr = "+a+"!!!");
		
						frsu.setOnClickListener(new OnClickListener() {
			
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								francuski2.this.finish();
							}
						});
					}
				}
			}
		});
	}
}