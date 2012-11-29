package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class angielski3a extends Activity {

	ImageButton ang3a;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.angielski3a);
		ang3a = (ImageButton) findViewById(R.id.iba3a);
										
		ang3a.setOnClickListener(new OnClickListener() {
												
			public void onClick(View v){
				System.gc();
				finish();
			}
		});
	}
}

						