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
    Then I should see the "Please enter valid user data"
    When I enter the username "arunv@gmail.com"
    And I press login button
    Then I should see the "Please enter valid user data"
    And I enter the password "abcd123"
    Then I should able to see the entered input "arunv@gmail.com" and "abcd123"
    When I press login button
    Then I should see the "User Not Found"

  Scenario: Validate register screen via login
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I press register button
    Then I should see the register screen ui

  Scenario: Validate register screen input
    When I clear shared preference data
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I press register button
    Then I should see the register screen ui
    When I enter the username "test12@gmail.com"
    And I press login button
    Then I should see the "Please enter valid user data"
    When I enter the password "password12"
    And I press login button
    Then I should see the "Please enter valid user data"
    When I enter the phone number "9790888113"
    And I press login button
    And I wait for 5 second
    Then I should see the home screen ui
    When I press logout
    Then I should see the login ui

#  Scenario: Validate login user input of username and password
#    When Launch the single activity
#    And I wait for 5 second
#    Then I should see the login ui
#    When I enter the username "123456"
#    And I enter the password "abcdef"
#    When I press login button
#    And I wait for 5 second
#    Then I should see the home screen ui