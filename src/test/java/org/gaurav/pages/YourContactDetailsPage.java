package org.gaurav.pages;

import org.gaurav.utils.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YourContactDetailsPage extends BasePage{

    String url= Configuration.baseURL +"/customs/payment-records/your-contact-details";

    public YourContactDetailsPage(WebDriver driver) {
        super(driver);
    }

    public  void clickOnContactDetailsLink(String accountNo){
        List<WebElement> accounts=driver.findElements(By.cssSelector(".govuk-summary-list"));

        for(WebElement account:accounts){
          if(account.findElement(By.cssSelector(".govuk-summary-list__value"))
                    .getText().equalsIgnoreCase(accountNo)){
              account.findElement(By.partialLinkText("View or change")).click();
              break;
          }
        }

    }

    public  Map<String,String> verifyContactDetailsContent(){
    List<WebElement> details=driver.findElements(By.cssSelector(".govuk-summary-list__row"));
    Map<String ,String> map=new HashMap<String,String>();
    for(WebElement detail:details){
        String key=detail.findElement(By.cssSelector(".govuk-summary-list__key")).getText();
        String value=detail.findElement(By.cssSelector(".govuk-summary-list__value")).getText()
                .replace("\n",",");
        map.put(key,value);

    }

        return map;
    }

}
