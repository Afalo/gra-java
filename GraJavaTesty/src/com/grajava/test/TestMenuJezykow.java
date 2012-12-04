package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import com.grajava.*;


public class TestMenuJezykow extends ActivityInstrumentationTestCase2<wybor>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestMenuJezykow() {
          super("com.grajava", wybor.class);
  }

  public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
  // Check if there's a proper button.
  public void testButtonAng() throws Exception {
	  solo.assertCurrentActivity("Expected activity", "Screen");
	  assertTrue(solo.searchText("ENGLISH"));
  }
    
  // Test pressing a button.
  public void testClickButtonAng() throws Exception {
    solo.clickOnButton("ENGLISH");
    solo.assertCurrentActivity("Expected activity", "Screen");

  }
  
//Check if there's a proper button.
 public void testButtonPl() throws Exception {
	 solo.assertCurrentActivity("Expected activity", "Screen");
	  assertTrue(solo.searchText("POLSKI"));
 }
   
 // Test pressing a button.
 public void testClickButtonPl() throws Exception {
   solo.clickOnButton("POLSKI");
   solo.assertCurrentActivity("Expected activity", "Screen");

 }
 
 
// Check if there's an proper button.
	  public void testButtonFr() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "Screen");
		  assertTrue(solo.searchText("FRANCE"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonFr() throws Exception {
	    solo.clickOnButton("FRANCE");
	    solo.assertCurrentActivity("Expected activity", "Screen");
	
	  }
	  
	// Check if there's a GetBack button.
	  public void testButtonCof() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "Screen");
		  assertTrue(solo.searchText("COFNIJ"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonCof() throws Exception {
	    solo.clickOnButton("COFNIJ");
	    solo.assertCurrentActivity("Expected activity", "Screen");
	
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

