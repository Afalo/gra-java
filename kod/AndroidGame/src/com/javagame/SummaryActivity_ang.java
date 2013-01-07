package com.javagame;

import com.javagame.utils.StaticHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity_ang extends Activity {

	int questionCount = 0;
	int goodQuestionCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary_ang);

		questionCount = getIntent().getIntExtra(StaticHelper.FLAG_QUEST_AMOUNT, 0);
		goodQuestionCount = getIntent().getIntExtra(StaticHelper.FLAG_GOOD_QUEST_AMOUNT, 0);

		TextView info = (TextView) findViewById(R.id.textView2);
		String newInfo = getResources().getText(R.string.goodQuestionCount_ang) + " " + goodQuestionCount + "/" + questionCount + " " + getResources().getText(R.string.question_ang);
		info.setText(newInfo);
		if(goodQuestionCount<=5){
			TextView info1 = (TextView) findViewById(R.id.textView1);
			String newInfo1 = "UNFORTUNATELY...";
			info1.setText(newInfo1);
		}
		else{
			TextView info2 = (TextView) findViewById(R.id.textView1);
			String newInfo2 = "CONGRATULATIONS!!";
			info2.setText(newInfo2);
		}

		Button backMenu = (Button) findViewById(R.id.menu);
		backMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplication(), Choose.class));
				System.gc();
				finish();
			}
		});

	}
}
