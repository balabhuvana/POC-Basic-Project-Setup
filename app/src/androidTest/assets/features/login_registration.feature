Feature: Login and Registration screen validation

  Scenario: Validate login screen ui
    When Launch the single activity
    Then I wait for 5 second
    And I should see the login ui

    Scenario: Validate register screen via login
    When Launch the single activity
    And I wait for 5 second
    Then I should see the login ui
    When I press register button
    Then I should see the register screen ui
