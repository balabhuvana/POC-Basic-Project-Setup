package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import repository.UserRepository
import room.User
import room.UserDao
import room.UserRoomDatabase

class RegistrationViewModel(application: Application) :
    AndroidViewModel(application) {

    private var userRepository: UserRepository? = null
    private var selectUserLiveData: LiveData<User>? = null
    private var progressLiveData: MutableLiveData<Boolean>? = null

    init {
        val userRoomDatabase: UserRoomDatabase =
            application.let { UserRoomDatabase.getDatabase(it) }
        val userDao: UserDao = userRoomDatabase.userDao()
        userRepository = UserRepository(userDao)
        progressLiveData = MutableLiveData()
    }

    fun insertUserRecord(user: User) {
        userRepository?.insertUserRecord(user)
    }

    fun selectUserRecord(user: User) {
        selectUserLiveData = userRepository?.selectUserRecord(user)
    }

    fun observeUserRecord(): LiveData<User>? {
        return selectUserLiveData
    }
}