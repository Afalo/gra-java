package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GraJava extends Activity {
	Button Start;
	Button Wyjscie;

	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Start = (Button) findViewById(R.id.Start);
		Wyjscie = (Button) findViewById(R.id.Wyjscie);

		Start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(
						new Intent(getApplication(), wybor.class));
				System.gc();
				GraJava.this.finish();
			}
		});
		
		Wyjscie.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				System.exit(0);
			}
		});
	}
}
