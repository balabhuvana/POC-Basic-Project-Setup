@login-feature
Feature: Login and Registration screen validation via mock server

  Scenario: Validate login screen via mock server
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I enter the username "arunv@gmail.com"
    And I enter the password "abcd123"
    And I validate mock server with login screen
    And I wait for 5 second
    Then I should see the home screen ui
    When I press logout
    Then I should see the login ui

  Scenario: Validate register screen via mock server
    When I clear shared preference data
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I press register button
    Then I should see the register screen ui
    When I enter the username "test16@gmail.com"
    When I enter the password "password12"
    When I enter the phone number "9790888113"
    And I validate mock server with register screen
    And I wait for 5 second
    Then I should see the home screen ui
    When I press logout
    Then I should see the login ui


  Scenario: Validate register and login flow  via mock server
    When I clear shared preference data
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I press register button
    Then I should see the register screen ui
    When I enter the username "test16@gmail.com"
    When I enter the password "password12"
    When I enter the phone number "9790888113"
    And I validate mock server with register with login screen
    Then I should see the home screen ui
    When I press logout
    Then I should see the login ui