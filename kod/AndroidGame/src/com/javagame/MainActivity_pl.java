package com.javagame;

import com.javagame.utils.StaticHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;



public class MainActivity_pl extends Activity {

	private int questionsAmount = 10;

	ImageButton imagebutton;
	
	String langVersion = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_pl);
		
		imagebutton = (ImageButton) findViewById(R.id.pl);
		imagebutton.setOnClickListener(new ClickListener());

		langVersion = getIntent().getStringExtra(StaticHelper.FLAG_LANG_VERSION);
	}

	class ClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// move data to other activity
			Intent i = new Intent(getApplication(), MyQuestionActivity_pl.class);
			i.putExtra(StaticHelper.FLAG_QUEST_AMOUNT, questionsAmount);
			i.putExtra(StaticHelper.FLAG_LANG_VERSION, langVersion);

			//forward to activity
			startActivity(i);
			System.gc();
			finish();
		}

	}
}