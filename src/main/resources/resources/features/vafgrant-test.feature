@CityTemperature
Feature: Vafgrant QA Test
Description: Testing assignment for vafgrant qa test

  Scenario Outline: verify city current temperature for <CityName>
    Given the user navigates to AccuWeather home page
    When the user enters <CityName>, and clicks on the first suggested city list displayed from drop-down
    And  the user fetches <CityName> current temperature form current weather card
 #   And the user clicks on more details to get complete <CityName> current weather information
    And the user prepare api request and execute it to get current weather information for <CityName>
    And the user validates current weather response returns status_code as 200 along with <CityName> current temperature
    Then the user compare <CityName> temperature return by api and gui for its equality or acceptable variance as <VarianceRange>
    Examples:
      | CityName    | VarianceRange |
      | Singapore   | 1.0           |
      | Kolkata     | 1.5           |
      | London      | 0.1           |
      | New York    | 0.3           |
      | Islamabad   | 3             |