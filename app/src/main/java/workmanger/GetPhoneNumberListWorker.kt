package workmanger

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import room.Patient
import room.UserDao
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
        val userDao: UserDao = userRoomDatabase.userDao()

        val patient: Patient =
            userDao.getPatientRecord(inputData.getString(Constants.PHONE_NUMBER)!!);
        CommonUtils.sendSms(applicationContext, patient,userDao)
        return Result.success()
    }


}
