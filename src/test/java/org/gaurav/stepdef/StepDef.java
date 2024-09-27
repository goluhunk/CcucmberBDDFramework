package org.gaurav.stepdef;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import org.gaurav.pages.*;
import static org.junit.Assert.assertTrue;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.gaurav.utils.BrowserDriver;
import org.gaurav.utils.DataTableConverters;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDef {

    WebDriver driver;
    AuthLoginPage alp;
    CustomsFinancialsHomePage cfh;
    EditContactDetailsPage ecd;
    YourContactDetailsPage ycd;
    public StepDef(BrowserDriver browserdriver){
        this.driver=browserdriver.getDriver();
        System.out.println("StepDef initialized with BrowserDriver: " + browserdriver);
        alp = new AuthLoginPage(driver);
        cfh=new CustomsFinancialsHomePage(driver);
        ecd=new EditContactDetailsPage(driver);
        ycd=new YourContactDetailsPage(driver);

    }
    @Given("I (am |)sign(ed|) in as a (.*) user$")
    public void signIn(String am,String ed,String userType) throws InterruptedException {
        if (driver== null) {
            throw new IllegalStateException("WebDriver is not initialized. Ensure the driver is set up in Hooks.");
        }

        alp.doLogin(userType);
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
    public void navigateToPage(String page) throws InterruptedException {
        switch (page) {
            case "Customs Financials Home": {
                   cfh.loadPage();

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
       List<List<String>> actual=  cfh.DutyDefermentAccountCard();
       Assert.assertEquals(actual,expected);
    }

    @Then("I should see duty deferment account limits$")
    public void dutyDefermentAccountLimits(DataTable data) {


        List<Map<String, String>> dataMap = data.asMaps();

        dataMap.forEach(account ->

                {

                    List<String> expectedList = Arrays.asList(account.get("Account Limit"),
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



                    List<String> actualList = Arrays.asList(cfh.accountLimit(accountNumber),
                            cfh.DutyDefermentAccountList(accountNumber),
                            cfh.guaranteeLimit(accountNumber),
                            cfh.guaranteeLimitRemaining(accountNumber)
                    );

                    Assert.assertEquals("Account Mismatch in account" + accountNumber,expectedList,actualList);

                }
                );
    }

    @Then("I should see the following duty deferment balances warning text")
    public void i_should_see_the_following_duty_deferment_balances_warning_text(DataTable data) {
        List expected= DataTableConverters.asListOfStrings(data);
        List actual=Arrays.asList(cfh.warningText());
        Assert.assertEquals(expected,actual);

    }

    @Then("I should see the Payment details link for account (.*)$")
    public void i_should_see_the_payment_details_link_for_account(String account) {
      Boolean expected =cfh.paymentDetailsLinkPresence(account);
      Assert.assertEquals(expected,true);
    }
    @Then("the payment details link for account (.*) should point to direct debit details page$")
    public void the_payment_details_link_for_account_should_point_to_direct_debit_details_page(String account) {
     String link=cfh.getPaymentDetailsHref(account);
     assertTrue(link.startsWith("http://localhost:9397/customs/duty-deferment"));
     assertTrue(link.endsWith("/direct-debit"));
    }



    @Then("I should see the following direct debit content")
    public void i_should_see_the_following_direct_debit_content(DataTable data) {
        List expected=DataTableConverters.asListOfStrings(data);
        List actual =cfh.directDebitContent();
        Assert.assertEquals(expected,actual);


    }
    @Then("I should see the set up a new Direct Debit link")
    public void i_should_see_the_set_up_a_new_direct_debit_link() {
        String actual=cfh.setUpDirectDebitLink();

        assertTrue(actual.startsWith("http://localhost:9397/customs/duty-deferment/"));
       assertTrue(actual.endsWith("/direct-debit"));
    }


    @When("I click on \'([^\"]*)\'$")
    public void i_click_on(String link) {
        cfh.linkText(link).click();
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

    @Then("I should see my cash account with status")
    public void i_should_see_my_cash_account_with_status(DataTable dataTable) {
        List expected=dataTable.asLists().stream().skip(1).collect(Collectors.toList());
        System.out.println("Expected =>"+expected);
        List actual=cfh.CashAccounts();
        System.out.println("Actual =>"+actual);
        Assert.assertEquals("Account mismatch",expected,actual);


    }


    @When("I click on Account details link for \"([^\"]*)\"$")
    public void i_click_on_account_details_link_for(String string) {
        ycd.clickOnContactDetailsLink((string));
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
      Assert.assertEquals(finalMap,ycd.verifyContactDetailsContent());
    }

    @Then("I should see the back link to previous page")
    public void i_should_see_the_back_link_to_previous_page() {
        cfh.backLink().getText().equals("back");
    }
    @When("I click on back link to previous page")
    public void i_click_on_back_link_to_previous_page() {
        cfh.backLink().click();
    }

    @When("I click on change link for (.*)$")
    public void i_click_on_change_link_for_name(String field) {
        cfh.changeLink(field).click();
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
        Assert.assertEquals(finalMap,ecd.getPrepopulatedFormDetails());
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
    public void i_should_see_a_link_to(String link) {
        cfh.presenceLink(link);
    }


    @Then("the Is this page not working properly? page url is correct")
    public void the_is_this_page_not_working_properly_page_url_is_correct() {

    }


}
