package com.arunv.poc_basic_project_setup_test

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arunv.poc_basic_project_setup.R
import cucumber.api.java.en.And
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import fragments.LoginFragment
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @When("^Launch the login screen and validate the ui")
    fun Launch_the_login_screen_and_validate_the_ui() {
        launchFragmentInContainer<LoginFragment>()

        onView(withId(R.id.tvUserName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.etUserName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tvPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.etPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.btnLogin))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.btnRegister))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Then("^I enter the username \"([^\"]*)\"$")
    fun I_enter_the_username(userName: String) {
        onView(withId(R.id.etUserName)).perform(typeText(userName), click())
    }

    @And("^I enter the password \"([^\"]*)\"$")
    fun I_enter_the_password(password: String) {
        onView(withId(R.id.etPassword)).perform(typeText(password), click())
    }

    @And("^I press login button$")
    fun I_press_login_button() {
        onView(withId(R.id.btnLogin)).perform(click())
    }

    @And("^I should not see the keyboard$")
    fun I_should_not_see_the_keyboard() {
        closeSoftKeyboard()
    }
}
