package worker

import android.content.Context
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

        val patient: Patient =
            patientDao.getPatientRecord(inputData.getString(Constants.PHONE_NUMBER)!!);
        CommonUtils.sendSms(applicationContext, patient, patientDao)
        return Result.success()
    }


}
