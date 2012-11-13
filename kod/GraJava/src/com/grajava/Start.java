package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;

public class Start extends Activity {
	
	Button P;
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s);
		P = (Button) findViewById(R.id.Pusty);
		context = getApplicationContext();
		P.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					Intent intent = new Intent(context, GraJava.class);
					startActivity(intent);
        			finish();
				}
			});
    }
}
