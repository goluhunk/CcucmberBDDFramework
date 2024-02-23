package org.gaurav.pages;


import org.junit.Assert;

public class CustomsFinancialsHomePage extends BasePage {

	static String url=baseurl+"/customs/payment-records";

	public static void loadPage() {
		goToPage(url);

	}

	public static void assertCashAccountDetails(String accountNo, String status,String balance) {

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
