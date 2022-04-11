@Number @TestMe
Feature: Number Operation
  Description: Add Two Numbers

  Scenario Outline: Add Two Given Number
    Given the user passes two input numbers: <numberOne> and <numberTwo>
    When the user performs addition operation on numbers
    Then the user validate addition result
    Examples:
    | numberOne | numberTwo |
    | 2         | 3         |