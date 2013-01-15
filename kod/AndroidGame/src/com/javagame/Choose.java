package com.javagame;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.javagame.utils.StaticHelper;

public class Choose extends Activity {
	Button polski;
	Button angielski;
	Button francuski;
	Button cofnij;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (1);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_choose);
		polski = (Button) findViewById(R.id.polski);
		angielski = (Button) findViewById(R.id.angielski);
		francuski = (Button) findViewById(R.id.francuski);
		cofnij = (Button) findViewById(R.id.cofnij);

		polski.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), MainActivity_pl.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});

		angielski.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), MainActivity_ang.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_EN);
				startActivity(i);
				System.gc();
				finish();

			}
		});

		francuski.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(getApplication(), MainActivity_fr.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_FR);
				startActivity(i);
				System.gc();
				finish();
			}
		});

		cofnij.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				startActivity(new Intent(getApplication(), JavaGame.class));
				System.gc();
				finish();
			}
		});
	}
}

