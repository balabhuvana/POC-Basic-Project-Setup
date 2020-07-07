Feature: Login
    Perform login on email and password are inputted

    @login-feature
    Scenario: Input email and password in wrong format
        When Launch the login screen and validate the ui
        Then I enter the username "123456"
        And I enter the password "abcde"
        Then I should not see the keyboard
        Then I press login button



