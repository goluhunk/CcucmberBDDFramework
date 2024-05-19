package org.gaurav.stepdef;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.cucumber.java.it.Ma;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.gaurav.pages.*;
import org.gaurav.utils.BrowserDriver;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDef extends BrowserDriver {

    AuthLoginPage alp;


    @Given("I am signed in as a registered user")
    public void signIn() {
        alp = new AuthLoginPage(driver);
        alp.login();
    }

    @When("I navigate to the (.*) page$")
    public void navigateToPage(String page) {
        switch (page) {
            case "Customs Financials Home": {
                CustomsFinancialsHomePage.loadPage();
                break;
            }
            default: {
                System.out.println("Page Not found");
            }
        }
    }

    @Then("I should see my Cash account with status$")
    public void accountWithStatus(DataTable dataTable) {
        //System.out.println("Data Table  = "+dataTable.asList());
        //List<List<String>> l1=dataTable.asLists();
        //System.out.println("L1 = "+l1);
        List<Map<String, String>> list = dataTable.asMaps();
        System.out.println("List =" + list);
        for (Map<String, String> m : list) {
            String accountNo = m.get("can");
            String status = m.get("Status");
            String balance = m.get("Avilable Account Balance");

            CustomsFinancialsHomePage.assertCashAccountDetails(accountNo, status, balance);
        }

    }

    @When("I click on \'([^\"]*)\'$")
    public void i_click_on(String link) {
        CustomsFinancialsHomePage.linkText(link).click();
    }

    @Then("^I click on the View link for dan account \"([^\"]*)\"$")
    public void i_click_on_the_view_link_for_dan_account(String account) {
        System.out.println("Account =" + account);
    }

    @Then("I should see the heading caption \"([^\"]*)\"$")
    public void i_should_see_the_heading_caption(String string) {
        System.out.println("Heading =" + string);
    }


    @Then("the page title should be \"([^\"]*)\"$")
    public void pageTitle(String expTitle) {
        String actualTitle = driver.getTitle();
        //System.out.println(actualTitle);
        Assert.assertEquals(actualTitle.equals(expTitle), true);

    }

    @Then("I should see the (|sub-)heading \"([^\"]*)\"$")
    public void subHeadingText(String tag, String subHeading) {
        String headingTag =null;
            switch (tag) {
                case "sub-":
                    headingTag = "h2";
                    break;
                case "":
                    headingTag = "h1";
                    break;
                default:
                    System.out.println("Element Not found");
            }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(headingTag)));
        driver.findElement(By.tagName(headingTag)).getText().equalsIgnoreCase(subHeading);
    }


    @When("I click on Account details link for \"([^\"]*)\"$")
    public void i_click_on_account_details_link_for(String string) {
        YourContactDetailsPage.clickOnContactDetailsLink((string));
    }

    @Then("I should see the following contact details")
    public void i_should_see_the_following_contact_details(DataTable dataTable) {
        List<Map<String, String>> list= dataTable.asMaps();
        Map<String, String> finalMap = new HashMap<>();
        for(Map<String,String> map:list){
            for(String key:map.keySet()){
                finalMap.put(key,map.get(key));
            }
        }
       // System.out.println(finalMap);
      // System.out.println(YourContactDetailsPage.verifyContactDetailsContent());
  Assert.assertEquals(finalMap,YourContactDetailsPage.verifyContactDetailsContent());
       // System.out.println(list.equals(YourContactDetailsPage.verifyContactDetailsContent()));
    }

    @Then("I should see the back link to previous page")
    public void i_should_see_the_back_link_to_previous_page() {
        BasePage.backLink().getText().equals("back");
    }
    @When("I click on back link to previous page")
    public void i_click_on_back_link_to_previous_page() {
        BasePage.backLink().click();
    }

    @When("I click on change link for (.*)$")
    public void i_click_on_change_link_for_name(String field) {
        BasePage.changeLink(field).click();
    }
    @Then("I should see the following details populated in the form")
    public void i_should_see_the_following_details_populated_in_the_form(DataTable dataTable) {
        List<Map<String, String>> list= dataTable.asMaps();
        Map<String, String> finalMap = new HashMap<>();
        for(Map<String,String> map:list){
            for(String key:map.keySet()){
                finalMap.put(key,map.get(key));
            }
        }
        Assert.assertEquals(finalMap,EditContactDetailsPage.getPrepopulatedFormDetails());
    }


}
