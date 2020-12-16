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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
        onView(withId(R.id.btnLogin))
            .check(matches(isDisplayed()))

        onView(withId(R.id.etUserName))
            .perform(ViewActions.typeText("1003"), click())

        onView(withId(R.id.etPassword))
            .perform(ViewActions.typeText("abcd"), click())

        val context = ApplicationProvider.getApplicationContext<Context>()
        userRoomDatabase = Room.inMemoryDatabaseBuilder(
            context, UserRoomDatabase::class.java
        ).build()
        userDao = userRoomDatabase.userDao()

        val user = User()
        user.userName = "1003"
        user.password = "abcd"
        val id = userDao.insertUser(user)

        Log.i("-----> ", "" + id)

        Log.i(
            "-----> ",
            "" + LiveDataTestUtil.getValue(userDao.getUserRecord(user.userName.toInt())).userName
        )
        Log.i("-----> ", "" + LiveDataTestUtil.getValue(userDao.getUserList()).size)

        onView(withId(R.id.btnLogin)).perform(click())

        Thread.sleep(5000)
    }

}