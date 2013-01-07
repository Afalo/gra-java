package com.javagame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Start extends Activity {

    @Override
    public void onCreate(Bundle Start) {
        super.onCreate(Start);
        setContentView(R.layout.activity_start);
        Handler h = new Handler();
		h.postDelayed(new SplashHandler(), 2000);
    }

	class SplashHandler implements Runnable {
		public void run() {
			startActivity(new Intent(getApplication(), JavaGame.class));
			System.gc();
			Start.this.finish();
		}
	}
}
