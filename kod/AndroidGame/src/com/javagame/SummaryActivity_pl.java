package com.javagame;

import com.javagame.utils.StaticHelper;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity_pl extends Activity {

	MediaPlayer mp;
	int questionCount = 0;
	int goodQuestionCount = 0;
	static boolean music=true;
	
	public static void putExtra(boolean yes){
		music=yes;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (1);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_summary_pl);

		questionCount = getIntent().getIntExtra(StaticHelper.FLAG_QUEST_AMOUNT, 0);
		goodQuestionCount = getIntent().getIntExtra(StaticHelper.FLAG_GOOD_QUEST_AMOUNT, 0);

		TextView info = (TextView) findViewById(R.id.textView2);
		String newInfo = getResources().getText(R.string.goodQuestionCount_pl) + " " + goodQuestionCount + "/" + questionCount + " " + getResources().getText(R.string.questions_pl);
		info.setText(newInfo);
		if(music==true){
			mp = MediaPlayer.create(SummaryActivity_pl.this, R.raw.pl);
			mp.start();
		}
		if(goodQuestionCount<=5){
			TextView info1 = (TextView) findViewById(R.id.textView1);
			String newInfo1 = "NIESTETY...";
			info1.setText(newInfo1);
		}
		else{
			TextView info2 = (TextView) findViewById(R.id.textView1);
			String newInfo2 = "GRATULACJE!!";
			info2.setText(newInfo2);
		}

		Button backMenu = (Button) findViewById(R.id.menu);
		backMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mp!=null){
					mp.release();
				}
				startActivity(new Intent(getApplication(), Choose.class));
				System.gc();
				finish();
			}
		});

	}
}
