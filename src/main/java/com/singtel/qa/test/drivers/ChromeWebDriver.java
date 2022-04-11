package com.singtel.qa.test.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriver extends DriverManager {

    ChromeOptions chromeOptions = new ChromeOptions();

    ChromeWebDriver() { }

    @Override
    protected WebDriver createWebDriver() {
        this.setDefaultCapabilities();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    @Override
    protected void setDefaultCapabilities() {
        this.chromeOptions.addArguments("disable-infobars");
        this.chromeOptions.addArguments("--disable-notification");
        this.chromeOptions.addArguments("disable-extension");
        this.chromeOptions.addArguments("start-maximized");
        this.chromeOptions.addArguments("enable-automation");
        this.chromeOptions.addArguments("--test-type");
        this.chromeOptions.addArguments("--disable-gpu");
        this.chromeOptions.addArguments("--no-sandbox");
        this.chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    }
}
