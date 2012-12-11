package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grajava.francuski2;
import com.jayway.android.robotium.solo.Solo;


public class TestGraFr extends ActivityInstrumentationTestCase2<francuski2>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestGraFr() {
          super("com.grajava", francuski2.class);
  }

  @Override
public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  public void testDobraOdpowiedz() throws Exception {
    solo.clickOnButton(0);
    solo.assertCurrentActivity("Expected activity", "francuski3");
    assertTrue(solo.searchText("Felicitations! T as gagne un point! :)"));
    solo.clickOnImageButton(0);
  }
  
  public void testZlaOdpowiedz() throws Exception {
	    solo.clickOnButton(1);
	    solo.assertCurrentActivity("Expected activity", "francuski3a");
	    assertTrue(solo.searchText("Helas, t es trompe! :("));
	    solo.clickOnImageButton(0);
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
