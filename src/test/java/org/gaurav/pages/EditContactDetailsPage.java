package org.gaurav.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditContactDetailsPage extends BasePage{

    String url=envBaseUrl+"/customs/payment-records/your-contact-details";



    public static Map<String,String> getPrepopulatedFormDetails(){
    List<WebElement> details=driver.findElements(By.cssSelector(".govuk-form-group"));
    Map<String ,String> map=new HashMap<String,String>();
    for(WebElement detail:details){
        String key=detail.findElement(By.cssSelector(".govuk-label")).getText().trim();
        String value=detail.findElement(By.tagName("input")).getAttribute("value").trim();
        map.put(key,value);

    }

        return map;
    }

}
