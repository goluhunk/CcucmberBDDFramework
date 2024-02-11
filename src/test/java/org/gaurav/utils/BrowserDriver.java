package org.gaurav.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriver {

	protected static  WebDriver driver;

	public static WebDriver getDriver( String rdriver) {
		switch(rdriver) {
		case "chrome":{
			driver= new ChromeDriver();
			break;
		}
		case "firefox":{
			driver= new FirefoxDriver();
			break;
		}
		case "edge":{
			driver= new EdgeDriver();
			break;
		}
		default: {
			System.out.println("Driver not found");
		}
		}
		return driver;

	}
}
