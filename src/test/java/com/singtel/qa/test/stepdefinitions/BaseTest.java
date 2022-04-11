package com.singtel.qa.test.stepdefinitions;

import com.singtel.qa.test.configs.ReaderManager;
import com.singtel.qa.test.drivers.DriverFactory;
import com.singtel.qa.test.drivers.DriverManager;
import com.singtel.qa.test.pageobjects.PageObjectManager;

public class BaseTest {

    protected final DriverManager driverManager;
    protected final PageObjectManager pageObjectManager;

    public BaseTest() {

        System.out.println("Test Base Setting Started");
        String browserName = ReaderManager.getInstance().getGUIConfigReader().getBrowser();
        System.out.println( "Running Browser: " + browserName);
        driverManager = DriverFactory.getInstance().getDriverManager(browserName.toUpperCase());
        pageObjectManager = new PageObjectManager(driverManager.getWebDriver());
        System.out.println("Test Base Setting Completed");

    }

    public DriverManager getWebDriverManager() { return driverManager; }
    public PageObjectManager getPageObjectManager() { return pageObjectManager; }
}
