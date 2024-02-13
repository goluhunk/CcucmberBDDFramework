package org.gaurav.stepdef;

import java.util.List;
import java.util.Map;

import org.gaurav.pages.AuthLoginPage;
import org.gaurav.pages.CustomsFinancialsHomePage;
import org.gaurav.utils.BrowserDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef extends BrowserDriver {

	AuthLoginPage alp;


	@Given("I am signed in as a registered user")
	public void signIn() {
		alp=new AuthLoginPage(driver);
		alp.login();
	}

	@When("I navigate to the (.*) page$")
	public void navigateToPage(String page) {
		switch(page) {
		case "Customs Financials Home":{
			CustomsFinancialsHomePage.loadPage();
			break;
		}
		default :{
			System.out.println("Page Not found");
		}
		}
	}
	
	@Then("I should see my Cash account with status$")
	public void accountWithStatus(DataTable dataTable) {
		List<Map<String,String>> list=dataTable.asMaps();
		for(Map<String,String> m:list) {
		String accountNo= m.get("can");
		String status=m.get("Status");
		String balance=m.get("Avilable Account Balance");
		
		CustomsFinancialsHomePage.assertCashAccountDetails(accountNo,status,balance);
	}
		
	}

	@When("I click on \'([^\"]*)\'$")
	public void i_click_on(String link) {
		CustomsFinancialsHomePage.linkText(link).click();
	}

	@Then("^I click on the View link for dan account \"([^\"]*)\"$")
	public void i_click_on_the_view_link_for_dan_account(String account) {
		System.out.println("Account ="+account);
	}

	@Then("I should see the heading caption \"([^\"]*)\"$")
	public void i_should_see_the_heading_caption(String string) {
		System.out.println("Heading ="+string);
	} 


	@Then("the page title should be \"([^\"]*)\"$")
	public void pageTitle(String expTitle) {
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle.equals(expTitle), true);;

	}

	@Then("I should see the sub-heading \"([^\"]*)\"$")
	public void subHeadingText(String subHeading) {
		String actualText=CustomsFinancialsHomePage.getElementByText(subHeading).getText();
		Assert.assertEquals(actualText, subHeading);
	}

	
}
