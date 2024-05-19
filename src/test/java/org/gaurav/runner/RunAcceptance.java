package org.gaurav.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features",
		glue="org.gaurav.stepdef",
		dryRun=false,
		monochrome=true,
		plugin= {"pretty","json:Test-Reports/cucumber.json","html:Test-Reports/cucumber.html"
				,"rerun:target/failed.txt"}
		,tags="@test"
		)

public class RunAcceptance {
	

}
