package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import com.grajava.*;


public class TestMenuGlowne extends ActivityInstrumentationTestCase2<GraJava> {
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestMenuGlowne() {
          super("com.grajava", GraJava.class);
  }

  public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
  // Check if there's a Start button.
  public void testButtonStart() throws Exception {
	    solo.assertCurrentActivity("Expected activity", "Screen");
	  assertTrue(solo.searchText("START"));
  }
    
  // Test pressing a Start button.
  public void testClickButtonStart() throws Exception {
    solo.clickOnButton("START");
    solo.assertCurrentActivity("Expected activity", "Screen");

  }
  
// Check if there's an Option button.
	  public void testButtonOption() throws Exception {
		    solo.assertCurrentActivity("Expected activity", "Screen");
		  assertTrue(solo.searchText("OPCJE"));
	  }
	    
	  // Test pressing an Option button.
	  public void testClickButtonOption() throws Exception {
	    solo.clickOnButton("OPCJE");
	    solo.assertCurrentActivity("Expected activity", "Screen");
	
	  }
	  
	// Check if there's an Exit button.
	  public void testButtonExit() throws Exception {
		    solo.assertCurrentActivity("Expected activity", "Screen");
		  assertTrue(solo.searchText("WYJŚCIE"));
	  }
	    
	  // Test pressing an exit button.
	  public void testClickButtonExit() throws Exception {
	    solo.clickOnButton("WYJŚCIE");
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
