package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class angielski1 extends Activity {
	Context context;
	Button ang1;

	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.angielski1);
		ang1 = (Button) findViewById(R.id.Pusty);
					
		ang1.setOnClickListener(new OnClickListener() {
							
		public void onClick(View v){
			Intent intent = new Intent(context, angielski2.class);
			startActivity(intent);
			finish();
		}
		});
	}
}
							