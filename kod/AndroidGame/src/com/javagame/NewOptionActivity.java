package com.javagame;

import com.javagame.db.DatabaseAdapter;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View.OnClickListener;

public class NewOptionActivity extends Activity {
	
	public static final int REQUEST_SAVE = 101;
	
	public static final int REQUEST_LOAD = 2;
	
	Button buttonBack;
	Button searchFile;
	Button save;
	TextView pathFile;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (1);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_new_option);
		
		buttonBack = (Button) findViewById(R.id.buttonAddOptionBack);
		searchFile = (Button) findViewById(R.id.searchFile);
		save = (Button) findViewById(R.id.buttonSaveFile);
		
		pathFile = (TextView) findViewById(R.id.pathFile);
		
		save.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				String textPl = ((EditText) findViewById(R.id.editTextPl)).getText().toString();
				String textEn = ((EditText) findViewById(R.id.editTextEn)).getText().toString();
				String textFr = ((EditText) findViewById(R.id.editTextFr)).getText().toString();
				
				if (textPl.equals("") || textFr.equals("") || textEn.equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(), "Wszystkie pola musza byc wypelnione", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				
				String path = pathFile.getText().toString();
				if (!path.startsWith("/")) {
					Toast toast = Toast.makeText(getApplicationContext(), "Brak wybranego pliku", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				
				DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext());
				databaseAdapter.open(getResources());
				
				boolean saved = databaseAdapter.savePictureWithAnswers(path, textPl, textEn, textFr);
								
				databaseAdapter.close();
				if (saved) {
					Toast toast = Toast.makeText(getApplicationContext(), "Wpis wykonano z powodzeniem", Toast.LENGTH_SHORT);
					toast.show();
					
					
					startActivity(new Intent(getApplication(), SettingsActivityMain.class));
					System.gc();
					finish();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), "Problem z zapisniem danych", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
			}
		});
		
		searchFile.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(), FileDialogActivity.class);
				intent.putExtra(FileDialogActivity.START_PATH, "/sdcard");
                                                
                //alternatively you can set file filter, display only png and jpg
                intent.putExtra(FileDialogActivity.FORMAT_FILTER, new String[] { "png", "jpg" });
                
                startActivityForResult(intent, REQUEST_SAVE);
			}
		});
		
		buttonBack.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				startActivity(new Intent(getApplication(), SettingsActivityMain.class));
				System.gc();
				finish();
			}
		});
		
		
	}
	
	@Override
	public synchronized void onActivityResult(final int requestCode, int resultCode, final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {

			if (requestCode == REQUEST_SAVE) {
				System.out.println("Saving...");
			} else if (requestCode == REQUEST_LOAD) {
				System.out.println("Loading...");
			}

			String filePath = data
					.getStringExtra(FileDialogActivity.RESULT_PATH);
			pathFile.setText(filePath);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_option, menu);
		return true;
	}

}
