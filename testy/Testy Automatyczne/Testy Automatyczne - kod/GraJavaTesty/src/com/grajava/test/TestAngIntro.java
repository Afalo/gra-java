package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grajava.angielski1;
import com.jayway.android.robotium.solo.Solo;


public class TestAngIntro extends ActivityInstrumentationTestCase2<angielski1>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestAngIntro() {
          super("com.grajava", angielski1.class);
  }

  @Override
public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  // Test pressing a button.
  public void testClickButtonAng() throws Exception {
	solo.clickOnImageButton(0); 
    solo.assertCurrentActivity("Expected activity", "angielski2");
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

