package org.gaurav.pages;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomsFinancialsHomePage extends BasePage {

	static String url= envBaseUrl+":"+port +"/customs/payment-records";

	public static void loadPage() {
		goToPage(url);

	}

	public static List<String> DutyDefermentAccounts(WebElement el){
		     return Arrays.asList(el.findElement(By.tagName("h3")).getText().trim().split("\n")[0],
		            el.findElements(By.cssSelector(".duty-deferment-status")).isEmpty() ? null : el.findElement(By.cssSelector(".duty-deferment-status")).getText().trim(),
					el.findElements(By.cssSelector(".available-account-balance")).isEmpty() ? null : el.findElement(By.cssSelector(".available-account-balance")).getText().trim(),
					el.findElements(By.cssSelector(".govuk-warning-text__text")).isEmpty() ? null : el.findElement(By.cssSelector(".govuk-warning-text__text")).getText().trim()
			);
	}

	 public static String accountLimit(String dan){
		 return driver.findElements(By.cssSelector("#account-limit-" + dan)).isEmpty() ? null : driver.findElement(By.cssSelector("#account-limit-" + dan)).getText().trim().split("\n")[1];

	 }

	 public static List directDebitContent(){
		List ls = null;
		for(WebElement e:DutyDeferment()){
			ls=Arrays.asList(e.findElement(By.tagName("p")).getText());
		}
		return ls;
	 }

	public static String setUpDirectDebitLink(){
		for(WebElement e:DutyDeferment()){
		return e.findElement(By.tagName("a")).getAttribute("href");
		}
		return null;
	}

	public static String getPaymentDetailsHref(String account){

		for(WebElement e:DutyDeferment()){
			if(e.getText().contains(account))
			 return e.findElement(By.cssSelector("#payment-details-"+account)).getAttribute("href").trim();
			break;
		}
		return null;
	}


	public static String guaranteeLimit(String dan){
		return driver.findElements(By.cssSelector("#guarantee-limit-" + dan)).isEmpty() ? null : driver.findElement(By.cssSelector("#guarantee-limit-" + dan)).getText().trim().split("\n")[1];
	 }

	public static String guaranteeLimitRemaining(String dan){
		return driver.findElements(By.cssSelector("#guarantee-limit-remaining-" + dan)).isEmpty() ? null : driver.findElement(By.cssSelector("#guarantee-limit-remaining-" + dan)).getText().trim().split("\n")[1];
	}

	public static  List<WebElement> DutyDeferment(){
		return driver.findElement(By.cssSelector("#duty-deferment-accounts"))
				.findElements(By.cssSelector(".duty-deferment-account"))
				.stream().collect(Collectors.toList());
	}





	public static String DutyDefermentAccountList(String account){
		//String text = null;
		for(WebElement el:DutyDeferment()) {
			if(el.getText().contains(account)){
				return el.findElements(By.cssSelector(".govuk-hint")).isEmpty() ? null :
						el.findElement(By.cssSelector(".govuk-hint")).getText();
		}

		}
		return null;
	}

	public static List<List<String>> DutyDefermentAccountCard(){
			return DutyDeferment().stream()
					.map(el->DutyDefermentAccounts(el)).collect(Collectors.toList());

	}

	public static boolean paymentDetailsLinkPresence(String account){
		boolean b=false;
     for(WebElement e:DutyDeferment()){
		 if(e.getText().contains(account)){
			b= e.findElement(By.cssSelector("#payment-details-"+account)).isDisplayed();
			 break;
		 }
	 }
		return b;
	}

	public static List<String> CashAccounts()
	{
		List<WebElement> cashAccount=driver.findElement(By.cssSelector("#cash-accounts"))
				.findElements(By.cssSelector(".cash-account"));

		List accountList=cashAccount.stream().map(el->
				Arrays.asList(el.findElement(By.tagName("h3")).getText().split("\n")[0],
						el.findElements(By.cssSelector(".cash-account-status")).isEmpty() ? null
								:el.findElement(By.cssSelector(".cash-account-status")).getText().trim(),
						el.findElement(By.tagName("p")).getText().trim())
				).collect(Collectors.toList());



		return accountList;
	}
}
