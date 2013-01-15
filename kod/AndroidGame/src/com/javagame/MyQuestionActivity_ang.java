package com.javagame;

import java.util.ArrayList;
import java.util.List;

import com.javagame.Start.SplashHandler;
import com.javagame.db.DatabaseAdapter;
import com.javagame.db.model.AnswerModel;
import com.javagame.db.model.PictureModel;
import com.javagame.exception.NoPictureNextException;
import com.javagame.exception.ObjectNotFoundException;
import com.javagame.utils.StaticHelper;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MyQuestionActivity_ang extends Activity {

	Button next = null;
	RadioGroup answers = null;
	RadioButton a1 = null;
	RadioButton a2 = null;
	RadioButton a3 = null;
	RadioButton a4 = null;
	ImageView image = null;
	MediaPlayer mp;
	String info;
	static boolean music=true;;
	
	public static void putExtra(boolean yes){
		music=yes;
	}
	
	private int goodAnswerId = -1;
	private int goodAnswerCounter = 0;
	private int questionCount = 0;
	private int currentQuestion = 1;

	/** Picture id to */
	private List<Long> displayedPictureId = null;

	long counter = 1;

	DatabaseAdapter databaseAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (1);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.activity_my_question);

		next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new ClickNextListener());

		answers = (RadioGroup) findViewById(R.id.answers);
		a1 = (RadioButton) findViewById(R.id.a1);
		a2 = (RadioButton) findViewById(R.id.a2);
		a3 = (RadioButton) findViewById(R.id.a3);
		a4 = (RadioButton) findViewById(R.id.a4);

		image = (ImageView) findViewById(R.id.image);

		questionCount = getIntent().getIntExtra(StaticHelper.FLAG_QUEST_AMOUNT, 10);
		displayedPictureId = new ArrayList<Long>();

		setView();
	}

	class ClickNextListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int checkedId = answers.getCheckedRadioButtonId();
			if(checkedId != -1) {
				int selectedPosition = answers.indexOfChild(findViewById(checkedId));
				
				if (selectedPosition == goodAnswerId) {
					mp = MediaPlayer.create(MyQuestionActivity_ang.this, R.raw.good);
					goodAnswerCounter++;
					info = "Correct Answer. All points: " + goodAnswerCounter;
				}
				else{
					mp = MediaPlayer.create(MyQuestionActivity_ang.this, R.raw.wrong);
					info = "Wrong Answer";
				}
				if(music==true){
				if(mp!=null){
					mp.start();
				}
				}
				
				Toast toast = Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT);
				toast.show();
				do{
					
				}while(mp.isPlaying()==true);
				if (currentQuestion < questionCount) {
					currentQuestion++;
					setView();
				} else {
					Intent i = new Intent(getApplication(), SummaryActivity_ang.class);
					i.putExtra(StaticHelper.FLAG_QUEST_AMOUNT, questionCount);
					i.putExtra(StaticHelper.FLAG_GOOD_QUEST_AMOUNT, goodAnswerCounter);

					//forward to activity
					mp.release();
					startActivity(i);
					System.gc();
					finish();
				}
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), "No Answer", Toast.LENGTH_SHORT);
				toast.show();
			}
			mp.release();
		}

	}


	private void setView() {

		try {
			databaseAdapter = new DatabaseAdapter(getApplicationContext());

			databaseAdapter.open(getResources());

			PictureModel pictureModel = databaseAdapter.getRandomPicture(displayedPictureId);
			Log.d("setView", "Image Id: " + pictureModel.getId());
			Log.d("setView", "Image size: " + pictureModel.getPicture().length);
			displayedPictureId.add(pictureModel.getId());
			Bitmap bitmap = BitmapFactory.decodeByteArray(pictureModel.getPicture(), 0, pictureModel.getPicture().length);
			this.image.setImageBitmap(bitmap);

			AnswerModel[] ansersDb = databaseAdapter.get4AnswersForPicture(pictureModel.getId(), StaticHelper.LANG_VERSION_EN);

			checkGoodAnswer(ansersDb[0], 0);
			this.a1.setText(ansersDb[0].getDescription());
			checkGoodAnswer(ansersDb[1], 1);
			this.a2.setText(ansersDb[1].getDescription());
			checkGoodAnswer(ansersDb[2], 2);
			this.a3.setText(ansersDb[2].getDescription());
			checkGoodAnswer(ansersDb[3], 3);
			this.a4.setText(ansersDb[3].getDescription());

			answers.clearCheck();

			counter++;

		} catch (ObjectNotFoundException e) {
			Toast toast = Toast.makeText(getApplicationContext(),
					e.getMessage(), Toast.LENGTH_LONG);
			toast.show();
		} catch (NoPictureNextException e) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Brak kolejenego oberazka", Toast.LENGTH_LONG);
			toast.show();

			Intent i = new Intent(getApplication(), SummaryActivity_ang.class);
			i.putExtra(StaticHelper.FLAG_QUEST_AMOUNT, questionCount);
			i.putExtra(StaticHelper.FLAG_GOOD_QUEST_AMOUNT, goodAnswerCounter);

			//forward to activity
			startActivity(i);
			System.gc();
			finish();

		} finally {
			databaseAdapter.close();
		}

	}

	private void checkGoodAnswer(AnswerModel answer, int position) {
		if(answer.isGoodAnswer()) {
			goodAnswerId = position;
		}
	}

	@Override
	protected void onDestroy() {
	    if(databaseAdapter != null)
	    	databaseAdapter.close();
	    super.onDestroy();
	}

	

}
