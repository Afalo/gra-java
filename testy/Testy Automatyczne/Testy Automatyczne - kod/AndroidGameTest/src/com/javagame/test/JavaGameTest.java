package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.JavaGame;

public class JavaGameTest extends ActivityInstrumentationTestCase2<JavaGame> {
	 private Solo solo;
	  
	  public JavaGameTest() {
		  super(JavaGame.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  // Test pressing a button.
	  public void testButtonStart() throws Exception {
		    solo.assertCurrentActivity("Expected activity", "JavaGame");
		    assertTrue(solo.searchText("START"));
		  
	  }
	    
	  // Test pressing a Start button.
	  public void testClickButtonStart() throws Exception {
	    solo.clickOnButton("START");
	    solo.assertCurrentActivity("Expected activity", "Choose");
	    solo.finishOpenedActivities();

	  }
	  
	// Check if there's an Option button.
	  public void testButtonOption() throws Exception {
		solo.assertCurrentActivity("Expected activity", "JavaGame");
		assertTrue(solo.searchText("OPCJE"));
	  }
		    
	  // Test pressing an Option button.
	  public void testClickButtonOption() throws Exception {
		solo.clickOnButton("OPCJE");
		solo.assertCurrentActivity("Expected activity", "Option");
		solo.finishOpenedActivities();
	  }
		  
		// Check if there's an Exit button.
	  public void testButtonWyjscie() throws Exception {
		solo.assertCurrentActivity("Expected activity", "JavaGame");
		assertTrue(solo.searchText("WYJŒCIE"));
	  }
		    
	// Test pressing an exit button.
	  /*public void testClickButtonWyjscie() throws Exception {
		solo.clickOnButton("WYJï¿½CIE");
		solo.assertCurrentActivity("Expected finish", "");
	  }*/
	  
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

