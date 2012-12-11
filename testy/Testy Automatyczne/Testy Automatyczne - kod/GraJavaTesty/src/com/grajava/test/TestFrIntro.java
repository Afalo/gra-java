package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grajava.francuski1;
import com.jayway.android.robotium.solo.Solo;


public class TestFrIntro extends ActivityInstrumentationTestCase2<francuski1>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestFrIntro() {
          super("com.grajava", francuski1.class);
  }

  @Override
public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  // Test pressing a button.
  public void testClickButtonFr() throws Exception {
	solo.clickOnImageButton(0); 
    solo.assertCurrentActivity("Expected activity", "francuski2");
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

