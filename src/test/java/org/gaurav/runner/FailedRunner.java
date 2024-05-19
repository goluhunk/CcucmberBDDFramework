package org.gaurav.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="@target/failed.txt",
		glue="org.gaurav.stepdef",
		dryRun=false,
		monochrome=true,
		plugin= {"pretty","json:Test-Reports/cucumber.json","html:Test-Reports/cucumber.html"}
		,tags="@Custom"
		)

public class FailedRunner {
	

}
