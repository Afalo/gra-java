package com.javagame.test;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import com.javagame.NewOptionActivity;
import com.javagame.R;



public class NewOptionActivityTest extends ActivityInstrumentationTestCase2<NewOptionActivity> {
	 private Solo solo;
	     
	 
	  public NewOptionActivityTest() {
		  super(NewOptionActivity.class);
	  }
	  
	  @Override
	  public void setUp() throws Exception {
		  solo = new Solo(getInstrumentation(), getActivity());
	  }
	  
		//Check if there's a proper button.
	  public void testButtonAdd() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "NewOptionActivity");
		  assertTrue(solo.searchText("Wybierz"));
	  }
	    
	  // Test pressing a button.
	  public void testClickButtonAdd() throws Exception {
	    solo.clickOnButton("Wybierz");
	    solo.assertCurrentActivity("Expected activity", "FileDialogActivity");
	    solo.finishOpenedActivities();

	  }
	//Check if there's a proper button.
	  public void testButtonBack() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "NewOptionActivity");
		  assertTrue(solo.searchText("COFNIJ"));
	  }
	   
	  // Test pressing a button.
	  public void testClickButtonBack() throws Exception {
	    solo.clickOnButton("COFNIJ");
	    solo.assertCurrentActivity("Expected activity", "SettingsActivityMain");
	    solo.finishOpenedActivities();

	  }
	  //Check if there's a proper button.
	  public void testButtonSave() throws Exception {
		  solo.assertCurrentActivity("Expected activity", "NewOptionActivity");
		  assertTrue(solo.searchText("Zapisz"));
	  }
	 	
	  
	  
	  //Test checking a text field.
	  	public void testCheckingTextFieldPl() throws Exception {
	  			EditText plInput = (EditText) solo.getCurrentActivity().findViewById(R.id.editTextPl);
	  			solo.clearEditText(plInput);
	  			solo.enterText(plInput, "coœ");
	  	}
	  		
		  
		//Test checking a text field.
		public void testCheckingTextFieldAng() throws Exception {
			EditText angInput = (EditText) solo.getCurrentActivity().findViewById(R.id.editTextEn);
			solo.clearEditText(angInput);
			solo.enterText(angInput, "something");
		}
		
		  	
	  
			  
		//Test checking a text field.
		public void testCheckingTextFieldFr() throws Exception {
			EditText frInput = (EditText) solo.getCurrentActivity().findViewById(R.id.editTextFr);
			solo.clearEditText(frInput);
			solo.enterText(frInput, "quelque chose");
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

