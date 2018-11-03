@googleSearch
Feature: Google Feature

  Scenario: TC1 Search for a keyword in Google Home page

    Given I have navigated to Google home page
    When I enter verify google title matches with expected
    When I enter a keyword and click on google search
    Then I can see and capture search result on search page

