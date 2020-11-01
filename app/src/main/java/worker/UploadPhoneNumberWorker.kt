package worker


import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import module.NetworkModule
import repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userApiWebService = NetworkModule().getApiInterface(retrofit)
        val userRepository = UserRepository(userDao, patientDao, userApiWebService)

        if (patientDao.getPatientRecord(inputData.getString(Constants.PHONE_NUMBER)!!) == null) {
            val patient = Patient()
            inputData.getString(Constants.PHONE_NUMBER)
            patient.patientPhoneNumber = inputData.getString(Constants.PHONE_NUMBER).toString()
            userRepository.insertPatientRecord(patient)
        } else {
            Log.i("-----> ", "UploadPhoneNumberWorker - patient record all ready available")
        }
    }
}