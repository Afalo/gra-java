package com.javagame;


import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;

public class Option extends Activity {
	Button Add;
	Button Delete;
	Button Back;
	Switch Music;
	boolean music;
	static boolean ustaw=true;
	
	protected static void putExtra(boolean b) {
		ustaw = b;	
	}
	
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (1);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_option);
		Back = (Button) findViewById(R.id.menu);
		Music = (Switch) findViewById(R.id.music);
		
		Music.setChecked(ustaw);

	
		Back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(getApplication(), JavaGame.class));
				System.gc();
				finish();
			}
		});
		
		Music.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(Music.isChecked()==true){
					music = true;
					MyQuestionActivity_ang.putExtra(music);
					MyQuestionActivity_pl.putExtra(music);
					MyQuestionActivity_fr.putExtra(music);
					SummaryActivity_ang.putExtra(music);
					SummaryActivity_pl.putExtra(music);
					SummaryActivity_fr.putExtra(music);
					Option.putExtra(true);
				}
				if(Music.isChecked()==false){
					music = false;
					MyQuestionActivity_ang.putExtra(music);
					MyQuestionActivity_pl.putExtra(music);
					MyQuestionActivity_fr.putExtra(music);
					SummaryActivity_ang.putExtra(music);
					SummaryActivity_pl.putExtra(music);
					SummaryActivity_fr.putExtra(music);
					Option.putExtra(false);
				}	
				
			}
		});
	}
}
