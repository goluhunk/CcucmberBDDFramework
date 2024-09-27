package org.gaurav.stepdef;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.gaurav.utils.BrowserDriver;
import org.gaurav.utils.Configuration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.browser.Browser;


public class Hooks extends BrowserDriver {


	public BrowserDriver browserDriver;
	public Hooks(BrowserDriver browserdriver) {
		this.browserDriver=browserdriver;
		System.out.println("Hooks initialized with BrowserDriver: " + browserdriver);

	}

	Configuration con;

	@Before()
	public void setUp() {
		String environment=System.getProperty("environment","local");
		String browser=System.getProperty("browser", "chrome");
		browserDriver.setDriver(browser);
		driver =browserDriver.getDriver();
		driver.manage().window().maximize();
		con =new Configuration();
		con.setting(environment);


	}

	@After()
	public void getScreenShot(Scenario sc) {
		if(sc.isFailed()) {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			TakesScreenshot screenshot=(TakesScreenshot)driver;
			File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);
			
			File destFile=new File("./Screenshots/screenshot_"+timestamp+".jpg");
			try {
				FileUtils.copyFile(SrcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@After
	public void tearDown() {
		driver.quit();
	}

}
