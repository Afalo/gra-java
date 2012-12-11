package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grajava.wybor;
import com.jayway.android.robotium.solo.Solo;


public class TestMenuJezykow extends ActivityInstrumentationTestCase2<wybor>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestMenuJezykow() {
          super("com.grajava", wybor.class);
  }

  @Override
public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
  // Check if there's a proper button.
  public void testButtonAng() throws Exception {
	  solo.assertCurrentActivity("Expected activity", "wybor");
	  assertTrue(solo.searchText("ENGLISH"));
  }
    
  // Test pressing a button.
  public void testClickButtonAng() throws Exception {
    solo.clickOnButton("ENGLISH");
    solo.assertCurrentActivity("Expected activity", "angielski1");
    solo.finishOpenedActivities();

  }
  
//Check if there's a proper button.
 public void testButtonPl() throws Exception {
	 solo.assertCurrentActivity("Expected activity", "wybor");
	  assertTrue(solo.searchText("POLSKI"));
 }
   
 // Test pressing a button.
 public void testClickButtonPl() throws Exception {
   solo.clickOnButton("POLSKI");
   solo.assertCurrentActivity("Expected activity", "polski1");
   solo.finishOpenedActivities();

 }
 
 
// Check if there's an proper button.
	  public void testButtonFr() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "wybor");
		  assertTrue(solo.searchText("FRANCE"));
		  
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonFr() throws Exception {
	    solo.clickOnButton("FRANCE");
	    solo.assertCurrentActivity("Expected activity", "francuski1");
	    solo.finishOpenedActivities();
	  }
	  
	// Check if there's a GetBack button.
	  public void testButtonCof() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "wybor");
		  assertTrue(solo.searchText("COFNIJ"));
		  
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonCof() throws Exception {
	    solo.clickOnButton("COFNIJ");
	    solo.assertCurrentActivity("Expected activity", "GraJava");
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

