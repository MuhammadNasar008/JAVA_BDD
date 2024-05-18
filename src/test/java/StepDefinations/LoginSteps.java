package StepDefinations;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import Constants.Constants;
import Pages.LoginPage;
import Utils.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import io.qameta.allure.Allure;

import org.apache.commons.io.FileUtils;

public class LoginSteps extends BaseClass {

	LoginPage loginPage = new LoginPage(driver);
	
	public Constants loginconstant;

	@Given("^Visit the app url$")
	public void user_is_on_login_page() throws InterruptedException {
		driver = initConfiguration();
		loginPage.navigateToUrl(driver, loginconstant.url);
		System.out.println("Welcome To Login Page");
	}
	@After
	public void tearDown() {
		driver.close();
	}

}
