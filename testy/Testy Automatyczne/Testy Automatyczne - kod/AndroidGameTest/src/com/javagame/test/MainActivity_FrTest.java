package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.MainActivity_fr;

public class MainActivity_FrTest extends ActivityInstrumentationTestCase2<MainActivity_fr> {
	 private Solo solo;
	  
	  public MainActivity_FrTest() {
		  super(MainActivity_fr.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng() throws Exception {
		solo.clickOnImageButton(0); 
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
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

