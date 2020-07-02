package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import repository.UserRepository
import room.User
import room.UserDao
import room.UserRoomDatabase

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository? = null
    private var userListLiveData: LiveData<List<User>>? = null
    private var userLiveData: LiveData<User>? = null

    init {
        val userRoomDatabase: UserRoomDatabase =
            application.let { UserRoomDatabase.getDatabase(it) }
        val userDao: UserDao = userRoomDatabase.userDao()
        userRepository = UserRepository(userDao)
    }

    fun insertUserRecord(user: User) {
        userRepository?.insertUserRecord(user)
    }

    fun selectUserList() {
        userListLiveData = userRepository?.selectUserList()
    }

    fun userObserverLiveData(): LiveData<List<User>>? {
        return userListLiveData;
    }
}