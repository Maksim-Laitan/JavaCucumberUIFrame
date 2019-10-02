package utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;

public class TestDriver {
    private WebDriver driver;
    private SupportedBrowsers type;
    private String mainTab;

    public TestDriver() {
        detectBrowserType();
        driverInitialization();
        setMainTab();
    }

    public enum SupportedBrowsers {
        CHROME,
        FIREFOX
    }

    private final void detectBrowserType() {
        String browserName = PropertiesUtils.getPropertyValueByKey("browser");
        switch (browserName.toLowerCase()) {
            case "chrome":
                type = SupportedBrowsers.CHROME;
                break;
            case "firefox":
                type = SupportedBrowsers.FIREFOX;
                break;
            default:
                throw new NotFoundException("Browser not found: " + browserName);
        }
    }

    private void driverInitialization(){
        System.out.format("Starting driver: %s. \n", String.valueOf(type));
        MutableCapabilities options = getOptions();
        try {
            String hubURL = PropertiesUtils.getPropertyValueByKey("HUB_URL");
                driver = new RemoteWebDriver(new URL(hubURL), options);
        } catch (WebDriverException | IOException e) {
            System.out.println("Error starting browser!");
            e.printStackTrace();
        }
    }

    private MutableCapabilities getOptions() {
        switch (type) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--log-level=3");
                chromeOptions.addArguments("--silent");
                chromeOptions.addArguments("--incognito");
                return chromeOptions;
            case FIREFOX:
                return new FirefoxOptions();
            default:
                throw new NotFoundException("Browser options not found: " + type);
        }
    }

    public WebDriver getDriver(){
        if (!isDriverOpened()) {
            driverInitialization();
            setMainTab();
        }
        return driver;
    }

    //TODO remove - moved to CustomWait class
    public WebDriverWait getWebDriverWait(){
        return new WebDriverWait(driver, 60);
    }

    //TODO remove if not needed local driver
    private void setPropertyForChromeDriver(){
        String chromeDriver = PropertiesUtils.getPropertyValueByKey("chromedriver");
        System.out.println(String.format("Driver place: %s", chromeDriver));
        System.setProperty("webdriver.chrome.driver", chromeDriver);
    }

    private boolean isDriverOpened() {
        return driver != null;
    }

    public void terminateDriver(){
        System.out.println("Terminating driver.");
        if (isDriverOpened()) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    public void setMainTab() {
        mainTab = driver.getWindowHandle();
    }

    public void closeAllTabsExceptMainTab() {
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                driver.close();
            }
        }
        driver.switchTo().window(mainTab);
    }
}
