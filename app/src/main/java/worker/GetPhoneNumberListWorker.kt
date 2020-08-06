package worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import room.Patient
import room.PatientDao
import room.UserRoomDatabase
import util.CommonUtils
import util.Constants

class GetPhoneNumberListWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {

        val userRoomDatabase: UserRoomDatabase =
            applicationContext.let {
                UserRoomDatabase.getDatabase(it)
            }
        val patientDao: PatientDao = userRoomDatabase.patientDao()

        Log.i("-----> ", "Patient list : ${patientDao.getPatientListNoLiveData().size}")

        val phoneNumber: String? = inputData.getString(Constants.PHONE_NUMBER)

        val patient: Patient =
            patientDao.getPatientRecord(phoneNumber!!)
        if (!patient.isSmsSend) {
            CommonUtils.sendSms(applicationContext, patient, patientDao)
        } else {
            Log.i("-----> ", "Sms all ready send to this number $phoneNumber")
        }
        return Result.success()
    }
}
