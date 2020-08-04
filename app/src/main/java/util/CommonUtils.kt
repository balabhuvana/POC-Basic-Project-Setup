package util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.telephony.SmsManager
import android.util.Log
import com.arunv.poc_basic_project_setup.R
import fragments.HomeFragment
import room.Patient
import room.UserDao

class CommonUtils {

    companion object {

        fun sendSms(context: Context, patient: Patient, userDao: UserDao) {
            try {
                Log.i("---->", "sendSms - ${patient.patientPhoneNumber}")

                val pi: PendingIntent =
                    PendingIntent.getActivity(
                        context,
                        0,
                        Intent(context, HomeFragment::class.java),
                        0
                    )
                val smsText: String = "Token - " + patient.patientId + " - Hello World"
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    patient.patientPhoneNumber,
                    null,
                    smsText,
                    pi,
                    null
                )

                patient.isSmsSend = true

                updateSMS(userDao, patient)

            } catch (exp: Exception) {
                exp.printStackTrace()
                Log.i("----->", "exp : ${exp.printStackTrace()}")
            }
        }

        private fun updateSMS(userDao: UserDao, patient: Patient) {
            userDao.updatePatientSmsStatus(patient)
        }

        fun saveUserLoginDetailInSharedPreferences(context: Context, userName: String) {
            val sharedPreferences: SharedPreferences? = context.getSharedPreferences(
                context.getString(R.string.user_login_data),
                Context.MODE_PRIVATE
            )
            val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.putBoolean(Constants.IS_USER_LOGINED, true)
            editor.putString(Constants.USER_NAME, userName)
            editor.apply()
        }

        fun isUserLogined(context: Context): Boolean {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(
                    context.getString(R.string.user_login_data),
                    Context.MODE_PRIVATE
                )
            return sharedPref.getBoolean(Constants.IS_USER_LOGINED, false)
        }

        fun getUserName(context: Context): String? {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(
                    context.getString(R.string.user_login_data),
                    Context.MODE_PRIVATE
                )
            return sharedPref.getString(Constants.USER_NAME, "")
        }

        fun clearUserData(context: Context) {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(
                    context.getString(R.string.user_login_data),
                    Context.MODE_PRIVATE
                )
            sharedPref.edit().clear().apply()
        }
    }

}