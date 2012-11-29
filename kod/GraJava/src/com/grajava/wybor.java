package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class wybor extends Activity {
	Button polski;
	Button angielski;
	Button francuski;
	Button cofnij;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.wybor);
		polski = (Button) findViewById(R.id.polski);
		angielski = (Button) findViewById(R.id.angielski);
		francuski = (Button) findViewById(R.id.francuski);
		cofnij = (Button) findViewById(R.id.cofnij);
				
		polski.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
				startActivity(
						new Intent(getApplication(), polski1.class));
				System.gc();
				wybor.this.finish();
				
			}
		});
			
		angielski.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
				startActivity(
						new Intent(getApplication(), angielski1.class));
				System.gc();
				wybor.this.finish();
				
			}
		});
			
		francuski.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
				startActivity(
						new Intent(getApplication(), francuski1.class));
				System.gc();
				wybor.this.finish();
			}
		});
				
		cofnij.setOnClickListener(new OnClickListener() {
					
			public void onClick(View v){
				startActivity(
						new Intent(getApplication(), GraJava.class));
				System.gc();
				wybor.this.finish();
			}
		});
	}
}

