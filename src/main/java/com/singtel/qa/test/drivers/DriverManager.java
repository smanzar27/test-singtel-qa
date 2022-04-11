package com.singtel.qa.test.drivers;

import com.singtel.qa.test.configs.ReaderManager;
import com.singtel.qa.test.exception.InvalidUserInputException;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public abstract class DriverManager {

    protected WebDriver driver;
    protected abstract WebDriver createWebDriver();
    protected abstract void setDefaultCapabilities();

    public WebDriver getWebDriver(){
        if(null == driver) createWebDriver();
        return driver;
    }

    public void includeImplicitWait() throws InvalidUserInputException {
        driver.manage().timeouts().implicitlyWait(setDefaultImplicitWaitTime());
    }

    public void quitDriver(){
        if(null != driver) {
        driver.close();
        driver.quit();
        }
    }


    public void maximizeWindowWebDriver(){ driver.manage().window().maximize(); }
    public Duration setDefaultImplicitWaitTime()  {  return Duration.ofSeconds(ReaderManager.getInstance().getGUIConfigReader().getDefaultWaitTime()); }

    public void navigateToApplicationURL()  {
        driver.navigate().to(constructURL());
        waitForFullPageLoad(driver);
    }
    public String getTitle(){ return driver.getTitle(); }

    public URL constructURL()  {

        String urlString=ReaderManager.getInstance().getGUIConfigReader().getApplicationWebURL();
        try {
            return  new URL(urlString);
        }   catch (Exception ex) {
            throw new InvalidUserInputException("user entered invalid in configuration: " +  urlString);
        }
    }

    public void onFailureTakeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.log(scenario.getName()+ " Failed");
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    public void waitForFullPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = pageDriver -> ((JavascriptExecutor) pageDriver).executeScript("return document.readyState").equals("complete");
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(ReaderManager.getInstance().getGUIConfigReader().getDefaultWaitTime()));
        driverWait.until(pageLoadCondition);
    }
}
