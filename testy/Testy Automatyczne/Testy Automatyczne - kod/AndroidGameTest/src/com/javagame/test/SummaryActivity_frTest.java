package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.SummaryActivity_fr;

public class SummaryActivity_frTest extends ActivityInstrumentationTestCase2<SummaryActivity_fr> {
	 private Solo solo;
	  
	  @SuppressWarnings("deprecation")
	  public SummaryActivity_frTest() {
		  super("com.javagame", SummaryActivity_fr.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng() throws Exception {
		solo.clickOnImageButton(0); 
	    solo.assertCurrentActivity("Expected activity", "SummaryActivity_fr");
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
