package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.SettingsActivityMain;

public class SettingsActivityMainTest extends ActivityInstrumentationTestCase2<SettingsActivityMain> {
	 private Solo solo;
	  
	  
	  public SettingsActivityMainTest() {
		  super(SettingsActivityMain.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
		//Check if there's a proper button.
	  public void testButtonAdd() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "SettingsActivityMain");
		  assertTrue(solo.searchText("Dodaj"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonAdd() throws Exception {
	    solo.clickOnButton("Dodaj");
	    solo.assertCurrentActivity("Expected activity", "NewOptionActivity");
	    solo.finishOpenedActivities();

	  }
	 
	//Check if there's a proper button.
	 public void testButtonDelete() throws Exception {
		 solo.assertCurrentActivity("Expected activity", "SettingsActivityMain");
		  assertTrue(solo.searchText("Usuñ wpis"));
	 }
	   
	 // Test pressing a button.
	 public void testClickButtonPl() throws Exception {
	   solo.clickOnButton("Usuñ wpis");
	   solo.assertCurrentActivity("Expected activity", "DeleteOptionActivity");
	   solo.finishOpenedActivities();

	 }
	 
		  
		// Check if there's a GetBack button.
		  public void testButtonCof() throws Exception {
			  solo.assertCurrentActivity("Expected activity", "SettingsActivityMain");
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


