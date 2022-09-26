package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/login.feature" }, // feature file // folder name
glue = { "LogintestStepDefination"},// "hooks" }, // step definition package name
//plugin = { "html:target/My/cucumber-html-report", "pretty", "json:target/MyReports/report.json" }, // reporting
monochrome = true, dryRun = false)



public class TestRunner {
	

}
