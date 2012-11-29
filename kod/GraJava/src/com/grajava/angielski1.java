package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class angielski1 extends Activity {
	ImageButton ang1;

	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.angielski1);
		ang1 = (ImageButton) findViewById(R.id.iba1);
					
		ang1.setOnClickListener(new OnClickListener() {
							
		public void onClick(View v){
			startActivity(
					new Intent(getApplication(), angielski2.class));
			System.gc();
			angielski1.this.finish();
			
		}
		});
	}
}
							