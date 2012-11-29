package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class polski3a extends Activity {	
	ImageButton pol3a;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
		setContentView(R.layout.polski3a);
		pol3a = (ImageButton) findViewById(R.id.ibp3a);
											
		pol3a.setOnClickListener(new OnClickListener() {
												
		public void onClick(View v){
			System.gc();
			finish();
		}
		});
	}
}
			