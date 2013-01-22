package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.AutorActivity;

public class AutorActivityTest extends ActivityInstrumentationTestCase2<AutorActivity> {
	 private Solo solo;
	  
	  public AutorActivityTest() {
		  super(AutorActivity.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  public void testButtonMenu() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "AutorActivity");
		  assertTrue(solo.searchText("COFNIJ"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonMenu() throws Exception {
	    solo.clickOnButton("COFNIJ");
	    solo.assertCurrentActivity("Expected activity", "JavaGame");
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

