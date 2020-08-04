package broadcast_receiver

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import util.CommonUtils
import util.Constants
import worker.GetPhoneNumberListWorker
import worker.UploadPhoneNumberWorker


class IncomingCallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.extras != null) {
            val phoneNumber: String? = intent.extras!!.getString(Constants.INCOMING_NUMBER)
            if (phoneNumber != null) {
                if (CommonUtils.isUserLogined(context!!)) {
                    GlobalScope.launch(Dispatchers.IO) {
                        uploadPhoneNumber(phoneNumber = phoneNumber)
                        getPatientList(phoneNumber)
                    }
                } else {
                    Toast.makeText(context, "User is not logined", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun uploadPhoneNumber(phoneNumber: String) {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data: Data = workDataOf(Constants.PHONE_NUMBER to phoneNumber)

        val uploadPhoneNumberWorker: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<UploadPhoneNumberWorker>()
                .setConstraints(constraints)
                .setInputData(data)
                .build()

        WorkManager.getInstance().enqueue(uploadPhoneNumberWorker)
    }

    private fun getPatientList(phoneNumber: String) {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data: Data = workDataOf(Constants.PHONE_NUMBER to phoneNumber)

        val getPhoneNumberListWorker: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<GetPhoneNumberListWorker>()
                .setConstraints(constraints)
                .setInputData(data)
                .build()

        WorkManager.getInstance().enqueue(getPhoneNumberListWorker)
    }
}