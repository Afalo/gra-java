package com.javagame;

import com.javagame.utils.StaticHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;



public class MainActivity_ang extends Activity {

	private int questionsAmount = 10;

	ImageButton imagebutton;
	
	String langVersion = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ang);
		
		imagebutton = (ImageButton) findViewById(R.id.ang);
		imagebutton.setOnClickListener(new ClickListener());

		langVersion = getIntent().getStringExtra(StaticHelper.FLAG_LANG_VERSION);
	}

	class ClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// move data to other activity
			Intent i = new Intent(getApplication(), MyQuestionActivity_ang.class);
			i.putExtra(StaticHelper.FLAG_QUEST_AMOUNT, questionsAmount);
			i.putExtra(StaticHelper.FLAG_LANG_VERSION, langVersion);

			//forward to activity
			startActivity(i);
			System.gc();
			finish();
		}

	}
}
