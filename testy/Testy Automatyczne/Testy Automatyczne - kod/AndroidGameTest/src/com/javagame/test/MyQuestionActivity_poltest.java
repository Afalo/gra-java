package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.MyQuestionActivity_pl;

public class MyQuestionActivity_poltest extends ActivityInstrumentationTestCase2<MyQuestionActivity_pl> {
	 private Solo solo;
	  
	  @SuppressWarnings("deprecation")
	  public MyQuestionActivity_poltest() {
		  super("com.javagame", MyQuestionActivity_pl.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng() throws Exception {
		solo.clickOnImageButton(0); 
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
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
