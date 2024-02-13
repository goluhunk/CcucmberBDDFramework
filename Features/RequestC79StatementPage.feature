Feature: Request Historic C79 Statements

#@Sanity
Scenario: Check Government Label Chrome
Given I am signed in as a registered user
When I navigate to Google
Then The Page title should be Google


@Regression
Scenario: Check Government Label Firefox
Given I am signed in as a RegisteredUser
When I navigate to Google
Then The Page title should be GoogleNot

