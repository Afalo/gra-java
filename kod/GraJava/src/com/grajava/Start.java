package com.grajava;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Start extends Activity {
	
    @Override
    public void onCreate(Bundle Start) {
        super.onCreate(Start);
        setContentView(R.layout.s);
        Handler h = new Handler();
		h.postDelayed(new SplashHandler(), 2000);
    }
    
    class SplashHandler implements Runnable{
				public void run() {
					startActivity(
							new Intent(getApplication(), GraJava.class));
					System.gc();
					Start.this.finish();
				}
    }
}
