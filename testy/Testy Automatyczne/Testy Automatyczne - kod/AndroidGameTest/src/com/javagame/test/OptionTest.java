package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.Option;

public class OptionTest extends ActivityInstrumentationTestCase2<Option> {
	 private Solo solo;
	  
	  
	  public OptionTest() {
		  super(Option.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	/*	//Check if there's a proper button.
	  public void testButtonAdd() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "Option");
		  assertTrue(solo.searchText("Dodaj s³owo"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonAdd() throws Exception {
	    solo.clickOnButton("Dodaj s³owo");
	    solo.assertCurrentActivity("Expected activity", "AddActivity");
	    solo.finishOpenedActivities();

	  }
	  
	//Check if there's a proper button.
	 public void testButtonDelete() throws Exception {
		 solo.assertCurrentActivity("Expected activity", "Option");
		  assertTrue(solo.searchText("Usuñ s³owo"));
	 }
	   
	 // Test pressing a button.
	 public void testClickButtonPl() throws Exception {
	   solo.clickOnButton("Usuñ s³owo");
	   solo.assertCurrentActivity("Expected activity", "DeleteActivity");
	   solo.finishOpenedActivities();

	 }*/
	 
		  
		// Check if there's a GetBack button.
		  public void testButtonCof() throws Exception {
			  solo.assertCurrentActivity("Expected activity", "Option");
			  assertTrue(solo.searchText("COFNIJ"));
			  
		  }
		    
		  // Test pressing a button.
		  public void testClickButtonCof() throws Exception {
		    solo.clickOnButton("COFNIJ");
		    solo.assertCurrentActivity("Expected activity", "JavaGame");
		    solo.finishOpenedActivities();
		  }
		  
		  // Test checking a button responsible for playing music.
		 /*  public void testToggleButton() throws Exception {
			  solo.waitForActivity("Option");
  
			  //search for toggle button
			 solo.searchToggleButton("Muzyka");
			  assertEquals("Toggle Button not found",true);
  
			  //Turn ON toggle button
			  solo.clickOnToggleButton("OFF");
			  solo.isToggleButtonChecked(0);
			  assertEquals("Toggle Button is OFF",true);
		  
			  //Turn OFF toggle button
			  solo.clickOnToggleButton("ON");
			  solo.isToggleButtonChecked(0);
			  assertEquals("Toggle Button is ON",false);
			  
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


