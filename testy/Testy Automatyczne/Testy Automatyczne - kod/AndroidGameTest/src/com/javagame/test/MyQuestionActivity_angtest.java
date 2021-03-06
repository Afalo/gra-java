package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;

import android.media.MediaPlayer;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.MyQuestionActivity_ang;

public class MyQuestionActivity_AngTest extends ActivityInstrumentationTestCase2<MyQuestionActivity_ang> {
	 private Solo solo;
	  
	  public MyQuestionActivity_AngTest() {
		  super(MyQuestionActivity_ang.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  //Test checking if the button exists
	  public void testButtonAng() throws Exception {
			 solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
			  assertTrue(solo.searchText("Dalej"));
		 }
		   
		 // Test pressing a button.
		 public void testClickButtonAng1() throws Exception {
			solo.clickOnRadioButton(2);
		   solo.clickOnButton("Dalej");
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
		   solo.finishOpenedActivities();
		 }
		
		 //Test going through the whole one game
		 public void testClickButtonAngD() throws Exception {
			 	for(int i=1; i<10; i++){
			 			solo.clickOnRadioButton(2);
			 		   solo.clickOnButton("Dalej");
			 		   solo.waitForActivity("MyQuestionActivity_ang");
					   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
			 	}
			 	solo.clickOnRadioButton(2);
			 	solo.clickOnButton("Dalej");
			 	solo.assertCurrentActivity("Expected activity", "SummaryActivity_ang");
				solo.finishOpenedActivities();
		 }
	
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonAng0() throws Exception {
		   solo.clickOnRadioButton(0);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonAng1() throws Exception {
		   solo.clickOnRadioButton(1);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonAng2() throws Exception {
		   solo.clickOnRadioButton(2);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonAng3() throws Exception {
		   solo.clickOnRadioButton(3);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_ang");
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

