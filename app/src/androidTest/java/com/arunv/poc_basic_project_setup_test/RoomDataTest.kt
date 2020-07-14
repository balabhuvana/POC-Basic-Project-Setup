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
import room.User
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
        val user: User = User()
        user.userName = "1005"
        user.password = "abdcef"
        val id=userDao.insertUser(user)
        Log.i("-----> ", "" + id)

        val user2 = User()
        user2.userName = "1007"
        user2.password = "asdfg"
        val id1=userDao.insertUser(user)

        Log.i("-----> ", "" +id1)

        val selectUser = getValue(userDao.getUserList()).size
        Log.i("-----> ", "" + selectUser)
        Assert.assertNotNull(selectUser)

    }

}