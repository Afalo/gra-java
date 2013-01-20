package com.javagame;

import com.javagame.utils.StaticHelper;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends Activity {
	ImageButton addImage;
	EditText pl;
	EditText fr;
	EditText en;
	Button add;
	public Cursor getMediaCursor() {
		    Uri uri = Media.EXTERNAL_CONTENT_URI;
		    String[] projection = { Media._ID };
		    String selectionArgs = Integer.toString(0);
		    String sortOrder = Media.DISPLAY_NAME + " ASC";
		    return Media.query(getContentResolver(), uri, projection, selectionArgs, sortOrder);
		}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_add);
		addImage = (ImageButton) findViewById(R.id.addImage);
		pl = (EditText) findViewById(R.id.addPl);
		fr = (EditText) findViewById(R.id.addFr);
		en = (EditText) findViewById(R.id.addEn);
		add = (Button) findViewById(R.id.addToDataBase);
		
		addImage.setOnClickListener(new OnClickListener() {

			public void onClick(View v){
				getMediaCursor();
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
				Intent i = new Intent(getApplication(), MainActivity_pl.class);
				i.putExtra(StaticHelper.FLAG_LANG_VERSION, StaticHelper.LANG_VERSION_PL);
				startActivity(i);
				System.gc();
				finish();

			}
		});
		add.setOnClickListener(new OnClickListener() {

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
