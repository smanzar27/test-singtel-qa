package com.singtel.qa.test.stepdefinitions.syntel;

import com.singtel.qa.test.drivers.DriverManager;
import com.singtel.qa.test.exception.InvalidUserInputException;
import com.singtel.qa.test.pageobjects.TodosMVCPage;
import com.singtel.qa.test.stepdefinitions.BaseTest;
import com.singtel.qa.test.utils.HardAssertion;
import com.singtel.qa.test.utils.SoftAssertion;
import com.singtel.qa.test.utils.UtilityOperation;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;


public class TodosMVCStep {

    DriverManager driverManager;
    TodosMVCPage todosMVCPage;

    int activeItems=0;
    List<String> todos = new ArrayList<>();
    List<String> todosActions = new ArrayList<>();
    List<String> activeTodos = new ArrayList<>(todos);
    List<String> completeTodos = new ArrayList<>(todosActions);


    public TodosMVCStep(BaseTest baseTest) {
        driverManager = baseTest.getWebDriverManager();
        todosMVCPage = baseTest.getPageObjectManager().getTodosPage();
    }

    public String getActiveLabel(){
        return todosMVCPage.setTodoFilter("all");
    }
    public int getActiveItems(){
        return UtilityOperation.extractInteger(getActiveLabel());
    }

    @Given("the user navigates to TodosMVC home page")
    public void the_user_navigates_to_TodosMVC_home_page() {
        driverManager.navigateToApplicationURL();
        driverManager.waitForFullPageLoad(driverManager.getWebDriver());
        System.out.println(driverManager.getTitle());
        HardAssertion.assertCompareString(driverManager.getTitle(), TodosMVCPage.TextMap.defaultPageTitle,"validated page title");
    }

    @When("^the user create todos entry in the system$")
    public void entryTodos(DataTable dataTable)  {

        todos = dataTable.asList();
        todos.forEach(todo ->  todosMVCPage.settingTodos(todo));
    }

    @Then("the user validates list of active todos sizes")
    public void pendingTodos() {

        String actualItemCount = getActiveLabel();
        activeItems= getActiveItems();
        HardAssertion.assertCompareInteger(activeItems, todos.size(), actualItemCount);

    }

    @Then("^the user validates (:?increase) in active todos sizes once re-active")
    @And("^the user validates (:?decrease) in active todos sizes once completed$")
    public void pendingTodosAction(String actionType) {

        String actualItemCount = getActiveLabel();
        int actualItem = getActiveItems();
        List<String> copyTodos = new ArrayList<>(todos);
        List<String> copyTodosActions = new ArrayList<>(todosActions);

        switch (actionType){
            case "decrease" :
                HardAssertion.assertCompareInteger(activeItems - actualItem, copyTodosActions.size(), actualItemCount);
                break;
            case "increase" :
                HardAssertion.assertCompareInteger(activeItems , copyTodos.size(), actualItemCount);
                break;

            default : throw new InvalidUserInputException("invalid test data");
        }

    }


    @Then("^the user validates (.+) todos are marked as (.+)")
    public void validateTodos(String counts, String filter) {

        SoftAssertion softAssertion = new SoftAssertion();
        List<String> copyTodos = new ArrayList<>(todos);
        List<String> copyTodosActions = new ArrayList<>(todosActions);
   //     Collections.sort(sortedCopy);
        todosMVCPage.setTodoFilter("all");
        switch (counts){
            case "all" :
                softAssertion.assertCompareObject(todosMVCPage.getTodos(false), copyTodos, filter + " todos -> " + todos);
                break;
            case "no" :
                softAssertion.assertCompareObject(todosMVCPage.getTodos(true), new ArrayList<>(), filter + " todos -> " + new ArrayList<>());
                break;
            case "above" :
                softAssertion.assertCompareObject(todosMVCPage.getTodos(true), copyTodosActions, filter + " todos -> " + todosActions);
                break;
            case "remaining"    :
                copyTodos.removeAll(copyTodosActions);
                softAssertion.assertCompareObject(todosMVCPage.getTodos(false), copyTodos, filter + " todos -> " + copyTodos);
                break;
            default : throw new InvalidUserInputException("invalid test data");
        }
        softAssertion.assertAll();
    }

    @When("^the user toggles following todos from (.+) to (.+)$")
    public void toggleAction(String src, String dest, DataTable dataTable){

        todosActions = dataTable.asList();
        if(dest.equalsIgnoreCase("completed"))
            todosActions.forEach(toggleAction -> todosMVCPage.toggleAction(src, dest,toggleAction,false));
        if(dest.equalsIgnoreCase("active"))
            todosActions.forEach(toggleAction -> todosMVCPage.toggleAction(dest, src,toggleAction,true));
    }

    @When("the user validates both active and completed todos are listed under all tab")
    public void toggleAction(){

        List<String> sortedCopy = new ArrayList<>(todos);
        Collections.sort(sortedCopy);

        todosMVCPage.setTodoFilter("all");
        List<String> actualResult = new ArrayList<>();
        actualResult.addAll(todosMVCPage.getTodos(false));
        actualResult.addAll(todosMVCPage.getTodos(true));

        Collections.sort(actualResult);
        HardAssertion.assertCompareObject(sortedCopy,actualResult,"both active and complete list");
    }

    @When("the user clicks on clear completed todos button")
    public void clearTodos(){
        todosMVCPage.clearCompletedTodos();
    }

    @When("^the user delete a todo of the (:?active|completed) todos$")
    public void deleteOperation(String operation){

        activeItems = getActiveItems();
        activeTodos = new ArrayList<>(todos);
        completeTodos = new ArrayList<>(todosActions);
        activeTodos.removeAll(completeTodos);

        switch (operation){
            case "active" :
                System.out.println(activeTodos);
                todosMVCPage.deleteAction(activeTodos.get(0),false);
                activeTodos.remove(activeTodos.get(0));
                break;
            case "completed" :
                System.out.println(completeTodos);
                todosMVCPage.deleteAction(completeTodos.get(0),true);
                completeTodos.remove(completeTodos.get(0));
                break;
            default : throw new InvalidUserInputException("invalid test data");
        }
    }

    @When("^the user validates a (:?completed) todo has been deleted along with no change in item left value$")
    @When("^the user validates an (:?active) todo has been deleted along with decrease item left value$")
    public void deleteVerification(String operation){

        SoftAssertion softAssertion = new SoftAssertion();
        int latestActiveItems = getActiveItems();

        switch (operation){
            case "active" :
               softAssertion.assertCompareInteger(latestActiveItems, activeItems-1,"an item left decease by one");
               softAssertion.assertCompareObject(todosMVCPage.getTodos(false), activeTodos, operation + " todos -> " + activeTodos);
                break;
            case "completed" :
                softAssertion.assertCompareInteger(latestActiveItems, activeItems,"no change in item left value");
                softAssertion.assertCompareObject(todosMVCPage.getTodos(true), completeTodos, operation + " todos -> " + activeTodos);
                break;
            default : throw new InvalidUserInputException("invalid test data");
        }
        softAssertion.assertAll();
    }
}
