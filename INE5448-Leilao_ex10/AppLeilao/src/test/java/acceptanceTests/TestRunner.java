package acceptanceTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "acceptanceTests.cucumberSteps", 
features = "AppLeilao/src/test/java/features",
monochrome = true/*,
dryRun = true*/)
public class TestRunner {
	
}
