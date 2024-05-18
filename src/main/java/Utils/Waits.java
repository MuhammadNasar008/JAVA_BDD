package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    /**
     * Pauses the execution for 1 second.
     */
    public static void wait1s() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 2 seconds.
     */
    public static void wait2s() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 3 seconds.
     */
    public static void wait3s() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 4 seconds.
     */
    public static void wait4s() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 5 seconds.
     */
    public static void wait5s() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 6 seconds.
     */
    public static void wait6s() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for the specified amount of time.
     *
     * @param time time to wait in milliseconds
     */
    public static void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for the specified amount of time.
     *
     * @param time   time to wait in milliseconds
     * @param driver WebDriver instance
     */
    public static void waitTime(int time, WebDriver driver) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for a WebElement to be clickable.
     *
     * @param element         WebElement to wait for
     * @param timeOutInSeconds timeout in seconds
     * @param driver          WebDriver instance
     */
    public void waitForElementToBeClickable(WebElement element, int timeOutInSeconds, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for a WebElement to be visible.
     *
     * @param element         WebElement to wait for
     * @param timeoutInSeconds timeout in seconds
     * @param driver          WebDriver instance
     */
    public static void waitForElementVisibility(WebElement element, int timeoutInSeconds, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Pauses the execution for 10 seconds.
     */
    public static void waitFor10s() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 10 minutes.
     */
    public static void waitFor10min() {
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 3 seconds.
     */
    public static void waitFor3s() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 5 seconds.
     */
    public static void waitFor5s() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the execution for 1 second.
     */
    public static void waitFor1s() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
