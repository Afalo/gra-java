package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grajava.polski2;
import com.jayway.android.robotium.solo.Solo;


public class TestGraPl extends ActivityInstrumentationTestCase2<polski2>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestGraPl() {
          super("com.grajava", polski2.class);
  }

  @Override
public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  public void testDobraOdpowiedz() throws Exception {
    solo.clickOnButton(0);
    solo.assertCurrentActivity("Expected activity", "polski3");
    assertTrue(solo.searchText("Zdobyles punkt!! :)"));
    solo.clickOnImageButton(0);
  }
  
  public void testZlaOdpowiedz() throws Exception {
	    solo.clickOnButton(1);
	    solo.assertCurrentActivity("Expected activity", "polski3a");
	    assertTrue(solo.searchText("zle! :("));
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
