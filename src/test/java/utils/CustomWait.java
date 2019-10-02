package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWait {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final int WAIT_TIME = 5;

    public CustomWait(TestDriver testDriver) {
        this.driver = testDriver.getDriver();
    }

    public static WebDriverWait getWebDriverWait() {
        return getWebDriverWait(WAIT_TIME);
    }

    public static WebDriverWait getWebDriverWait(int time) {
        return new WebDriverWait(driver, time);
    }

    /**
     * WAIT with WebElement parameter
     */

    public static void waitElementIsVisible(WebElement element) {
        waitElementIsVisible(element, WAIT_TIME);
    }

    public static void waitElementIsVisible(WebElement element, int time) {
        wait = getWebDriverWait(time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitTextIsPresentInElement(WebElement element, String text) {
        waitTextIsPresentInElement(element, text, WAIT_TIME);
    }

    public static void waitTextIsPresentInElement(WebElement element, String text, int time) {
        waitElementIsVisible(element, time);
        wait = getWebDriverWait(time);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitElementIsClickable(WebElement element) {
        waitElementIsClickable(element, WAIT_TIME);
    }

    public static void waitElementIsClickable(WebElement element, int time) {
        waitElementIsVisible(element, time);
        wait = getWebDriverWait(time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitElementIsSelectable(WebElement element) {
        waitElementIsSelectable(element, WAIT_TIME);
    }

    public static void waitElementIsSelectable(WebElement element, int time) {
        waitElementIsVisible(element, time);
        wait = getWebDriverWait(time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * WAIT with locator parameter
     */

    public static void waitElementByLocatorIsVisible(String locator) {
        waitElementIsVisible(getWebElementByLocator(locator));
    }

    public static void waitElementByLocatorIsVisible(String locator, int time) {
        waitElementIsVisible(getWebElementByLocator(locator), time);
    }

    public static void waitTextIsPresentInElementByLocator(String locator, String text) {
        waitTextIsPresentInElement(getWebElementByLocator(locator), text);
    }

    public static void waitTextIsPresentInElementByLocator(String locator, String text, int time) {
        waitTextIsPresentInElement(getWebElementByLocator(locator), text, time);
    }

    public static void waitElementByLocatorIsClickable(String locator) {
        waitElementIsClickable(getWebElementByLocator(locator));
    }

    public static void waitElementByLocatorIsClickable(String locator, int time) {
        waitElementIsClickable(getWebElementByLocator(locator), time);
    }

    public static void waitElementByLocatorIsSelectable(String locator) {
        waitElementIsSelectable(getWebElementByLocator(locator));
    }

    public static void waitElementByLocatorIsSelectable(String locator, int time) {
        waitElementIsSelectable(getWebElementByLocator(locator), time);
    }

    private static WebElement getWebElementByLocator(String locator) {
        if (locator.startsWith(".//") || locator.startsWith("./") || locator.startsWith("//") || locator.startsWith("/")) {
           return driver.findElement(By.xpath(locator));
        }
        return driver.findElement(By.cssSelector(locator));
    }
}
