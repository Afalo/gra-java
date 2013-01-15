package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.SummaryActivity_ang;

public class SummaryActivity_angTest extends ActivityInstrumentationTestCase2<SummaryActivity_ang> {
	 private Solo solo;
	  
	  public SummaryActivity_angTest() {
		  super(SummaryActivity_ang.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  public void testButtonMenu() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "SummaryActivity_ang");
		  assertTrue(solo.searchText("Menu"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonMenu() throws Exception {
	    solo.clickOnButton("Menu");
	    solo.assertCurrentActivity("Expected activity", "Choose");
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

