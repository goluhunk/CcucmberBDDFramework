package org.gaurav.pages;

import java.util.List;

import org.gaurav.utils.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class BasePage extends BrowserDriver {


	static String baseurl="https://www.development.tax.service.gov.uk";


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
	
}
