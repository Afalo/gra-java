package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.Choose;

public class ChooseTest extends ActivityInstrumentationTestCase2<Choose> {
	 private Solo solo;
	  
	  
	  public ChooseTest() {
		  super(Choose.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  public void testButtonAng() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "Choose");
		  assertTrue(solo.searchText("ENGLISH"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonAng() throws Exception {
	    solo.clickOnButton("ENGLISH");
	    solo.assertCurrentActivity("Expected activity", "MainActivity_ang");
	    solo.finishOpenedActivities();

	  }
	  
	//Check if there's a proper button.
	 public void testButtonPl() throws Exception {
		 solo.assertCurrentActivity("Expected activity", "Choose");
		  assertTrue(solo.searchText("POLSKI"));
	 }
	   
	 // Test pressing a button.
	 public void testClickButtonPl() throws Exception {
	   solo.clickOnButton("POLSKI");
	   solo.assertCurrentActivity("Expected activity", "MainActivity_pl");
	   solo.finishOpenedActivities();

	 }
	 
	 
	// Check if there's an proper button.
		  public void testButtonFr() throws Exception {
			  solo.assertCurrentActivity("Expected activity", "Choose");
			  assertTrue(solo.searchText("FRANCE"));
			  
		  }
		    
		  // Test pressing a button.
		  public void testClickButtonFr() throws Exception {
		    solo.clickOnButton("FRANCE");
		    solo.assertCurrentActivity("Expected activity", "MainActivity_fr");
		    solo.finishOpenedActivities();
		  }
		  
		// Check if there's a GetBack button.
		  public void testButtonCof() throws Exception {
			  solo.assertCurrentActivity("Expected activity", "Choose");
			  assertTrue(solo.searchText("COFNIJ"));
			  
		  }
		    
		  // Test pressing a button.
		  public void testClickButtonCof() throws Exception {
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

