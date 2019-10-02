package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TestDriver;

public class GooglePage {

    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#hplogo")
    public WebElement googleLogo;

    public void clickOnLogo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        googleLogo.click();
//        googleLogo.isDisplayed();
    }
}
