package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class francuski1 extends Activity {	
	ImageButton fr1;

	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        	
			setContentView(R.layout.francuski1);
			fr1 = (ImageButton) findViewById(R.id.ibf1);
						
			fr1.setOnClickListener(new OnClickListener() {
							
					public void onClick(View v){
						startActivity(
								new Intent(getApplication(), francuski2.class));
						System.gc();
						francuski1.this.onDestroy();
						francuski1.this.finish();
					}
			});
	}
}
