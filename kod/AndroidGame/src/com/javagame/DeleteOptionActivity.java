package com.javagame;

import java.util.ArrayList;
import java.util.List;

import com.javagame.db.DatabaseAdapter;
import com.javagame.db.model.AnswerModel;
import com.javagame.utils.StaticHelper;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteOptionActivity extends Activity {

	private List<AnswerModel> answers;
	private int positionSelected = -1;
	
	Button buttonBack;
	Button buttonDelete;
	TextView textInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (1);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_delete_option);
		
		buttonBack = (Button) findViewById(R.id.buttonBackREmoveList);
		buttonDelete = (Button) findViewById(R.id.buttonDeleteOptionDelete);
		buttonDelete.setEnabled(false);
		textInfo = (TextView) findViewById(R.id.textSelectedToDelete);
				
		ListView listView = (ListView) findViewById(R.id.listAnswersPL);
		
		DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext());
		databaseAdapter.open(getResources());
		
		answers = databaseAdapter.getAllAnswerByLang(StaticHelper.LANG_VERSION_PL);
						
		databaseAdapter.close();
		
		String[] values = new String[answers.size()];
		int counter = 0;
		for(AnswerModel model : answers) {
			values[counter] = model.getDescription();
			counter++;
		}		
		
		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		  android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				positionSelected = position;
				buttonDelete.setEnabled(true);
				textInfo.setText("Czy usunac: " + answers.get(position).getDescription());
								
			}
			}); 
		
		buttonDelete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (positionSelected < 0) {
					Toast toast = Toast.makeText(getApplicationContext(), "Nie wybrano pozycji", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				
				Long pictureId = answers.get(positionSelected).getPictureId();
				String info = "";
				
				DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext());
				databaseAdapter.open(getResources());
				if (pictureId > 0) {
					// remove picture and all answers for this picture					
										
					
					if (databaseAdapter.deletePictureAndAnswers(pictureId)) {
						info = "Usunieto obrazek z odpowiedziami";						
					} else {
						info = "Problem z usunieciem obrazka z odpowiedziami";
					}
										
				} else {
					// remove empty answer
					Long answerId = answers.get(positionSelected).getId();
					if(databaseAdapter.deleteAnswer(answerId)) {
						info = "Usunieto odpowiedz";
					} else {
						info = "Problem z usunieciem odpowiedzi";
					}
					
				}
				databaseAdapter.close();
				
				Toast toast = Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT);
				toast.show();
				
				startActivity(new Intent(getApplication(), SettingsActivityMain.class));
				System.gc();
				finish();
				
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_delete_option, menu);
		return true;
	}

}
