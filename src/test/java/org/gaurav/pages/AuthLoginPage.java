package org.gaurav.pages;

import java.io.IOException;
import java.util.List;

import org.gaurav.utils.Configuration;
import org.gaurav.utils.ReadJSONData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthLoginPage extends BasePage {

    String url = Configuration.getAuthLoginStub() + "/gg-sign-in";

    public AuthLoginPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='redirectionUrl']")
    WebElement redirectURL;

    @FindBy(xpath = "//table[@class='govuk-table']/tbody/tr[2]/td/div/input")
    List<WebElement> enrollmentsKeys;

    public void login() {
        goToPage(url);
        redirectURL.sendKeys(redirectURL()+"/customs/payment-records");
          try {
            enrollmentsKeys.get(0).sendKeys(ReadJSONData.readData("enrollmentKey"));
            enrollmentsKeys.get(1).sendKeys(ReadJSONData.readData("enrollmentValue"));
            enrollmentsKeys.get(2).sendKeys(ReadJSONData.readData("EORINumber"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        enrollmentsKeys.get(2).sendKeys(Keys.ENTER);
    }


}
