package com.arunv.poc_basic_project_setup_test

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.arunv.poc_basic_project_setup.R
import com.arunv.poc_basic_project_setup.SingleActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import room.RegisterRequestRoomModel
import room.UserDao
import room.UserRoomDatabase

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginScenarioTest {

    private lateinit var userDao: UserDao
    private lateinit var userRoomDatabase: UserRoomDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun launchActivity() {
        ActivityScenario.launch(SingleActivity::class.java)
        Thread.sleep(5000)
        onView(withId(R.id.btn_next))
            .check(matches(isDisplayed()))

        onView(withId(R.id.et_username))
            .perform(ViewActions.typeText("1003"), click())

        onView(withId(R.id.et_password))
            .perform(ViewActions.typeText("abcd"), click())

        val context = ApplicationProvider.getApplicationContext<Context>()
        userRoomDatabase = Room.inMemoryDatabaseBuilder(
            context, UserRoomDatabase::class.java
        ).build()
        userDao = userRoomDatabase.userDao()

        val registerRequestRoomModel = RegisterRequestRoomModel()
        registerRequestRoomModel.userName = "arunv@gmail.com"
        registerRequestRoomModel.password = "abcd123"
        val id = userDao.insertUserMariaData(registerRequestRoomModel)

        Log.i("-----> ", "" + id)

        Log.i(
            "-----> ",
            "" + LiveDataTestUtil.getValue(userDao.getUserRecordMaria(registerRequestRoomModel.userName!!)).userName
        )

        onView(withId(R.id.btn_next)).perform(click())

        Thread.sleep(5000)
    }

}