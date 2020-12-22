package com.arunv.poc_basic_project_setup_test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arunv.poc_basic_project_setup.R
import cucumber.api.java.en.Then
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @Then("^I should see the home screen ui")
    fun i_should_see_the_home_screen_ui() {
        onView(ViewMatchers.withId(R.id.tvUsername))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Then("^I press logout")
    fun i_press_logout() {
        onView(ViewMatchers.withId(R.id.btnLogout)).perform(click())
    }

}