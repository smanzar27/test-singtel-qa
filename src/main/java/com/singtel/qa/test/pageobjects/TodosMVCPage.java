package com.singtel.qa.test.pageobjects;

import com.singtel.qa.test.exception.InvalidUserInputException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.hotkey.Keys;

import java.util.ArrayList;
import java.util.List;


public class TodosMVCPage extends BaseWebBrowserService {

    WebDriver driver;

    public TodosMVCPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public static class TextMap {
        public static String defaultPageTitle= "Vue.js • TodoMVC";
    }

    @FindBy(how = How.XPATH, using = "//input[@class='new-todo']")
    protected WebElement elementText;
    @FindBy(how = How.CLASS_NAME, using = "todo-count")
    protected WebElement labelTodosCount;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='All']")
    protected WebElement linkALL;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Active']")
    protected WebElement linkActive;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Completed']")
    protected WebElement linkCompleted;
    @FindBy(how = How.XPATH, using = "//li[@class='todo']")
    protected List<WebElement> labelTodos;
    @FindBy(how = How.XPATH, using = "//li[@class='todo completed']")
    protected List<WebElement> labelCompleteTodos;
    @FindBy(how = How.CLASS_NAME, using = "clear-completed")
    protected WebElement buttonClearCompleted;

    @FindBy(how = How.XPATH, using = "//li[@class='todo editing']//input[@type='text']")
    protected WebElement editText;


    public void settingTodos(String todosTasks) {

        setTextFieldValue(elementText,todosTasks);
        pressEnter(elementText);
    } //end of settingTodos

    public String setTodoFilter(String filter) {

        switch (filter) {
            case "all"          :   clickOperation(linkALL);
                                    break;
            case "active"       :   clickOperation(linkActive);
                                    break;
            case "completed"    :   clickOperation(linkCompleted);
                                    break;
            default:  throw new InvalidUserInputException("filter should be either all or active or completed");
        }
        return getElementValue(labelTodosCount);
    } //end of getTodosCount

    public List<String> getTodos(boolean isCompleted) {

        List<String> todos = new ArrayList<>();
        if(isCompleted) {
            if (labelCompleteTodos.size() !=0)
                labelCompleteTodos.forEach(labelCompleteTodo -> todos.add(getElementValue(labelCompleteTodo)));
        } else {
            if (labelTodos.size() !=0)
                labelTodos.forEach(labelTodo -> todos.add(getElementValue(labelTodo)));
        }
        return todos;
    } //end of getTodos

    public void toggleAction(String src, String dest, String todo, boolean isCompleted) {

        WebElement element = null;
        if(isCompleted) {
            for (WebElement webElement : labelCompleteTodos) {
                if (getElementValue(webElement).equalsIgnoreCase(todo)) {
                    element = webElement;
                    break;
                }
            }
        } else {
            for (WebElement webElement : labelTodos) {
                if (getElementValue(webElement).equalsIgnoreCase(todo)) {
                    element = webElement;
                    break;
                }
            }
        }
        element.findElement(By.xpath("./child::div//child::input")).click();
    } //end of toggle action

    public void clearCompletedTodos(){
        clickOperation(buttonClearCompleted);
    }

    public void deleteAction(String todo, boolean isCompleted) {

        WebElement element = null;
        if(isCompleted) {
            for (WebElement webElement : labelCompleteTodos) {
                if (getElementValue(webElement).equalsIgnoreCase(todo)) {
                    element = webElement;
                    break;
                }
            }
        } else {
            for (WebElement webElement : labelTodos) {
                if (getElementValue(webElement).equalsIgnoreCase(todo)) {
                    element = webElement;
                    break;
                }
            }
        }
        mouseHover(element);
        element.findElement(By.xpath("./child::div//child::button")).click();
    } //end of toggle action

}

