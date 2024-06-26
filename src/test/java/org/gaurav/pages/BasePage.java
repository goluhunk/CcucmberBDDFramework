package org.gaurav.pages;

import java.util.List;

import org.gaurav.utils.BrowserDriver;
import org.gaurav.utils.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class BasePage extends BrowserDriver {

	static String envBaseUrl = Configuration.getBaseURL();
	static int port = 9876;
	static String redirect="/customs/payment-records";

	public static  String redirectURL(){
		if(Configuration.getBaseURL().startsWith("http://local"))
			return envBaseUrl=envBaseUrl+":"+port;
		else
			return envBaseUrl;
	}


	public static void goToPage(String url) {

		driver.get(url);
	}

	public static WebElement linkText(String link) {

		return driver.findElement(By.linkText(link));
	}

	public static WebElement getElementByText(String text) {
		if(text!=null) {
		return driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]"));
		}
		else return null;
	}
	
	public static List<WebElement> getElementsByID(String id){
		return driver.findElements(By.xpath("//*[contains(@id,'"+id+"')]"));
		
	}

	public static WebElement backLink() {

		return driver.findElement(By.cssSelector(".govuk-back-link"));
	}

	public static WebElement changeLink(String link) {

		WebElement el=null;
		List<WebElement> list = driver.findElements(By.cssSelector(".govuk-summary-list__row"));
		for (WebElement e : list) {
			if (e.findElement(By.cssSelector(".govuk-summary-list__key"))
					.getText().equalsIgnoreCase(link)) {
				el=e.findElement(By.tagName("a"));
				break;
			}
		}
		return el;
	}
	
}
