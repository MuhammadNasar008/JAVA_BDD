package Utils;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class Utilities extends Waits {

	/**
	 * Waits for an element to be visible based on its XPath.
	 */
	public static void waitForElementVisibility(String xpath, String timeoutInSeconds, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(timeoutInSeconds)));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Waits for an element to be visible.
	 */
	public static void waitForElementVisibility(WebElement element, String timeoutInSeconds, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(timeoutInSeconds)));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Types a value into a given WebElement after waiting for its visibility and clickability.
	 */
	public void type(WebElement element, int timeInSecond, String value, WebDriver driver) {
		waitForElementVisibility(element, Integer.toString(timeInSecond), driver);
		waitForElementClickable(element, Integer.toString(timeInSecond), driver);
		scrollToElement(element, driver);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Waits for an element to be clickable.
	 */
	public static void waitForElementClickable(WebElement element, String timeoutInSeconds, WebDriver driver) {
		WebDriverWait waitClickable = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(timeoutInSeconds)));
		waitClickable.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Waits for an element to be clickable based on its XPath.
	 */
	public static void waitForElementClickable(String xpath, String timeoutInSeconds, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		WebDriverWait waitClickable = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(timeoutInSeconds)));
		waitClickable.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Types a value into a given WebElement.
	 */
	public static void type(WebElement element, String value, WebDriver driver) {
		waitUntilElementDisplayed(element, driver);
		element.clear();
		try {
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			element.sendKeys(Keys.DELETE);
			element.sendKeys(value);
		} catch (Exception e) {
			element.sendKeys(value);
		}
	}

	/**
	 * Types a value into a WebElement found by its XPath.
	 */
	public static void type(String xpath, String value, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		try {
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			element.sendKeys(Keys.DELETE);
			element.sendKeys(value);
		} catch (Exception e) {
			element.sendKeys(value);
		}
	}

	/**
	 * Presses the TAB key on an element found by its XPath.
	 */
	public static void pressTABKey(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.sendKeys(Keys.TAB);
	}

	/**
	 * Clears the text field of an element found by its XPath.
	 */
	public static void clearTextField(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		try {
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			element.sendKeys(Keys.DELETE);
		} catch (Exception e) {
			// Handle exception
		}
	}

	/**
	 * Gets the text of an element found by its XPath.
	 */
	public static String getText(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.getText();
	}

	/**
	 * Gets the value attribute of an element found by its XPath.
	 */
	public static String getValue(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.getAttribute("value");
	}

	/**
	 * Gets the innerHTML of a given WebElement.
	 */
	public static String getValue(WebElement element, WebDriver driver) {
		return element.getAttribute("innerHTML");
	}

	/**
	 * Gets the innerHTML attribute of an element found by its XPath.
	 */
	public static String getValueFromAttribute(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.getAttribute("innerHTML");
	}

	/**
	 * Gets the placeholder attribute of an element found by its XPath.
	 */
	public static String getPlaceHolder(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.getAttribute("placeholder");
	}

	/**
	 * Waits until a WebElement is displayed.
	 */
	public static void waitUntilElementDisplayed(final WebElement webElement, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					webElement.isDisplayed();
					return true;
				} catch (NoSuchElementException | StaleElementReferenceException e) {
					return false;
				}
			}
		};
		wait.until(elementIsDisplayed);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Clicks a given WebElement after waiting for its visibility and clickability.
	 */
	public static void click(WebElement element, WebDriver driver) {
		scrollIntoViewSmoothly(element, driver);
		waitForElementVisibility(element, "30", driver);
		waitForElementClickable(element, "20", driver);
		element.click();
		Waits.wait5s();
	}

	/**
	 * Clicks an element found by its XPath after waiting for its visibility and clickability.
	 */
	public static void click(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		scrollIntoViewSmoothly(element, driver);
		waitForElementVisibility(element, "30", driver);
		waitForElementClickable(element, "20", driver);
		element.click();
		Waits.wait5s();
	}

	/**
	 * Double-clicks an element found by its XPath after waiting for its visibility and clickability.
	 */
	public static void doubleClick(String xpath, WebDriver driver) {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(xpath));
		waitForElementVisibility(element, "30", driver);
		waitForElementClickable(element, "20", driver);
		actions.doubleClick(element).perform();
		Waits.wait5s();
		Waits.waitTime(10000);
	}

	/**
	 * Double-clicks a given WebElement after waiting for its visibility and clickability.
	 */
	public static void doubleClick(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		waitForElementVisibility(element, "30", driver);
		waitForElementClickable(element, "20", driver);
		actions.doubleClick(element).perform();
		Waits.wait5s();
		Waits.waitTime(10000);
	}

	/**
	 * Clicks an element found by its XPath using JavaScript.
	 */
	public static void clickJs(String xpath, WebDriver driver) {
		Waits.wait5s();
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		Waits.wait5s();
	}

	/**
	 * Sets an attribute of an element found by its XPath using JavaScript.
	 */
	public static void type(String xpath, WebDriver driver, String val, String attributeName) {
		Waits.wait5s();
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + val + "');", element);
		Waits.wait5s();
	}

	/**
	 * Scrolls smoothly to a given WebElement.
	 */
	public static void scrollIntoViewSmoothly(WebElement element, WebDriver driver) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", element);
	}

	/**
	 * Scrolls smoothly to an element found by its XPath.
	 */
	public static void scrollIntoViewSmoothly(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", element);
	}

	/**
	 * Scrolls to a given WebElement.
	 */
	public static void scrollToElement(WebElement element, WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -150)");
		if (!isElementDisplayed(element, driver)) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -100)");
		}
	}

	/**
	 * Scrolls to an element found by its XPath.
	 */
	public static void scrollToElement(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -150)");
		if (!isElementDisplayed(element, driver)) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -100)");
		}
	}

	/**
	 * Checks if an element found by its XPath is displayed.
	 */
	public static boolean isElementDisplayed(String xpath, WebDriver driver) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			return element.isDisplayed();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Checks if a given WebElement is displayed.
	 */
	public static boolean isElementDisplayed(WebElement element, WebDriver driver) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Verifies if the text of a given WebElement matches the expected text.
	 */
	public void verifyText(WebElement element, String expectedText) {
		String actualText = element.getText();
		if (actualText.equals(expectedText)) {
			System.out.println("Value Matches");
		} else {
			try {
				throw new Exception("Text does not match!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks if an element found by its XPath is disabled.
	 */
	public static boolean isDisabled(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return !element.isEnabled();
	}

	/**
	 * Checks if an element found by its XPath is read-only.
	 */
	public static boolean isDisabledCheckAttribute(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return Boolean.parseBoolean(element.getAttribute("readonly"));
	}

	/**
	 * Checks if an element found by its XPath is displayed.
	 */
	public static boolean isDisplayed(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.isDisplayed();
	}

	/**
	 * Selects a value from a dropdown by index.
	 */
	public static void selectValueFromDropdown(String xpath, int selectVal, WebDriver driver) {
		Select select = new Select(driver.findElement(By.xpath(xpath)));
		select.selectByIndex(selectVal);
		waitTime(10000);
	}

	/**
	 * Selects a value from a dropdown by visible text.
	 */
	public static void selectValueFromDropdownThroughText(String xpath, String selectVal, WebDriver driver) {
		Select select = new Select(driver.findElement(By.xpath(xpath)));
		List<WebElement> options = select.getOptions();
		for (int i = 1; i < options.size(); i++) {
			WebElement option = select.getFirstSelectedOption();
			String value = option.getText().trim();
			if (value.equals(selectVal.trim())) {
				break;
			}
			select.selectByIndex(i);
		}
		waitTime(10000);
	}

	/**
	 * Waits until an element disappears.
	 */
	public void waitForElementDisappear(String xpath, WebDriver driver) throws InterruptedException {
		int count = 0;
		while (true) {
			try {
				if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
					Thread.sleep(10000);
				}
			} catch (NoSuchElementException e) {
				break;
			}
			count++;
			if (count == 40) {
				break;
			}
		}
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			waitUntilElementDisplayed(element, driver);
		} catch (Exception e) {
			// Handle exception
		}
	}

	/**
	 * Reformats a date from one format to another.
	 */
	public static String reformatDate(String dateToFormat, String preFormat, String postFormat) throws ParseException {
		DateFormat srcDf = new SimpleDateFormat(preFormat);
		Date date = srcDf.parse(dateToFormat);
		DateFormat destDf = new SimpleDateFormat(postFormat);
		return destDf.format(date);
	}

	/**
	 * Takes a screenshot and adds it to the Allure report.
	 */
	public static void screenshot(WebDriver driver) {
		waitTime(1000);
		Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		System.out.println("Added screenshot after each step");
	}
}
