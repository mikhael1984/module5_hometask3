Feature: WKTS test

  Scenario: Login to WKTS
    Given I am at the start page
    When I perform login
    Then I am redirected to home page

  Scenario: Open blacklist menu item
    Given I am at the WKTS home page
    When I click Blacklisted menu item
    Then Blacklist page open

  Scenario: Remove company from Blacklist
    Given I am at the Blacklist page
    When I remove company from Blacklist
    Then Company is not shown in the list

  Scenario: Search for unblocked company
    Given I am still  at the Blacklist page
    When I type company name in search string
    Then the company not found