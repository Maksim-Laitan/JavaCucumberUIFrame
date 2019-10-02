package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utils.TestDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleMainPageSteps {
    private TestDriver testDriver;

    public GoogleMainPageSteps(TestDriver testDriver){
        this.testDriver = testDriver;
    }

    @Given("^I navigated to (.*?) main page$")
    public void iNavigatedToGoogleMainPage(String site){
        String url = null;
        switch (site){
            case "Google": url = "https://google.com";
            break;
            case "Delfi": url = "https:/rus.delfi.lv";
            break;
        }
        testDriver.navigateToUrl(url);
    }

    @Then("^I see that title should be (.*?)$")
    public void seeThatTitleShouldBe(String titleExpected){
        String titleActual = testDriver.getDriver().getTitle();

        assertEquals(titleExpected, titleActual);
    }
}
