@Sanity
Feature: Duty Deferment Account Details

  Background:
    Given I am signed in as a registered user

  Scenario: Navigation between landing page and DD account details page
    When I navigate to the Customs Financials Home page
    Then the page title should be "Your customs financial accounts - View your customs financial accounts - GOV.UK"
    And I click on the View link for dan account "Account: 1234567"
    Then I should see the heading caption "Account: 1234567"
