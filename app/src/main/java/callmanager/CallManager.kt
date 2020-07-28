package callmanager

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.telecom.Call
import android.util.Log
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import room.Patient
import room.UserDao
import room.UserRoomDatabase
import util.CommonUtils

@TargetApi(Build.VERSION_CODES.M)
object CallManager {

    private const val LOG_TAG = "CallManager"

    private val subject = BehaviorSubject.create<GsmCall>()

    private var currentCall: Call? = null

    fun updates(): Observable<GsmCall> = subject

    fun updateCall(call: Call?) {
        currentCall = call
        call?.let {
            subject.onNext(it.toGsmCall())
        }
    }

    fun cancelCall(context: Context) {
        currentCall?.let {
            when (it.state) {
                Call.STATE_RINGING -> rejectCall(subject.value?.displayName, context)
                else -> disconnectCall()
            }
        }
    }

    fun acceptCall() {
        Log.i(LOG_TAG, "acceptCall")
        currentCall?.let {
            it.answer(it.details.videoState)
        }
    }

    private fun rejectCall(phoneNumber: String?, context: Context) {
        Log.i(LOG_TAG, "rejectCall")
        CommonUtils.uploadPhoneNUmber(context, phoneNumber!!)
        Thread.sleep(5000)
        currentCall?.reject(true, getPatientDetail(phoneNumber, context))
    }

    private fun getPatientDetail(phoneNumber: String, context: Context): String {
        val userRoomDatabase: UserRoomDatabase =
            context.let {
                UserRoomDatabase.getDatabase(it)
            }
        val userDao: UserDao = userRoomDatabase.userDao()

        val patient: Patient =
            userDao.getPatientRecord(phoneNumber)

        return "Token - " + patient.patientId + " - Hello World"

    }

    private fun disconnectCall() {
        Log.i(LOG_TAG, "disconnectCall")
        currentCall?.disconnect()
    }
}
