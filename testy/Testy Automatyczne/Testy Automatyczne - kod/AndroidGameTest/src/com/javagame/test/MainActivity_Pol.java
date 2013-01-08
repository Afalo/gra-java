package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.*;
import com.javagame.db.DatabaseAdapter;
import com.javagame.utils.StaticHelper;



public class MainActivity_Pol extends ActivityInstrumentationTestCase2<MainActivity_pl> {
	 private Solo solo;
	  DatabaseAdapter db = new DatabaseAdapter(null);
	  StaticHelper sh = new StaticHelper();
	  public MainActivity_Pol() {
		  super(MainActivity_pl.class);
		  
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonPl() throws Exception {
		solo.clickOnImageButton(0); 
		db.open(null);
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
	    solo.finishOpenedActivities();
	  }
	  
	  @Override
	  public void tearDown() throws Exception {

	    try {
	      solo.finalize();
	    } catch (Throwable e) {

	      e.printStackTrace();
	    }
	    getActivity().finish();
	    super.tearDown();

	  }
}
