package org.gaurav.stepdef;

import java.time.Duration;
import java.util.*;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDef extends BrowserDriver {

    AuthLoginPage alp;


    @Given("I (am |)sign(ed|) in as a (.*) user$")
    public void signIn(String am,String ed,String userType) throws InterruptedException {
        alp = new AuthLoginPage(driver);
        alp.login(userType);
    }

    @Given("I am not signed in$")
    public void notSignIn() {
        System.out.println("*** BEFORE ***" +driver.manage().getCookies());
        driver.manage().deleteAllCookies();
        System.out.println("*** AFTER ***" +driver.manage().getCookies());
    }
    @Then("^I see the EORI and Company name '(.*)' on landing page$")
    public void EORIAndCompanyName(String expectedEoriCompany){
        String expected=expectedEoriCompany;
        System.out.println();

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

    @Then("I should see my duty deferment accounts$")
    public void dutyDefermentAccount(DataTable data) {
       List<List<String>> expected=data.asLists().stream().skip(1).collect(Collectors.toList());
       List<List<String>> actual=  CustomsFinancialsHomePage.DutyDefermentAccountCard();
       Assert.assertEquals(actual,expected);
    }

    @Then("I should see duty deferment account limits$")
    public void dutyDefermentAccountLimits(DataTable data) {


        List<Map<String, String>> dataMap = data.asMaps();

        dataMap.stream().map(account ->

                {

                    List expectedList = Arrays.asList(account.get("Account Limit"),
                            account.get("Schemes text"),
                            account.get("Guarantee Limit"),
                            account.get("Guarantee Remaining")
                    );
                    String accountNumber;
                    if(account.get("Account Number").contains("Northern Ireland")) {
                        System.out.println("Inside Northern Ireland");
                         accountNumber = account.get("Account Number").substring(26);
                        System.out.println("accountNumber =>"+accountNumber);
                    }
                    else{
                        accountNumber = account.get("Account Number").substring(9);
                    }



                    List actualList = Arrays.asList(CustomsFinancialsHomePage.accountLimit(accountNumber),
                            CustomsFinancialsHomePage.DutyDefermentAccountList(accountNumber),
                            CustomsFinancialsHomePage.guaranteeLimit(accountNumber),
                            CustomsFinancialsHomePage.guaranteeLimitRemaining(accountNumber)
                    );

                    Assert.assertEquals(expectedList,actualList);

                    return null;
                }
        ).collect(Collectors.toList());
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
    public void  pageTitle(String title) {
        Assert.assertEquals(title,driver.getTitle());
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

    @When("I enter the following details")
    public void i_enter_the_following_details(DataTable dataTable) {
        throw new io.cucumber.java.PendingException();
    }
    @When("I click on Save changes button")
    public void i_click_on_save_changes_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should see the following confirmation text")
    public void i_should_see_the_following_confirmation_text(io.cucumber.datatable.DataTable dataTable) {
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should see a link to {string}")
    public void i_should_see_a_link_to(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
