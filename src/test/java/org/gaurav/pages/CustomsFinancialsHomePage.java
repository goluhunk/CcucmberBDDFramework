package org.gaurav.pages;


import org.gaurav.utils.Configuration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomsFinancialsHomePage extends BasePage {

	static String url= envBaseUrl +"/customs/payment-records";

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


	public static List<List<String>> DutyDefermentAccountCard(){
			return driver.findElement(By.cssSelector("#duty-deferment-accounts"))
					.findElements(By.cssSelector(".duty-deferment-account"))
					.stream()
					.map(el->DutyDefermentAccounts(el)).collect(Collectors.toList());

	}

	public static void assertCashAccountDetails(String accountNo, String status, String balance) {

		Assert.assertEquals(getElementByText(accountNo).getText().contains(accountNo), true);
		 if(status==null) {
			 Assert.assertEquals(null, status);
		 }
		 else {
		switch(status) {
		case "SUSPENDED":Assert.assertEquals("SUSPENDED", status);
		break;
		case "CLOSED":Assert.assertEquals("CLOSED", status);
		break;
		default :Assert.assertEquals(getElementByText(status).getText(), status);
		}
		 }
		
		String thisbalance=balance.replaceAll("\\s.*", "");
		Assert.assertEquals(getElementByText(thisbalance).getText(),thisbalance);
	}
}
