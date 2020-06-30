package repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import room.User
import room.UserDao

class UserRepository(private var userDao: UserDao) {

    private var userListData: LiveData<List<User>> =
        MutableLiveData<List<User>>()

    private var userLiveData: LiveData<User>? = null


    fun insertUserRecord(user: User) {
        userDao.insertUser(user)
    }

    fun selectUserList(): LiveData<List<User>> {
        userListData = userDao.getUserList()
        return userListData
    }

    fun selectUserRecord(rollNo: Int): LiveData<User> {
        userLiveData = userDao.getUserRecord(rollNo)
        return userLiveData as LiveData<User>
    }

}