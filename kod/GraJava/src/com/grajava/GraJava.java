package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GraJava extends Activity {
	
	TextView Minus;
	TextView Plus;
	TextView suma;
	Button Start;
	Button Wyjscie;
	Context context;
	Button car;
	Button doll;
	Button dog;
	Button sister;
	Button Ok;
	int a=0;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s);
        setContentView(R.layout.main);
        context = getApplicationContext();
    
        Start = (Button) findViewById(R.id.Start);
        Wyjscie = (Button) findViewById(R.id.Wyjscie);
        
        Start.setOnClickListener(new OnClickListener() {
        	 
        	public void onClick(View v) {
        		
                	
                        
                	
					
						
        			setContentView(R.layout.ekran1);
        	    
        	        car = (Button) findViewById(R.id.car);
        	        doll = (Button) findViewById(R.id.doll);
        	        dog = (Button) findViewById(R.id.dog);
        	        sister = (Button) findViewById(R.id.sister);

        	        car.setOnClickListener(new OnClickListener() {
        	       	 
        	        	public void onClick(View v){
							a++;
							Intent intent = new Intent(context, GraJava2.class);
							startActivity(intent);
        	        
							setContentView(R.layout.ekran3);
							suma = (TextView) findViewById(R.id.suma);
							suma.setText("Suma punktów = "+a);
							Ok = (Button) findViewById(R.id.Ok);
							Ok.setOnClickListener(new OnClickListener() {

									public void onClick(View v) {
										Intent intent = new Intent(context, GraJava.class);
										startActivity(intent);
										finish();
									}
								});
        	        	}
        	        });
        	        doll.setOnClickListener(new OnClickListener() {
        	          	 
        	        	public void onClick(View v) {
							
							Intent intent = new Intent(context, GraJava2a.class);
							startActivity(intent);
        	        		setContentView(R.layout.ekran3);
							suma = (TextView) findViewById(R.id.suma);
							suma.setText("Suma punktów = "+a);
							Ok = (Button) findViewById(R.id.Ok);
							Ok.setOnClickListener(new OnClickListener() {

									public void onClick(View v) {
										Intent intent = new Intent(context, GraJava.class);
										startActivity(intent);
										finish();
									}
								});
    
        	        		
        	        	}
					
        	        });
        	        dog.setOnClickListener(new OnClickListener() {
        	          	 
        	        	public void onClick(View v) {
							Intent intent = new Intent(context, GraJava2a.class);
							startActivity(intent);
        	        			
     						setContentView(R.layout.ekran3);
							suma = (TextView) findViewById(R.id.suma);
							suma.setText("Suma punktów = "+a);
							Ok = (Button) findViewById(R.id.Ok);
							Ok.setOnClickListener(new OnClickListener() {

									public void onClick(View v) {
										Intent intent = new Intent(context, GraJava.class);
										startActivity(intent);
										finish();
									}
								});
        	        	
        	        	}
        	        });
        	        sister.setOnClickListener(new OnClickListener() {
        	          	 
        	        	public void onClick(View v) {
        	               
        	        			
        	        		Intent intent = new Intent(context, GraJava2.class);
							startActivity(intent);
							setContentView(R.layout.ekran3);
							suma = (TextView) findViewById(R.id.suma);
							suma.setText("Suma punktów = "+a);
							Ok = (Button) findViewById(R.id.Ok);
							Ok.setOnClickListener(new OnClickListener() {

									public void onClick(View v) {
										Intent intent = new Intent(context, GraJava.class);
										startActivity(intent);
										finish();
									}
								});
        	        	}
        	        });

                	
        	    
        }
        });
        Wyjscie.setOnClickListener(new OnClickListener() {
       	 
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
