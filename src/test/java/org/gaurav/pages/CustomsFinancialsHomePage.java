package org.gaurav.pages;


import org.gaurav.utils.Configuration;
import org.junit.Assert;

import java.util.Optional;

public class CustomsFinancialsHomePage extends BasePage {

	static String url= envBaseUrl +"/customs/payment-records";

	public static void loadPage() {
		goToPage(url);

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
