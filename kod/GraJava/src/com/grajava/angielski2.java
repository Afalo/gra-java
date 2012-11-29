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

public class angielski2 extends Activity {
	Button car;
	Button doll;
	Button dog;
	Button sister;
	ImageButton angsu;
	TextView tekst;
	int a, i=1;

	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.angielski2);					  
		car = (Button)findViewById(R.id.car); 
		doll = (Button)findViewById(R.id.doll); 
		dog = (Button)findViewById(R.id.dog);
		sister = (Button)findViewById(R.id.sister);
									
		car.setOnClickListener(new OnClickListener() {  
										
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), angielski3.class));
					a++;
					i++;
					if(i>10){
						setContentView(R.layout.sumang);
						angsu = (ImageButton) findViewById(R.id.ibas);
						tekst = (TextView)findViewById(R.id.tvas);
						
						tekst.setText("Your Score = "+a+"!!!");
						
						angsu.setOnClickListener(new OnClickListener() {
							
							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								angielski2.this.finish();
							}
						});
					}
				}
			}
		});
											
		doll.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), angielski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumang);
						angsu = (ImageButton) findViewById(R.id.ibas);
						tekst = (TextView)findViewById(R.id.tvas);

						tekst.setText("Your Score = "+a+"!!!");

						angsu.setOnClickListener(new OnClickListener() {

							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								angielski2.this.finish();
							}
						});
					}
				}
			}
		});
											
		dog.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), angielski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumang);
						angsu = (ImageButton) findViewById(R.id.ibas);
						tekst = (TextView)findViewById(R.id.tvas);

						tekst.setText("Your Score = "+a+"!!!");

						angsu.setOnClickListener(new OnClickListener() {

							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								angielski2.this.finish();
							}
						});
					}
				}
			}
		});			
		
		sister.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v){
				if(i<=10){
					startActivity(
							new Intent(getApplication(), angielski3a.class));
					i++;
					if(i>10){
						setContentView(R.layout.sumang);
						angsu = (ImageButton) findViewById(R.id.ibas);
						tekst = (TextView)findViewById(R.id.tvas);

						tekst.setText("Your Score = "+a+"!!!");

						angsu.setOnClickListener(new OnClickListener() {

							public void onClick(View v){
								startActivity(
										new Intent(getApplication(), GraJava.class));
								System.gc();
								angielski2.this.finish();
							}
						});
					}
				}
			}
		});
	}
}