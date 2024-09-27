package org.gaurav.pages;

import java.util.List;

import org.gaurav.utils.Configuration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class BasePage  {

	WebDriver driver;
	String url;
	int port = 9876;
	public BasePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}


	public  String redirectURL(){
		if(Configuration.baseURL.startsWith("http://local"))
			return (Configuration.baseURL+":"+port);
		else
			return Configuration.baseURL;
	}




	public void goToPage(String url) throws InterruptedException {
		driver.get(url);
	}

	public  WebElement linkText(String link) {
		return driver.findElement(By.linkText(link));
	}

	public  WebElement getElementByText(String text) {
		if(text!=null) {
		return driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]"));
		}
		else return null;
	}
	
	public  List<WebElement> getElementsByID(String id){
		return driver.findElements(By.xpath("//*[contains(@id,'"+id+"')]"));
		
	}

	public  WebElement backLink() {

		return driver.findElement(By.cssSelector(".govuk-back-link"));
	}

	public  WebElement changeLink(String link) {

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

	public  String warningText(){

		return driver.findElement(By.cssSelector("#duty-deferment-balances-warning")).getText().trim();

	}

}
