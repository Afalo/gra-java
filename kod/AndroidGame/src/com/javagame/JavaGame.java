package com.javagame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JavaGame extends Activity {
	Button Start;
	Button Option;
	Button Exit;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_java_game);
		Start = (Button) findViewById(R.id.Start);
		Option = (Button) findViewById(R.id.opcje);
		Exit = (Button) findViewById(R.id.Wyjscie);

		Start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(getApplication(), Choose.class));
				System.gc();
				finish();
			}
		});
		
		Option.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				startActivity(new Intent(getApplication(), Option.class));
				System.gc();
				finish();
			}
		});
		
		Exit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				System.exit(0);
			}
		});
	}
}
