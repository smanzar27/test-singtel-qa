package com.singtel.qa.test.pageobjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver driver;

    private TodosMVCPage todosPage;

    public PageObjectManager(WebDriver driver) { this.driver = driver; }
    public TodosMVCPage getTodosPage(){ return (todosPage == null) ? todosPage = new TodosMVCPage(driver) : todosPage; }

}
