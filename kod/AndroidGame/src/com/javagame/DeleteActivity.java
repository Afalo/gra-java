package com.javagame;

import com.javagame.utils.StaticHelper;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class DeleteActivity extends Activity {
	SearchView search;
	ImageView image;
	TextView pl;
	TextView fr;
	TextView en;
	Button delete;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_delete);
		search = (SearchView) findViewById(R.id.searchDelete);
		image = (ImageView) findViewById(R.id.image_delete);
		pl = (TextView) findViewById(R.id.text_pl);
		fr = (TextView) findViewById(R.id.text_fr);
		en = (TextView) findViewById(R.id.text_en);
		delete = (Button) findViewById(R.id.button_delete);
		
		search.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), MainActivity_pl.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});
		image.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), MainActivity_pl.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});
		pl.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), MainActivity_pl.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});
		fr.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), MainActivity_pl.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});
		en.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), Option.class);
				//i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});
		delete.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				Intent i = new Intent(getApplication(), Option.class);
				//i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});
	}
}
