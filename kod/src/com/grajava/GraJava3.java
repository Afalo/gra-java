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

public class GraJava3 extends Activity {
	int a;
	TextView suma;
	public int sumowanie(int a){
		a=a+a;
		return a;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran3);
        suma = (TextView) findViewById(R.id.suma);
        suma.setText("Suma punktów = w trakcie pracy nad sumowaniem");
    }
}
