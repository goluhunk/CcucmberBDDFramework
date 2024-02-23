package org.gaurav.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserDriver {

	protected static  WebDriver driver;

	public static WebDriver getDriver( String rdriver) {

		switch(rdriver) {
		case "chrome":{
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			break;
		}
		case "firefox":{
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		}
			case "remote-chrome":{
				ChromeOptions options = new ChromeOptions();
				driver=new RemoteWebDriver(options);
				break;
			}

			case "remote-firefox":{
				FirefoxOptions options = new FirefoxOptions();
				driver=new RemoteWebDriver(options);
				break;
			}
		case "edge":{
			driver= new EdgeDriver();
			driver.manage().window().maximize();
			break;
		}
		default: {
			System.out.println("Driver not found");
		}
		}
		return driver;

	}
}
