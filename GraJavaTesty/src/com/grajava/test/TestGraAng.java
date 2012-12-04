package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import com.grajava.*;


public class TestGraAng extends ActivityInstrumentationTestCase2<angielski2>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestGraAng() {
          super("com.grajava", angielski2.class);
  }

  public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  public void testDobraOdpowiedz() throws Exception {
    solo.clickOnButton(0);
    solo.assertCurrentActivity("Expected activity", "Screen");
    assertTrue(solo.searchText("One more point!! :)"));

  }
  
  public void testZlaOdpowiedz() throws Exception {
	    solo.clickOnButton(1);
	    solo.assertCurrentActivity("Expected activity", "Screen");
	    assertTrue(solo.searchText("Wrong!! ;("));

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
