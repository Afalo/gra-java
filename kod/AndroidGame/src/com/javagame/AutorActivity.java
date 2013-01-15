package com.javagame;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AutorActivity extends Activity{
	Button cofnij;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (1);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_autor);
		cofnij = (Button) findViewById(R.id.cofnij);
		
		cofnij.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(getApplication(), JavaGame.class));
				System.gc();
				finish();
			}
		});
	}

}
