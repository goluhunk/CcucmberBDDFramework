package org.gaurav.utils;

import org.gaurav.stepdef.Hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserDriver{

	public  WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}




	public void setDriver(String browser) {
		switch(browser) {
			case "chrome":{
				driver=	new ChromeDriver();
				break;
			}
			case "firefox":{
				driver=new FirefoxDriver();

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
				driver=new EdgeDriver();
				break;
			}
			default: {
				System.out.println("Driver not found");
			}
		}

	}


}
