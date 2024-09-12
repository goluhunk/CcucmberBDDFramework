#@Custom
Feature: Customs Financial Home Page

  Background: 
    Given I am signed in as a Super user

  Scenario: Validate Title for Customs Financial Home Page
    When I navigate to the Customs Financials Home page
    Then the page title should be "Your customs financial accounts - View your customs financial accounts - GOV.UK"

  Scenario: Gov.uk link points to correct page
    Given I navigate to the Customs Financials Home page
    When I click on 'GOV.UK'
    Then the page title should be "Welcome to GOV.UK"
@test
  Scenario: Duty Deferment accounts overview on Customs Financials home page
    When I navigate to the Customs Financials Home page
    Then I should see my duty deferment accounts
      | dan              | status                 | Account balance           | Direct debit info                               |
      | Account: 6259461 |                        | £34,632 available         |                                                 |
      | Account: 6149488 |                        | £999,999,999.99 available |                                                 |
      | Account: 6149461 |                        | £999,999,999.99 available |                                                 |
      | Account: 6149462 |                        | £1,000 available          |                                                 |
      | Account: 6139486 | CLOSED                 | £0                        |                                                 |
      | Account: 6139508 | PENDING OPENING ON CDS | £10,000 will be available | Warning\nYou cannot use this account on CDS yet |
      | Account: 6139494 | ACTION REQUIRED        |                           | Warning\nUnable to use this account on CDS      |
    And I should see duty deferment account limits
      | Account Number   | Account Limit                  | Schemes text                       | Guarantee Limit                  | Guarantee Remaining                  |
      | Account: 6259461 | Account limit: £60,000         | Including SIVA, EPSS or AEO status | Guarantee limit: £50,000         | Guarantee remaining: £10,000         |
      | Account: 6149488 | Account limit: £999,999,999.99 | Including SIVA, EPSS or AEO status | Guarantee limit: £999,999,999.98 | Guarantee remaining: £999,999,999.98 |
      | Account: 6149461 |                                |                                    | Guarantee limit: £999,999,999.99 |                                      |
      | Account: 6149462 | Account limit: £1,000          | Including SIVA, EPSS or AEO status |                                  |                                      |
      | Account: 6139486 |                                |                                    |                                  |                                      |
      | Account: 6139508 |                                |                                    |                                  |                                      |
      | Account: 6139494 |                                |                                    |                                  |                                      |


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
