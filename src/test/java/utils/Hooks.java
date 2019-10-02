package utils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    private TestDriver testDriver;

    public Hooks(TestDriver testDriver){
        this.testDriver = testDriver;
    }

    @Before
    public void setup(Scenario scenario){
        System.out.println("In the Setup method.");
        System.out.println("Scenario name: " + scenario.getName());
        scenario.getSourceTagNames().forEach(tag -> System.out.println(tag));
    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("In the TearDown method.");
        System.out.println("Status: " + scenario.getStatus());
        testDriver.terminateDriver();
    }
}
