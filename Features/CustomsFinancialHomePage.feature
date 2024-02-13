@Custom
Feature: Customs Financial Home Page

  Background: 
    Given I am signed in as a registered user

  Scenario: Validate Title for Customs Financial Home Page
    When I navigate to the Customs Financials Home page
    Then the page title should be "Your customs financial accounts - View your customs financial accounts - GOV.UK"

  Scenario: Gov.uk link points to correct page
    Given I navigate to the Customs Financials Home page
    When I click on 'GOV.UK'
    Then the page title should be "Welcome to GOV.UK"

  @cash
  Scenario: Cash Account card on Customs Financial Home Page with account status
    Given I navigate to the Customs Financials Home page
    Then I should see the sub-heading "Cash account"
    And I should see my Cash account with status
      | can                  | Status    | Avilable Account Balance |
      | Account: 10000008018 |           | £318,432.17 available    |
      | Account: 10000008019 |           | £950 available           |
      | Account: 10000008020 | SUSPENDED | £54,321.76 available     |
      | Account: 10000008021 | CLOSED    | £0                       |
      | Account: 10000008022 |           | £23,756.23 available     |
      | Account: 10000008023 |           | £123.01 available        |

  @dda
  Scenario: Duty Deferment Account card on Customs Financial Home Page with account status
    Given I navigate to the Customs Financials Home page
    Then I should see the sub-heading "Duty deferment accounts"


  @gga
  Scenario: General guarantee account card on Customs Financial Home Page with account status
    Given I navigate to the Customs Financials Home page
    Then I should see the sub-heading "General guarantee account"
