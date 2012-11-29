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

public class polski2 extends Activity {

	Button kwiatek;
	Button samochod;
	Button pies;
	Button siostra;
	ImageButton polsu;
	TextView tekst;
	int a, i=1;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.polski2);
									  
		kwiatek = (Button)findViewById(R.id.kwiatek); 
		samochod = (Button)findViewById(R.id.samochod); 
		pies = (Button)findViewById(R.id.pies);
		siostra = (Button)findViewById(R.id.siostra);
									
									
		kwiatek.setOnClickListener(new OnClickListener() {  
										
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), polski3.class));
					a++;
					i++;
					if(i>10){
						setContentView(R.layout.sumpol);
						polsu = (ImageButton) findViewById(R.id.ibps);
						tekst = (TextView)findViewById(R.id.tvps);
						
						tekst.setText("Suma punktów = "+a+"!!!");
						
						polsu.setOnClickListener(new OnClickListener() {
							
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								polski2.this.finish();
							}
						});
					}
				}
			}
		});
											
		samochod.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), polski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumpol);
						polsu = (ImageButton) findViewById(R.id.ibps);
						tekst = (TextView)findViewById(R.id.tvps);
						
						tekst.setText("Suma punktów = "+a+"!!!");
						
						polsu.setOnClickListener(new OnClickListener() {
							
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								polski2.this.finish();
							}
						});
					}
				}
			}
		});
											
		pies.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), polski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumpol);
						polsu = (ImageButton) findViewById(R.id.ibps);
						tekst = (TextView)findViewById(R.id.tvps);
						
						tekst.setText("Suma punktów = "+a+"!!!");
						
						polsu.setOnClickListener(new OnClickListener() {
							
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								polski2.this.finish();
							}
						});
					}
				}
			}
		});
									
		siostra.setOnClickListener(new OnClickListener() {
										  
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), polski3a.class));
					a++;
					i++;
					if(i>10){
						setContentView(R.layout.sumpol);
						polsu = (ImageButton) findViewById(R.id.ibps);
						tekst = (TextView)findViewById(R.id.tvps);
						
						tekst.setText("Suma punktów = "+a+"!!!");
						
						polsu.setOnClickListener(new OnClickListener() {
							
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								polski2.this.finish();
							}
						});
					}
				}
			}
		});
	}
}