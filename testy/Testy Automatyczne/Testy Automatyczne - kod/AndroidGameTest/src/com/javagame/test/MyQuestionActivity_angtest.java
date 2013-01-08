package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.MyQuestionActivity_ang;

public class MyQuestionActivity_angtest extends ActivityInstrumentationTestCase2<MyQuestionActivity_ang> {
	 private Solo solo;
	  
	  @SuppressWarnings("deprecation")
	  public MyQuestionActivity_angtest() {
		  super("com.javagame", MyQuestionActivity_ang.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  //Test checking if the button exists
	  public void testButtonAng() throws Exception {
			 solo.assertCurrentActivity("Expected activity", "Choose");
			  assertTrue(solo.searchText("Dalej"));
		 }
		   
		 // Test pressing a button.
		 public void testClickButtonAng1() throws Exception {
		   solo.clickOnButton("Dalej");
		   solo.assertCurrentActivity("Expected activity", "MainActivity_ang");
		   solo.finishOpenedActivities();

		 }
		 
	/*  // Test pressing a button.
	  public void testClickButtonAng2() throws Exception {
		solo.clickOnImageButton(0); 
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng3() throws Exception {
		solo.clickOnImageButton(1); 
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng4() throws Exception {
		solo.clickOnImageButton(2); 
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
	  }
	  
	  // Test pressing a button.
	  public void testClickButtonAng() throws Exception {
		solo.clickOnImageButton(3); 
	    solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
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
