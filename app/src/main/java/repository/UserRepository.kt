package repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import room.Patient
import room.PatientDao
import room.User
import room.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private var userDao: UserDao,
    private var patientDao: PatientDao
) {


    private var userLiveData: LiveData<User>? = null


    fun insertUserRecord(user: User) {
        GlobalScope.launch {
            userDao.insertUser(user)
        }
    }

    fun insertPatientRecord(patient: Patient) {
        GlobalScope.launch {
            patientDao.insertPatient(patient)
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
}