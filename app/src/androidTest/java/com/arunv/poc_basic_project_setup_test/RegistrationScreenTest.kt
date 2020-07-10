package com.arunv.poc_basic_project_setup_test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arunv.poc_basic_project_setup.R
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationScreenTest {

    @Then("^I should see the register screen ui")
    fun I_should_see_the_register_screen_ui() {

        onView(ViewMatchers.withId(R.id.etUserName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.etPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.etConfirmPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.btnRegister))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Then("^I should validate the register screen ui input")
    fun I_should_validate_the_register_screen_ui_input() {

        onView(ViewMatchers.withId(R.id.etUserName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.etPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.etConfirmPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @When("^I enter the confirm password \"([^\"]*)\"$")
    fun I_enter_the_confirm_password(confirmPassword: String) {
        onView(ViewMatchers.withId(R.id.etConfirmPassword))
            .perform(ViewActions.typeText(confirmPassword), ViewActions.click())
    }

}