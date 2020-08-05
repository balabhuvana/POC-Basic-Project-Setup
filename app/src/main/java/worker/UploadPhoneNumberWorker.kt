package worker


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import repository.UserRepository
import room.Patient
import room.PatientDao
import room.UserDao
import room.UserRoomDatabase
import util.Constants


class UploadPhoneNumberWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        uploadPhoneNUmber(applicationContext)
        return Result.success()
    }

    private fun uploadPhoneNUmber(context: Context) {
        val userRoomDatabase: UserRoomDatabase =
            context.let {
                UserRoomDatabase.getDatabase(it)
            }
        val userDao: UserDao = userRoomDatabase.userDao()
        val patientDao: PatientDao = userRoomDatabase.patientDao()
        val userRepository = UserRepository(userDao, patientDao)

        val patient = Patient()
        inputData.getString(Constants.PHONE_NUMBER)
        patient.patientPhoneNumber = inputData.getString(Constants.PHONE_NUMBER).toString()
        userRepository.insertPatientRecord(patient)
    }
}