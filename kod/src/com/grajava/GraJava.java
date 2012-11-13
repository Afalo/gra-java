package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GraJava extends Activity {
	
	Button Start;
	Button Wyjscie;
	Context context;
    public void Start(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = getApplicationContext();
    
        Start = (Button) findViewById(R.id.Start);
        Wyjscie = (Button) findViewById(R.id.Wyjscie);
        
        Start.setOnClickListener(new OnClickListener() {
        	 
        	public void onClick(View v) {
        		for(int i = 0; i<=10; i++){
        			Intent intent = new Intent(context, GraJava1.class);
        			startActivity(intent);
        		}
        		onStop();
        	    Intent in = new Intent(context, GraJava3.class);
        	    startActivity(in);
        	}
        });
        Wyjscie.setOnClickListener(new OnClickListener() {
       	 
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
