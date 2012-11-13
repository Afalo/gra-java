package com.grajava;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GraJava2a extends Activity {
	
	TextView Minus;
	Button Ok;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran2a);
    
        Minus = (TextView) findViewById(R.id.Minus);
		Ok = (Button) findViewById(R.id.Ok);
		Ok.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
        			finish();
				}
			});
        
    }
}
