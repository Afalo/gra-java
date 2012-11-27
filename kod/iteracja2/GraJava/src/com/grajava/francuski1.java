package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class francuski1 extends Activity {
	Context context;	
	Button fr1;

	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        	
			setContentView(R.layout.francuski1);
			fr1 = (Button) findViewById(R.id.Pusty);
						
			fr1.setOnClickListener(new OnClickListener() {
							
					public void onClick(View v){
						Intent intent = new Intent(context, francuski2.class);
						startActivity(intent);
						finish();
					}
			});
	}
}
