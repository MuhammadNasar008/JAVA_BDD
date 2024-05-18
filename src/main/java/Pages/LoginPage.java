package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Constants.Constants;

import java.io.File;
import java.io.IOException;

import Utils.BaseClass;
import org.junit.Assert;
import static org.junit.Assert.*;

public class LoginPage extends BaseClass {
	private WebDriver podriver = null;

	String loginPageTitle = "//img[contains(@class,'img-')]";
	String loginUserNameTxt = "//input[@name='username']";
	

	public LoginPage(WebDriver driverParam) {
		this.podriver = driverParam;
		PageFactory.initElements(this.podriver, this);
	}

	public void navigateToUrl(WebDriver driver, String Url) throws InterruptedException {
		driver.get(Url);

	}
}
