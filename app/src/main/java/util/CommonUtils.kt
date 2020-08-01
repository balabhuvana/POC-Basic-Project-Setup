package util

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repository.UserRepository
import room.Patient
import room.UserDao
import room.UserRoomDatabase

class CommonUtils {

    companion object {

        fun uploadPhoneNUmber(context: Context, phoneNumber: String) {
            val userRoomDatabase: UserRoomDatabase =
                context.let {
                    UserRoomDatabase.getDatabase(it)
                }
            val userDao: UserDao = userRoomDatabase.userDao()
            val userRepository = UserRepository(userDao)

            val patient = Patient()
            patient.patientPhoneNumber = phoneNumber
            GlobalScope.launch {
                userRepository.insertPatientRecord(patient)
            }
        }

    }

}