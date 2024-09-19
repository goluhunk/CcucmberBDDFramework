package org.gaurav.pages;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
		String ls = null;
		for(WebElement e:DutyDeferment()){
			ls =e.findElement(By.tagName("a")).getAttribute("href");
		}
		return ls;
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
		String text = null;
		for(WebElement el:DutyDeferment()) {
			if(el.getText().contains(account)){
				text=el.findElements(By.cssSelector(".govuk-hint")).isEmpty() ? null :
						el.findElement(By.cssSelector(".govuk-hint")).getText();
		}

		}
		return text;
	}

	public static List<List<String>> DutyDefermentAccountCard(){
			return DutyDeferment().stream()
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
