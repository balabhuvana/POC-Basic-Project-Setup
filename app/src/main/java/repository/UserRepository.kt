package repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import room.Patient
import room.User
import room.UserDao

class UserRepository(private var userDao: UserDao) {


    private var userLiveData: LiveData<User>? = null
    private var patientList: LiveData<List<Patient>>? = null


    fun insertUserRecord(user: User) {
        GlobalScope.launch {
            userDao.insertUser(user)
        }
    }

    fun insertPatientRecord(patient: Patient) {
        GlobalScope.launch {
            userDao.insertPatient(patient)
        }
    }

    fun selectUserRecord(user: User): LiveData<User> {
        return selectUserRecord(user.userName.toInt())
    }

    private fun selectUserRecord(userName: Int): LiveData<User> {
        userLiveData = userDao.getUserRecord(userName)
        return userLiveData as LiveData<User>
    }

    fun selectUserRecordWithPassword(userName: Int, userPassword: String): LiveData<User> {
        userLiveData = userDao.getUserRecordWithPassword(userName, userPassword)
        return userLiveData as LiveData<User>
    }

    fun getPatentList(): LiveData<List<Patient>> {
        patientList = userDao.getPatientList()
        return userDao.getPatientList()
    }
}