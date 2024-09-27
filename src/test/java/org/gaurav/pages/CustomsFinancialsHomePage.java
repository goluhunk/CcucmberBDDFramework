package org.gaurav.pages;




import org.gaurav.utils.Configuration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomsFinancialsHomePage extends BasePage {

	String url;
	public CustomsFinancialsHomePage(WebDriver driver) {
		super(driver);
		this.url=Configuration.baseURL+":9876/customs/payment-records/";
	}

	public  void loadPage() throws InterruptedException {
		goToPage(url);
		Assert.assertEquals("Url is mismatched : ",driver.getCurrentUrl(),url);
	}

	public  List<String> DutyDefermentAccounts(WebElement el){
		     return Arrays.asList(el.findElement(By.tagName("h3")).getText().trim().split("\n")[0],
		            el.findElements(By.cssSelector(".duty-deferment-status")).isEmpty() ? null : el.findElement(By.cssSelector(".duty-deferment-status")).getText().trim(),
					el.findElements(By.cssSelector(".available-account-balance")).isEmpty() ? null : el.findElement(By.cssSelector(".available-account-balance")).getText().trim(),
					el.findElements(By.cssSelector(".govuk-warning-text__text")).isEmpty() ? null : el.findElement(By.cssSelector(".govuk-warning-text__text")).getText().trim()
			);
	}

	 public  String accountLimit(String dan){
		 return driver.findElements(By.cssSelector("#account-limit-" + dan)).isEmpty() ? null : driver.findElement(By.cssSelector("#account-limit-" + dan)).getText().trim().split("\n")[1];

	 }

	 public  List directDebitContent(){
		List ls = null;
		for(WebElement e:DutyDeferment()){
			ls=Arrays.asList(e.findElement(By.tagName("p")).getText());
		}
		return ls;
	 }

	public  String setUpDirectDebitLink(){
		for(WebElement e:DutyDeferment()){
		return e.findElement(By.tagName("a")).getAttribute("href");
		}
		return null;
	}

	public  String getPaymentDetailsHref(String account){

		for(WebElement e:DutyDeferment()){
			if(e.getText().contains(account))
			 return e.findElement(By.cssSelector("#payment-details-"+account)).getAttribute("href").trim();
			break;
		}
		return null;
	}


	public  String guaranteeLimit(String dan){
		return driver.findElements(By.cssSelector("#guarantee-limit-" + dan)).isEmpty() ? null : driver.findElement(By.cssSelector("#guarantee-limit-" + dan)).getText().trim().split("\n")[1];
	 }

	public  String guaranteeLimitRemaining(String dan){
		return driver.findElements(By.cssSelector("#guarantee-limit-remaining-" + dan)).isEmpty() ? null : driver.findElement(By.cssSelector("#guarantee-limit-remaining-" + dan)).getText().trim().split("\n")[1];
	}

	public  List<WebElement> DutyDeferment(){
		return driver.findElement(By.cssSelector("#duty-deferment-accounts"))
				.findElements(By.cssSelector(".duty-deferment-account"))
				.stream().collect(Collectors.toList());
	}

	public  boolean presenceLink(String link){
		return driver.findElement(By.xpath("//*[contains(text(),'"+link+"')]")).isDisplayed();
	}




	public  String DutyDefermentAccountList(String account){
		//String text = null;
		for(WebElement el:DutyDeferment()) {
			if(el.getText().contains(account)){
				return el.findElements(By.cssSelector(".govuk-hint")).isEmpty() ? null :
						el.findElement(By.cssSelector(".govuk-hint")).getText();
		}

		}
		return null;
	}

	public List<List<String>> DutyDefermentAccountCard(){
			return DutyDeferment().stream()
					.map(el->DutyDefermentAccounts(el)).collect(Collectors.toList());

	}

	public  boolean paymentDetailsLinkPresence(String account){
		boolean b=false;
     for(WebElement e:DutyDeferment()){
		 if(e.getText().contains(account)){
			b= e.findElement(By.cssSelector("#payment-details-"+account)).isDisplayed();
			 break;
		 }
	 }
		return b;
	}

	public  List<List<String>> CashAccounts()
	{
		List<WebElement> cashAccount=driver.findElement(By.cssSelector("#cash-accounts"))
				.findElements(By.cssSelector(".cash-account"));
		List<List<String>> accountList;

		 accountList=cashAccount.stream().map(el->
				Arrays.asList(el.findElement(By.tagName("h3")).getText().split("\n")[0],
						el.findElements(By.cssSelector(".cash-account-status")).isEmpty() ? null
								:el.findElement(By.cssSelector(".cash-account-status")).getText().trim(),
						el.findElement(By.tagName("p")).getText().trim())
				).collect(Collectors.toList());



		return accountList;
	}
}
