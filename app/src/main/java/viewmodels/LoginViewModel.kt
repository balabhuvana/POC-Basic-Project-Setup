package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import repository.UserRepository
import room.User
import room.UserDao
import room.UserRoomDatabase

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository? = null
    private var userLiveData: LiveData<User>? = null

    init {
        val userRoomDatabase: UserRoomDatabase =
            application.let { UserRoomDatabase.getDatabase(it) }
        val userDao: UserDao = userRoomDatabase.userDao()
        userRepository = UserRepository(userDao)
    }

    fun selectSpecificUser(userName: Int, userPassword: String) {
        userLiveData = userRepository?.selectUserRecordWithPassword(userName, userPassword)
    }

    fun observeSpecificUser(): LiveData<User>? {
        return userLiveData
    }

}