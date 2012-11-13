package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GraJava1 extends Activity {
	Button car;
	Button carr;
	Button ccar;
	Button caar;
	int a=0;
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran1);
        context = getApplicationContext();
    
        car = (Button) findViewById(R.id.car);
        carr = (Button) findViewById(R.id.carr);
        ccar = (Button) findViewById(R.id.ccar);
        caar = (Button) findViewById(R.id.caar);

        car.setOnClickListener(new OnClickListener() {
       	 
        	public void onClick(View v) {
        			Intent intent = new Intent(context, GraJava2.class);
        			startActivity(intent);
        			a++;
        			GraJava3 g3 = new GraJava3();
        			g3.sumowanie(a);
        			finish();
        	}
        });
        carr.setOnClickListener(new OnClickListener() {
          	 
        	public void onClick(View v) {
        			Intent intent = new Intent(context, GraJava2a.class);
        			startActivity(intent);
        			a--;
        			GraJava3 g3 = new GraJava3();
        			g3.sumowanie(a);
        			finish();
        	}
        });
        ccar.setOnClickListener(new OnClickListener() {
          	 
        	public void onClick(View v) {
        			Intent intent = new Intent(context, GraJava2a.class);
        			startActivity(intent);
        			a--;
        			GraJava3 g3 = new GraJava3();
        			g3.sumowanie(a);
        			finish();
        	}
        });
        caar.setOnClickListener(new OnClickListener() {
          	 
        	public void onClick(View v) {
        			Intent intent = new Intent(context, GraJava2a.class);
        			startActivity(intent);
        			a--;  
        			GraJava3 g3 = new GraJava3();
        			g3.sumowanie(a);
        			finish();
        	}
        });
    }
}
