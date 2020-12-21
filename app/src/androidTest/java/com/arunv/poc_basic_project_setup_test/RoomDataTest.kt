package com.arunv.poc_basic_project_setup_test

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.arunv.poc_basic_project_setup_test.LiveDataTestUtil.getValue
import org.junit.*
import org.junit.runner.RunWith
import room.RegisterRequestRoomModel
import room.UserDao
import room.UserRoomDatabase
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class RoomDataTest {

    private lateinit var userDao: UserDao
    private lateinit var userRoomDatabase: UserRoomDatabase
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userRoomDatabase = Room.inMemoryDatabaseBuilder(
            context, UserRoomDatabase::class.java
        ).build()
        userDao = userRoomDatabase.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        userRoomDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadDataFromRoom() {

        val registerRequestRoomModelOne = RegisterRequestRoomModel()
        registerRequestRoomModelOne.userName = "arunv@gmail.com"
        registerRequestRoomModelOne.password = "abcd123"
        val idOne = userDao.insertUserMariaData(registerRequestRoomModelOne)
        Log.i("-----> ", "" + idOne)

        val registerRequestRoomModelTwo = RegisterRequestRoomModel()
        registerRequestRoomModelTwo.userName = "arunv@gmail.com"
        registerRequestRoomModelTwo.password = "abcd123"
        val idTwo = userDao.insertUserMariaData(registerRequestRoomModelTwo)

        Log.i("-----> ", "" + idTwo)

        val selectUser = getValue(userDao.getUserListLogin()).size
        Log.i("-----> ", "" + selectUser)
        Assert.assertNotNull(selectUser)

    }

}