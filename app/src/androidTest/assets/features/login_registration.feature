@login-feature
Feature: Login and Registration screen validation

  Scenario: Validate login screen ui
    When Launch the single activity
    Then I wait for 5 second
    And I should see the login ui

  Scenario: Validate login user input of username and password
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I enter the username ""
    And I press login button
    Then I should see the "Data should not be empty"
    When I enter the username "100009"
    And I press login button
    Then I should see the "Data should not be empty"
    And I enter the password "aaasss"
    Then I should able to see the entered input "100009" and "aaasss"
    When I press login button
    Then I should see the "Invalid User"

  Scenario: Validate register screen via login
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I press register button
    Then I should see the register screen ui

  Scenario: Validate register screen input
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I press register button
    Then I should see the register screen ui
    When I enter the username "123456"
    And I press register button
    Then I should see the "Data should not be empty"
    When I enter the password "abcdef"
    And I press register button
    Then I should see the "Data should not be empty"
    When I enter the confirm password "abcdef"
    And I press register button
    And I wait for 5 second
    Then I should see the home screen ui

  Scenario: Validate login user input of username and password
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I enter the username "123456"
    And I enter the password "abcdef"
    When I press login button
    And I wait for 5 second
    Then I should see the home screen ui