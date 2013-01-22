package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

import com.javagame.DeleteOptionActivity;


public class DeleteOptionActivityTest extends ActivityInstrumentationTestCase2<DeleteOptionActivity> {
	 private Solo solo;
   
	 
	  public DeleteOptionActivityTest() {
		  super(DeleteOptionActivity.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
		//Check if there's a proper button.
	  public void testButtonBack() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "DeleteOptionActivity");
		  assertTrue(solo.searchText("COFNIJ"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonBack() throws Exception {
	    solo.clickOnButton("COFNIJ");
	    solo.assertCurrentActivity("Expected activity", "SettingsActivityMain");
	    solo.finishOpenedActivities();

	  }
		//Check if there's a proper button.
	  public void testButtonDelete() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "DeleteOptionActivity");
		  assertTrue(solo.searchText("Usuñ"));
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
