#@acceptance
Feature: Customs Financial Home Page

  Background: 
    Given I am signed in as a Super user


  Scenario: Validate Title for Customs Financial Home Page
    When I navigate to the Customs Financials Home page
    Then the page title should be "Your customs financial accounts - Manage import duties and VAT accounts - GOV.UK"


  Scenario: Gov.uk link points to correct page
    Given I navigate to the Customs Financials Home page
    When I click on 'GOV.UK'
    Then the page title should be "Welcome to GOV.UK"


  Scenario Outline: Page Title, Heading, Company name and EORI number
    Given I am not signed in
    And I am signed in as a <User-type> user
    When I navigate to the Customs Financials Home page
    Then the page title should be "Your customs financial accounts - Manage import duties and VAT accounts - GOV.UK"
    And I should see the heading "Your import duties and VAT accounts"
    And I see the EORI and Company name '<Companyname-Eori>' on landing page
    Examples:
      | User-type     | Companyname-Eori                             |
      | Super         | Tony Stark - GB000000000000 , XI000000000000 |
      | noconsentEori | ABC Ltd - GB333186811543                     |

  #@test
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

  #@test
  Scenario: Display XI EORI Duty Deferment accounts on Customs Financials home page
    Given I am not signed in
    And I sign in as a XiEori user
    When I navigate to the Customs Financials Home page
    Then I should see my duty deferment accounts
      | dan                               | status                 | Account balance           | Direct debit info                               |
      | Account: 1234567                  |                        | £34,632 available         |                                                 |
      | Northern Ireland account: 6259461 |                        | £34,632 available         |                                                 |
      | Northern Ireland account: 6149488 |                        | £9,999.99 available       |                                                 |
      | Northern Ireland account: 6149461 |                        | £99,999.99 available      |                                                 |
      | Northern Ireland account: 6149462 |                        | £1,000 available          |                                                 |
      | Northern Ireland account: 6139486 | CLOSED                 | £0                        |                                                 |
      | Northern Ireland account: 6139508 | PENDING OPENING ON CDS | £10,000 will be available | Warning\nYou cannot use this account on CDS yet |
      | Northern Ireland account: 6139494 | ACTION REQUIRED        |                           | Warning\nUnable to use this account on CDS      |
    And I should see duty deferment account limits
      | Account Number                    | Account Limit            | Schemes text                       | Guarantee Limit             | Guarantee Remaining            |
      | Account: 1234567                  | Account limit: £60,000   | Including SIVA, EPSS or AEO status | Guarantee limit: £50,000    | Guarantee remaining: £10,000   |
      | Northern Ireland account: 6259461 | Account limit: £60,000   | Including SIVA, EPSS or AEO status | Guarantee limit: £50,000    | Guarantee remaining: £10,000   |
      | Northern Ireland account: 6149488 | Account limit: £9,999.99 | Including SIVA, EPSS or AEO status | Guarantee limit: £9,999.98  | Guarantee remaining: £9,999.98 |
      | Northern Ireland account: 6149461 |                          |                                    | Guarantee limit: £99,999.99 |                                |
      | Northern Ireland account: 6149462 | Account limit: £1,000    | Including SIVA, EPSS or AEO status |                             |                                |
      | Northern Ireland account: 6139486 |                          |                                    |                             |                                |
      | Northern Ireland account: 6139508 |                          |                                    |                             |                                |
      | Northern Ireland account: 6139494 |                          |                                    |                             |                                |

  #@test
  Scenario: Display duty deferment accounts enrolled in schemes
    Given I sign in as a defermentSchemes user
    When I navigate to the Customs Financials Home page
    Then I should see duty deferment account limits
      | Account Number       | Account Limit             | Schemes text                       | Guarantee Limit             | Guarantee Remaining             |
      | Account: 1234567     | Account limit: £60,000    | Including SIVA, EPSS or AEO status |                             |                                 |
      | Account: 6263456345  |                           |                                    | Guarantee limit: £99,999.99 |                                 |
      | Account: 12345678901 | Account limit: £99,999.99 | Including SIVA, EPSS or AEO status | Guarantee limit: £99,999.99 | Guarantee remaining: £57,149.39 |


  Scenario: Display Duty Deferment inaccurate balances message when the feature is enabled
    When I navigate to the Customs Financials Home page
    Then I should see the following duty deferment balances warning text
      | Balances include import declarations made using Customs Handling of Import and Export Freight (CHIEF) and the Customs Declarations Service (CDS). |

  #@test
  Scenario Outline: Duty Deferment card for regular and Isle of Man users on Customs Financials home page with account status
    When I sign in as a <user> user
    And I navigate to the Customs Financials Home page
    Then I should see the sub-heading "Duty deferment account"
    And I should see my duty deferment accounts
      | dan              | Status   | Available Account Balance   | Direct debit info                |
      | <account number> | <status> | <available account balance> | <direct debit setup information> |

    Examples:
      | user                                   | status          | account number   | available account balance | direct debit setup information             |
      | dutyDefermentAccountSuspendedStatus4   | ACTION REQUIRED | Account: 0005992 |                           | Warning\nUnable to use this account on CDS |

  #@test
  Scenario Outline: Direct debit content on Duty deferment account card with suspended status
    When I sign in as a <user> user
    And I navigate to the Customs Financials Home page
    Then I should see the following direct debit content
      | You must set up a new Direct Debit to be able to use this deferment account on Customs Declarations Service (CDS).        |
    And I should see the set up a new Direct Debit link
    Examples:
      | user                                 |
      | dutyDefermentAccountSuspendedStatus4 |


  #@test
  Scenario: Payment details link on Duty deferment account card
    When I navigate to the Customs Financials Home page
    Then I should see the Payment details link for account 6259461
    And the payment details link for account 6259461 should point to direct debit details page

  @test
  Scenario: Cash account card on Customs Financials home page with account status
    When I navigate to the Customs Financials Home page
    Then I should see the sub-heading "Cash account"
    And I should see my cash account with status
      | can                  | Status    | Available Account Balance |
      | Account: 10000008018 |           | £318,432.17 available     |
      | Account: 10000008019 |           | £950 available            |
      | Account: 10000008020 | SUSPENDED | £54,321.76 available      |
      | Account: 10000008021 | CLOSED    | £0                        |
      | Account: 10000008022 |           | £23,756.23 available      |
      | Account: 10000008023 |           | £123.01 available         |
