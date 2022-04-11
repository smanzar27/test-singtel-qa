@QATest
Feature: Singtel QA Test
Description: testing assignment for syntel qa test

  Background:
    Given the user navigates to TodosMVC home page

  Scenario: verify newly created todos are always in active state
    When the user create todos entry in the system
      | Task One   |
      | Task Two   |
      | Task Three |
    Then the user validates all todos are marked as active
    And the user validates list of active todos sizes
    And the user validates no todos are marked as completed

  Scenario: verify todos can be toggle from active to complete and vice-versa
    When the user create todos entry in the system
      | Task One   |
      | Task Two   |
      | Task Three |
      | Task Four  |
    And the user validates list of active todos sizes
    Then the user toggles following todos from active to completed
      | Task Two  |
      | Task Four |
    And the user validates decrease in active todos sizes once completed
    And the user validates above todos are marked as completed
    And the user validates both active and completed todos are listed under all tab
    Then the user toggles following todos from completed to active
      | Task Two  |
      | Task Four |
    And the user validates increase in active todos sizes once re-active
    And the user validates all todos are marked as active

  Scenario: verify on clearing todos only active todos remains in the system
    When the user create todos entry in the system
      | Task One   |
      | Task Two   |
      | Task Three |
      | Task Four  |
    And the user toggles following todos from active to completed
      | Task Two  |
      | Task Four |
    Then the user clicks on clear completed todos button
    And the user validates no todos are marked as completed
    And the user validates remaining todos are marked as active

  Scenario: verify an active and a completed todo is deleted
    When the user create todos entry in the system
      | Task One   |
      | Task Two   |
      | Task Three |
      | Task Four  |
    And the user toggles following todos from active to completed
      | Task Two  |
      | Task Four |
    Then the user delete a todo of the active todos
    And the user validates an active todo has been deleted along with decrease item left value
    Then the user delete a todo of the completed todos
    And the user validates a completed todo has been deleted along with no change in item left value

