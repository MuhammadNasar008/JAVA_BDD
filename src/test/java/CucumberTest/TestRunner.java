package CucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/Features/Login/Login.feature"}, glue = {
		"StepDefinations" }, monochrome = true,
		plugin = { "pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })
public class TestRunner {


}
