package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.SummaryActivity_ang;

public class SummaryActivity_angTest extends ActivityInstrumentationTestCase2<SummaryActivity_ang> {
	 private Solo solo;
	  
	  @SuppressWarnings("deprecation")
	  public SummaryActivity_angTest() {
		  super("com.javagame", SummaryActivity_ang.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng() throws Exception {
		solo.clickOnImageButton(0); 
	    solo.assertCurrentActivity("Expected activity", "SummaryActivity_ang");
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
