@acceptance

Feature: Tracking consent banner on financials pages

  Background:
    Given I am not signed in
    And I am signed in as a super user

  Scenario: Cookie consent banner is displayed
    When I navigate to the Customs Financials Home with cookie banner page
    Then I should see a cookie consent banner with the following details
      | Cookies on HMRC services                                                                                                                |
      | We use some essential cookies to make our services work.                                                                                |
      | We would like to set additional cookies so we can remember your settings, understand how people use our services and make improvements. |
      | Accept additional cookies                                                                                                               |
      | Reject additional cookies                                                                                                               |
      | View cookie preferences                                                                                                                 |
    And I should see the following links on the cookie banner
      | View cookie preferences |
    And I should see the following buttons on the cookie banner
      | Accept additional cookies |
      | Reject additional cookies |

  Scenario: Accept cookies on cookie consent banner
    When I navigate to the Customs Financials Home with cookie banner page
    And I click on Accept additional cookies
    Then I should see a cookie consent banner with the following details
      | You have accepted additional cookies. You can change your cookie settings at any time. |
      | Hide cookies message                                                                   |
    And I should see the following links on the cookie banner
      | change your cookie settings |
    And I should see the following buttons on the cookie banner
      | Hide cookies message |
    When I click on Hide cookies message
    Then I should not see cookie consent banner

  Scenario: Reject cookies on cookie consent banner
    When I navigate to the Customs Financials Home with cookie banner page
    And I click on Reject additional cookies
    Then I should see a cookie consent banner with the following details
      | You have rejected additional cookies. You can change your cookie settings at any time. |
      | Hide cookies message                                                                   |
    And I should see the following links on the cookie banner
      | change your cookie settings |
    And I should see the following buttons on the cookie banner
      | Hide cookies message |
    When I click on Hide cookies message
    Then I should not see cookie consent banner

  Scenario: Cookie consent banner is not displayed once it is accepted
    When I navigate to the Customs Financials Home with cookie banner page
    And I click on Accept additional cookies
    And I click on the View import VAT certificates (C79) link on the Import VAT certificates card
    And I navigate to the Customs Financials Home with cookie banner page
    Then I should not see cookie consent banner

  Scenario: Cookie consent banner is not displayed once it is rejected
    When I navigate to the Customs Financials Home with cookie banner page
    And I click on Reject additional cookies
    And I click on the View import VAT certificates (C79) link on the Import VAT certificates card
    And I navigate to the Customs Financials Home with cookie banner page
    Then I should not see cookie consent banner

