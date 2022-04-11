package com.singtel.qa.test.stepdefinitions;


import com.singtel.qa.test.drivers.DriverManager;
import com.singtel.qa.test.utils.Constant;
import com.singtel.qa.test.utils.FileOperation;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;

import java.util.List;

public class Hooks {

    DriverManager driverManager;

    public Hooks(BaseTest baseTest){
        driverManager = baseTest.getWebDriverManager();
    }

    @Before
    public void beforeScenario(Scenario scenario) throws InterruptedException {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        scenario.log("STARTING TEST: " + scenario.getName());
        System.out.println(scenario.getSourceTagNames());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

        driverManager.getWebDriver();
        driverManager.maximizeWindowWebDriver();
        driverManager.includeImplicitWait();
        Thread.sleep(5000);
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        scenario.log(("COMPLETE TEST: " + scenario.getName()));
        driverManager.quitDriver();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @BeforeStep
    public void beforeStep(Scenario scenario)  {
        FileOperation.createFile(Constant.CUCUMBER_STEP);
    }

    @AfterStep
    public void afterStep(Scenario scenario){

        driverManager.onFailureTakeScreenShot(scenario);
        List<String> listStringLine = FileOperation.readFile(Constant.CUCUMBER_STEP);
        listStringLine.forEach(scenario::log);
        FileOperation.deleteFile(Constant.CUCUMBER_STEP);

    }
}
