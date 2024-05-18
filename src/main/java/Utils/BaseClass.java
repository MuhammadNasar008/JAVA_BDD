package Utils;

import java.io.File;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends Utilities {
	// Static variables for browser, WebDriver instance, and file paths
	public static String browser;
	public static WebDriver driver;
	public static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator;
	public static String propertiesFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;

	// Method to initialize WebDriver configuration based on the browser type
	public static WebDriver initConfiguration() {
		WebDriver localD = null;
		browser = System.getenv("browser");
		System.out.println("Browser: " + browser);
		System.out.println("OS : " + System.getProperty("os.name"));
		System.out.println("User Dir : " + System.getProperty("user.dir"));

		// If the browser is not set in the environment variables, read from properties file
		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
			browser = System.getenv("browser");
			System.out.println("Browser: " + browser);
		} else {
			browser = PropertiesReader.getPropertyValue("browser");
		}

		// Initialize the WebDriver based on the specified browser
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "gecko.exe");
			localD = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			// Configure ChromeDriver with necessary options
			WebDriverManager.chromedriver().clearDriverCache().setup();
			WebDriverManager.chromedriver().clearResolutionCache().setup();
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator + "ExcelFile");
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			options.addArguments("window-size=1920,1080");
			options.addArguments("start-maximized"); // Open Browser in maximized mode
			options.addArguments("disable-infobars"); // Disabling infobars
			options.addArguments("--disable-extensions"); // Disabling extensions
			options.addArguments("--disable-gpu"); // Applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
			options.addArguments("--no-sandbox");

			try {
				localD = new ChromeDriver(options);
			} catch (Exception e) {
				e.printStackTrace();
			}
			localD.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			localD.manage().window().maximize();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			localD = new InternetExplorerDriver();
		}
		localD.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
		return localD;
	}

	// Method to close the browser
	public void CloseBrowser(WebDriver driver) {
		driver.close();
	}

	// Method to quit the browser
	public void QuitBrowser(WebDriver driver) {
		driver.quit();
	}

	// Method to navigate back in the browser history
	public void goBack(WebDriver driver) {
		driver.navigate().back();
	}

	// Method to get the initialized WebDriver instance
	public WebDriver getDriver() {
		return initConfiguration();
	}

	// Method to check if an element is clickable
	public static boolean isClickable(String xpath, WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Method to switch the window handle
	public static void shiftWindowHandle(int val) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(val));
	}

	// Method to generate a random number string of a given length
	public String randomNumberString(int len) {
		String AB = "123456789";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	// Method to hover over an element and then click another element
	public void hoverAndClick(String xpathOfHover, String xpathOfClick) {
		WebElement elementOfHover = driver.findElement(By.xpath(xpathOfHover));
		WebElement elementOfClick = driver.findElement(By.xpath(xpathOfClick));
		Actions builder = new Actions(driver);
		builder.moveToElement(elementOfHover).perform();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// Handle interruption
		}
		builder.moveToElement(elementOfClick).click().perform();
	}
}
