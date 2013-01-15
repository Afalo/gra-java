package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.MyQuestionActivity_pl;

public class MyQuestionActivity_PlTest extends ActivityInstrumentationTestCase2<MyQuestionActivity_pl> {
	 private Solo solo;
	  
	  public MyQuestionActivity_PlTest() {
		  super(MyQuestionActivity_pl.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  //Test checking if the button exists
	  public void testButtonPl() throws Exception {
			 solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
			  assertTrue(solo.searchText("Dalej"));
		 }
		   
		 // Test pressing a button.
		 public void testClickButtonPl1() throws Exception {
			 solo.clickOnRadioButton(2);
		   solo.clickOnButton("Dalej");
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
		   solo.finishOpenedActivities();
		 }
		
		 //Test going through the whole one game
		 public void testClickButtonPlD() throws Exception {
			 	for(int i=1; i<10; i++){
			 		solo.clickOnRadioButton(2);
			 		   solo.clickOnButton("Dalej");
					   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
			 	}
			 	solo.clickOnRadioButton(2);
			 	solo.clickOnButton("Dalej");
			 	solo.assertCurrentActivity("Expected activity", "SummaryActivity_pl");
				solo.finishOpenedActivities();
		 }
	
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonPl0() throws Exception {
		   solo.clickOnRadioButton(0);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonPl1() throws Exception {
		   solo.clickOnRadioButton(1);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonPl2() throws Exception {
		   solo.clickOnRadioButton(2);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonPl3() throws Exception {
		   solo.clickOnRadioButton(3);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_pl");
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

