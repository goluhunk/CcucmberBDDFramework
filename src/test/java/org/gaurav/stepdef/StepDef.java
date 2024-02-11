package org.gaurav.stepdef;

import org.gaurav.pages.AuthLoginPage;
import org.gaurav.utils.BrowserDriver;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class StepDef extends BrowserDriver {
	
	AuthLoginPage alp;
	
	 
	@Given("I am signed in as a RegisteredUser")
	public void signIn() {
		alp=new AuthLoginPage(driver);
		alp.login();
	  System.out.println("In Given");
	}

	@When("I navigate to (.*)$")
	public void navigateToGoogle(String site) {
		 driver.get("http://www.google.com");
	}

	@Then("The Page title should be (.*)$")
	public void pageTitle(String title) {
		 String s=driver.getTitle();
		 System.out.println(title);
		 System.out.println(s);
		 Assert.assertEquals(s.equals(title), true);;
		
	}
}
