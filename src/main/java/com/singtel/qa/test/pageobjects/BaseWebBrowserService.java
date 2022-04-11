package com.singtel.qa.test.pageobjects;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseWebBrowserService {

    WebDriver driver;

    public BaseWebBrowserService(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setTextFieldValue(WebElement element, String textString) {
        explicitWaitUntilVisibility(element);
        clearString(element);
        element.sendKeys(textString);
    }

    public String getElementValue(WebElement element) {
        return element.getText();
    }

    public void pressEnter(WebElement element) {
        element.sendKeys((Keys.ENTER));
    }

    public void clearString(WebElement element) {
        element.clear();
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        if (isElementDisplayed(element)) return element.getAttribute(attributeName);
        return null;
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public void clickOperation(WebElement element) {
        explicitWaitUntilVisibility(element);
        element.click();
    }

    protected void explicitWaitUntilVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void switchToFrame(WebElement element) {
        explicitWaitUntilVisibility(element);
        driver.switchTo().frame(element);
    }

    public void mouseHover(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).perform();
    }

    public void doubleClick(WebElement webElement) {
        new Actions(driver).doubleClick(webElement).perform();
    }
}
