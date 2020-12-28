package com.arunv.poc_basic_project_setup_test

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arunv.poc_basic_project_setup.R
import com.arunv.poc_basic_project_setup.SingleActivity
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    private val mockWebServer = MockWebServer()

    @When("^Launch the single activity")
    fun launch_the_single_activity() {
        ActivityScenario.launch(SingleActivity::class.java)
    }

    @Then("^I should see the login ui")
    fun i_should_see_the_login_ui() {

        onView(withId(R.id.et_username))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.et_password))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.btn_next))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tv_new_to_app))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @When("^I enter the username \"([^\"]*)\"$")
    fun i_enter_the_username(userName: String) {
        onView(withId(R.id.et_username)).perform(typeText(userName), click())
    }

    @When("^I enter the password \"([^\"]*)\"$")
    fun i_enter_the_password(password: String) {
        onView(withId(R.id.et_password)).perform(typeText(password), click())
    }

    @When("^I enter the phone number \"([^\"]*)\"$")
    fun i_enter_the_phone_number(password: String) {
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.et_phone_number)).perform(typeText(password), click())
    }

    @Then("^I should able to see the entered input \"([^\"]*)\" and \"([^\"]*)\"$")
    fun i_should_able_to_see_the_entered_input(userName: String, password: String) {
        onView(withText(userName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withText(password))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @When("^I press login button$")
    fun i_press_login_button() {
        CommonUITestUtils.showOrHidKeyBoard(false)
        onView(withId(R.id.btn_next)).perform(click())
    }

    @When("^I press register button$")
    fun i_press_register_button_from_login_screen() {
        onView(withId(R.id.tv_new_to_app)).perform(click())
    }

    @When("^I validate mock server with register screen$")
    fun i_validate_mock_server_with_register_screen() {
        mockWebServer.start(8080)
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        "{" +
                                "\"data\": \"testworld04@reqres.in\"," + "  \"success\": true," + "  \"message\": \"Asshole User Registered\"\n" + "}"
                    )
            }
        }

        onView(withId(R.id.et_username)).perform(ViewActions.clearText())
        onView(withId(R.id.et_username)).perform(
            typeText("autotest72@gmail.com"),
            click()
        )
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_next)).perform(click())
        Thread.sleep(5000)
        onView(withId(R.id.tvUsername)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        mockWebServer.shutdown()
    }

    @When("^I validate mock server with login screen$")
    fun i_validate_mock_server_with_login_screen() {
        mockWebServer.start(8080)
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        "{" +
                                "\"data\": \"eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI4MiIsInVuaXF1ZV9uYW1lIjoicGhvbmUxMEB2YWlkLmNvbSIsIm5iZiI6MTYwOTEzODM2MCwiZXhwIjoxNjA5MjI0NzYwLCJpYXQiOjE2MDkxMzgzNjB9.YaT4KFsjGO88gJJrBkT9iBprjJ5GEIVCSgyJUs9rjFmt2upeu8aXjRPt5660cpd2\"," + "  \"success\": true," + "  \"message\": \"Login Success\"\n" + "}"
                    )
            }
        }

        onView(withId(R.id.et_username)).perform(ViewActions.clearText())
        onView(withId(R.id.et_username)).perform(
            typeText("autotest72@gmail.com"),
            click()
        )
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_next)).perform(click())
        Thread.sleep(5000)
        onView(withId(R.id.tvUsername)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        mockWebServer.shutdown()
    }

    @When("^I validate mock server with register with login screen$")
    fun i_validate_mock_server_with_register_with_login_screen() {
        mockWebServer.start(8080)

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        "{" +
                                "\"data\": \"testworld04@reqres.in\"," + "  \"success\": true," + "  \"message\": \"Asshole User Registered\"\n" + "}"
                    )
            }
        }

        onView(withId(R.id.et_username)).perform(ViewActions.clearText())
        onView(withId(R.id.et_username)).perform(
            typeText("autotest72@gmail.com"),
            click()
        )
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btn_next)).perform(click())
        Thread.sleep(5000)
        onView(withId(R.id.tvUsername)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btnLogout)).perform(click())

        onView(withId(R.id.et_username))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.et_password))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.et_username)).perform(ViewActions.clearText())
        onView(withId(R.id.et_username)).perform(
            typeText("autotest72@gmail.com"),
            click()
        )

        onView(withId(R.id.et_password)).perform(typeText("pass123"), click())

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        "{" +
                                "\"data\": \"eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI4MiIsInVuaXF1ZV9uYW1lIjoicGhvbmUxMEB2YWlkLmNvbSIsIm5iZiI6MTYwOTEzODM2MCwiZXhwIjoxNjA5MjI0NzYwLCJpYXQiOjE2MDkxMzgzNjB9.YaT4KFsjGO88gJJrBkT9iBprjJ5GEIVCSgyJUs9rjFmt2upeu8aXjRPt5660cpd2\"," + "  \"success\": true," + "  \"message\": \"Login Success\"\n" + "}"
                    )
            }
        }

        CommonUITestUtils.showOrHidKeyBoard(false)
        onView(withId(R.id.btn_next)).perform(click())

        mockWebServer.shutdown()
    }

}
