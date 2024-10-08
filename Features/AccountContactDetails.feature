
Feature: Contact Account Details Page Scenarios
  Background:
    Given I am signed in as a registered user
    And I navigate to the Customs Financials Home page
    And I click on 'Your contact details'

  Scenario: Display The Account Details Page
    When I click on Account details link for "1234567"
    Then I should see the sub-heading "Account: 1234567"
    Then I should see the heading "Duty deferment contact details"
    And I should see the following contact details
      | Name           | Address                                                     | Telephone   | Email address    |
      | Contact Name 1 | Street 1,District A,County A,City 1,AB12 3CD,United Kingdom | 01111111111 | email1@gmail.com |

  Scenario: Navigation back to Customs Financials home page from Account details page
    When I click on Account details link for "1234567"
    Then I should see the back link to previous page
    When I click on back link to previous page
    Then the page title should be "Your contact details - Manage import duties and VAT accounts - GOV.UK"


  Scenario Outline: Change links on name, telephone and email navigate to edit contact details page
    When I click on Account details link for "1234567"
    And I click on change link for <field>
    Then I should see the sub-heading "Account: 1234567"
    Then I should see the heading "Duty deferment personal details"
    And I should see the following details populated in the form
      | Full name      | Telephone number (optional) | Email address    |
      | Contact Name 1 | 01111111111                 | email1@gmail.com |
    Examples:
      | field         |
      | Name          |
      | Telephone     |


  #@test
  Scenario Outline: Change contact details and check back link on confirmation page
    When I click on Account details link for "1234567"
    And I click on change link for Name
    And I should see the following details populated in the form
      | Full name      | Telephone number (optional) | Email address    |
      | Contact Name 1 | 01111111111                 | email1@gmail.com |
    And I enter the following details
      | <field> |
      | <value> |
    When I click on Save changes button
    Then I should see the following confirmation text
      | Personal details for duty deferment successfully updated |
      | Account number                                           |
      | 1234567                                                  |
    And I should see a link to "Back to your duty deferment contact details"
    When I click on 'Back to your duty deferment contact details'
    Then I should see the sub-heading "Account: 1234567"
    Then I should see the heading "Duty deferment contact details"
    And I should see the following contact details
      | Name   | Address   | Telephone   | Email address |
      | <name> | <address> | <telephone> | <email>       |
    Examples:
      | field                      | value            | name           | address                                                     | telephone   | email            |
      | Full name                  | Contact Name 2   | Contact Name 2 | Street 1,District A,County A,City 1,AB12 3CD,United Kingdom | 01111111111 | email1@gmail.com |
      | Telephone number (optional)| 01111111112      | Contact Name 1 | Street 1,District A,County A,City 1,AB12 3CD,United Kingdom | 01111111112 | email1@gmail.com |
      #| Email address              | email2@gmail.com | Contact Name 1 | Street 1,District A,County A,City 1,AB12 3CD,United Kingdom | 01111111111 | email2@gmail.com |
