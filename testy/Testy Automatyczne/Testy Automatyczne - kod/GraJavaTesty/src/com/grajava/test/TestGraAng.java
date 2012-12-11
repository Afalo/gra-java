package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grajava.angielski2;
import com.jayway.android.robotium.solo.Solo;


public class TestGraAng extends ActivityInstrumentationTestCase2<angielski2>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestGraAng() {
          super("com.grajava", angielski2.class);
  }

  @Override
public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  public void testDobraOdpowiedz() throws Exception {
    solo.clickOnButton(0);
    solo.assertCurrentActivity("Expected activity", "angielski3");
    assertTrue(solo.searchText("One more point!! :)"));
    solo.clickOnImageButton(0);

  }
  
  public void testZlaOdpowiedz() throws Exception {
	  	solo.clickOnButton(1);
	    solo.assertCurrentActivity("Expected activity", "angielski3a");
	    assertTrue(solo.searchText("Wrong!! ;("));
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
