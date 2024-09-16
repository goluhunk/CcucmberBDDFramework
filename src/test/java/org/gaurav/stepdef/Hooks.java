package org.gaurav.stepdef;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.gaurav.utils.BrowserDriver;
import org.gaurav.utils.Configuration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;


public class Hooks extends BrowserDriver {

	@BeforeAll()
	public static void setup() {
		//System.getProperty("browser");
		Configuration.settings(System.getProperty("environment"));
		BrowserDriver.getDriver(System.getProperty("browser"));
	}
	
	
	
	/*
	 * @Before("@Regression") public void setup2() {
	 * BrowserDriver.getDriver("firefox"); }
	 */
	@After()
	public void getScreenShot(Scenario sc) {
		if(sc.isFailed()) {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String file="./Screenshots/screenshot_"+timestamp+".jpg";
			TakesScreenshot screenshot=(TakesScreenshot)driver;
			File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);
			
			File destFile=new File(file);
			try {
				FileUtils.copyFile(SrcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

	/*
	 * @BeforeStep public void beforeStep() {
	 * System.out.println("This is before step ..."); }
	 * 
	 * @AfterStep public void afterStep() {
	 * System.out.println("This is after step ..."); }
	 */

}
