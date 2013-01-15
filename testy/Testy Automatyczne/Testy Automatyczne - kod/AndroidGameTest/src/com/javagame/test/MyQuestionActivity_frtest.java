package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.javagame.MyQuestionActivity_fr;

public class MyQuestionActivity_FrTest extends ActivityInstrumentationTestCase2<MyQuestionActivity_fr> {
	 private Solo solo;
	  
	  public MyQuestionActivity_FrTest() {
		  super(MyQuestionActivity_fr.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
	  //Test checking if the button exists
	  public void testButtonFr() throws Exception {
			 solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
			  assertTrue(solo.searchText("Dalej"));
		 }
		   
		 // Test pressing a button.
		 public void testClickButtonFr1() throws Exception {
			 solo.clickOnRadioButton(2);
		   solo.clickOnButton("Dalej");
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
		   solo.finishOpenedActivities();
		 }
		
		 //Test going through the whole one game
		 public void testClickButtonFrD() throws Exception {
			 	for(int i=1; i<10; i++){
			 		solo.clickOnRadioButton(2);
			 		   solo.clickOnButton("Dalej");
					   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
			 	}
			 	solo.clickOnRadioButton(2);
			 	solo.clickOnButton("Dalej");
			 	solo.assertCurrentActivity("Expected activity", "SummaryActivity_fr");
				solo.finishOpenedActivities();
		 }
	
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonFr0() throws Exception {
		   solo.clickOnRadioButton(0);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonFr1() throws Exception {
		   solo.clickOnRadioButton(1);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonFr2() throws Exception {
		   solo.clickOnRadioButton(2);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
		   solo.finishOpenedActivities();
		 }
		 
		 // Test pressing a radio button with an answear.
		 public void testClickRButtonFr3() throws Exception {
		   solo.clickOnRadioButton(3);
		   solo.assertCurrentActivity("Expected activity", "MyQuestionActivity_fr");
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

