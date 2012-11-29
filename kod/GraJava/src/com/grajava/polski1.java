package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class polski1 extends Activity {
	ImageButton pol1;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.polski1);
		pol1 = (ImageButton) findViewById(R.id.ibp1);
					
		pol1.setOnClickListener(new OnClickListener() {
							
			public void onClick(View v){
				startActivity(
						new Intent(getApplication(), polski2.class));
				System.gc();
				polski1.this.finish();
			}
		});
	}
}