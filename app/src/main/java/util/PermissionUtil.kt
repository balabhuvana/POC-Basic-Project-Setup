package util

import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.arunv.poc_basic_project_setup.R
import com.google.android.material.snackbar.Snackbar

class PermissionUtil {


    companion object {

        private lateinit var activityMultipleRequestPermission: ActivityResultLauncher<Array<String>>

        fun handleMultipleRunTimePermission(
            fragment: Fragment
        ) {

            activityMultipleRequestPermission =
                fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                    if (it.isNotEmpty()) {
                        if (it.containsKey(Manifest.permission.READ_PHONE_STATE)) {
                            if (it[Manifest.permission.READ_PHONE_STATE]!!) {
                                Snackbar.make(
                                    fragment.requireView(),
                                    R.string.permission_granted,
                                    Snackbar.LENGTH_LONG
                                ).show()
                            } else {
                                Snackbar.make(
                                    fragment.requireView(),
                                    R.string.read_phone_state_ui,
                                    Snackbar.LENGTH_LONG
                                ).setAction(R.string.action) {
                                    activityMultipleRequestPermission.launch(arrayOf(Manifest.permission.READ_PHONE_STATE))
                                }.show()
                            }
                        } else if (it.containsKey(Manifest.permission.READ_CALL_LOG)) {
                            if (it[Manifest.permission.READ_CALL_LOG]!!) {
                                Snackbar.make(
                                    fragment.requireView(),
                                    R.string.permission_granted,
                                    Snackbar.LENGTH_LONG
                                ).show()
                            } else {
                                Snackbar.make(
                                    fragment.requireView(),
                                    R.string.permission_not_granted,
                                    Snackbar.LENGTH_LONG
                                ).setAction(R.string.action) {
                                    activityMultipleRequestPermission.launch(arrayOf(Manifest.permission.READ_CALL_LOG))
                                }.show()
                            }
                        } else if (it.containsKey(Manifest.permission.SEND_SMS)) {
                            if (it[Manifest.permission.SEND_SMS]!!) {
                                Snackbar.make(
                                    fragment.requireView(),
                                    R.string.permission_granted,
                                    Snackbar.LENGTH_LONG
                                ).show()
                            } else {
                                Snackbar.make(
                                    fragment.requireView(),
                                    R.string.permission_not_granted,
                                    Snackbar.LENGTH_LONG
                                ).setAction(R.string.action) {
                                    activityMultipleRequestPermission.launch(arrayOf(Manifest.permission.SEND_SMS))
                                }.show()
                            }
                        }
                    }
                }

        }

        fun requestMultiplePermission() {
            val handleMultiplePermission: Array<String> =
                arrayOf(
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.SEND_SMS
                )

            activityMultipleRequestPermission.launch(handleMultiplePermission)
        }
    }
}