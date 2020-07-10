package com.arunv.poc_basic_project_setup_test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arunv.poc_basic_project_setup.R
import cucumber.api.java.en.Then
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @Then("^I should see the home screen ui")
    fun I_should_see_the_home_screen_ui() {

        onView(ViewMatchers.withId(R.id.tvUserName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.btnStartNextScreen))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}