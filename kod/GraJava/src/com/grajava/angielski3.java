package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class angielski3 extends Activity {

	ImageButton ang3;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.angielski3);
		ang3 = (ImageButton) findViewById(R.id.iba3);
										
		ang3.setOnClickListener(new OnClickListener() {
												
			public void onClick(View v){
				System.gc();
				finish();
			}
		});
	}
}

						