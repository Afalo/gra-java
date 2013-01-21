package com.javagame;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;

public class SettingsActivityMain extends Activity {
	Button buttonNew;
	Button buttonDelete;
	Button buttonBack;
	Switch Music;
	boolean music;
	static boolean ustaw=true;
	
	protected static void putExtra(boolean b) {
		ustaw = b;	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (1);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_settings_activity_main);
		
		buttonNew = (Button) findViewById(R.id.buttonNewOption);
		buttonDelete = (Button) findViewById(R.id.buttonDeleteOption);
		buttonBack = (Button) findViewById(R.id.buttonBackOption);
		Music = (Switch) findViewById(R.id.music);
		
		Music.setChecked(ustaw);
		
		buttonDelete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(new Intent(getApplication(), DeleteOptionActivity.class));
				System.gc();
				finish();
			}
		});
		
		buttonNew.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(new Intent(getApplication(), NewOptionActivity.class));
				System.gc();
				finish();
			}
		});
		
		buttonBack.setOnClickListener(new OnClickListener() {

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
					SettingsActivityMain.putExtra(true);
				}
				if(Music.isChecked()==false){
					music = false;
					MyQuestionActivity_ang.putExtra(music);
					MyQuestionActivity_pl.putExtra(music);
					MyQuestionActivity_fr.putExtra(music);
					SummaryActivity_ang.putExtra(music);
					SummaryActivity_pl.putExtra(music);
					SummaryActivity_fr.putExtra(music);
					SettingsActivityMain.putExtra(false);
				}	
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings_activity_main, menu);
		return true;
	}

}
