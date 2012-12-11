package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grajava.polski1;
import com.jayway.android.robotium.solo.Solo;


public class TestPlIntro extends ActivityInstrumentationTestCase2<polski1>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestPlIntro() {
          super("com.grajava", polski1.class);
  }

  @Override
public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  // Test pressing a button.
  public void testClickButtonPl() throws Exception {
	solo.clickOnImageButton(0); 
    solo.assertCurrentActivity("Expected activity", "polski2");
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

