package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.MainActivity_ang;

public class MainActivity_AngTest extends ActivityInstrumentationTestCase2<MainActivity_ang> {
	 private Solo solo;
	  
	  public MainActivity_AngTest() {
		  super(MainActivity_ang.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng() throws Exception {
		solo.clickOnImageButton(0); 
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
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

