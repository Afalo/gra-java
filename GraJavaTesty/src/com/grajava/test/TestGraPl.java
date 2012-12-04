package com.grajava.test;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import com.grajava.*;


public class TestGraPl extends ActivityInstrumentationTestCase2<polski2>{
  private Solo solo;
  
  @SuppressWarnings("deprecation")
public TestGraPl() {
          super("com.grajava", polski2.class);
  }

  public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }
  
 
  public void testDobraOdpowiedz() throws Exception {
    solo.clickOnButton(0);
    solo.assertCurrentActivity("Expected activity", "Screen");
    assertTrue(solo.searchText("Zdobyles punkt!! :)"));

  }
  
  public void testZlaOdpowiedz() throws Exception {
	    solo.clickOnButton(1);
	    solo.assertCurrentActivity("Expected activity", "Screen");
	    assertTrue(solo.searchText("zle! :("));

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
