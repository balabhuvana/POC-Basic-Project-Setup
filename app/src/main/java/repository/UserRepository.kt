package repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import room.User
import room.UserDao

class UserRepository(private var userDao: UserDao) {

    private var userListData: LiveData<List<User>> =
        MutableLiveData<List<User>>()

    private var userLiveData: LiveData<User>? = null


    fun insertUserRecord(user: User) {
        GlobalScope.launch {
            userDao.insertUser(user)
        }
    }

    fun selectUserList(): LiveData<List<User>> {
        userListData = userDao.getUserList()
        return userListData
    }

    fun selectUserRecord(userName: Int, userPassword:String): LiveData<User> {
        userLiveData = userDao.getUserRecord(userName, userPassword)
        return userLiveData as LiveData<User>
    }

}