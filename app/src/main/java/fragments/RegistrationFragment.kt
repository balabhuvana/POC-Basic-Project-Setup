package fragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_registration.*
import util.CommonUtils
import util.PermissionUtil


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure the run time permission handle setting
        PermissionUtil.handleMultipleRunTimePermission(this)

        this.handleEditTextOperation()

        tv_all_ready_registered.setOnClickListener {
            takeToLoginScreen()
        }

        next_button_registration.setOnClickListener {
            if ((requireActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
            ) {
                tapOnNext()
            } else {
                PermissionUtil.requestMultiplePermission()
            }
        }
    }

    private fun tapOnNext() {
        handleUserNameValidation()
        handleEmailValidation()
        handlePasswordValidation()
    }

    private fun handleUserNameValidation() {
        if (!CommonUtils.isUsernameOrPasswordValid(et_full_name.text, 6)) {
            et_full_name.error = getString(R.string.error_username)
        } else {
            et_full_name.error = null
        }
    }

    private fun handleEmailValidation() {
        if (CommonUtils
                .isValidEmailAddress(et_email.text)
        ) {
            et_email.error = null
        } else {
            et_email.error = getString(R.string.error_email)
        }
    }

    private fun handlePasswordValidation() {
        if (!CommonUtils.isUsernameOrPasswordValid(et_password_registration.text, 8)) {
            et_password_registration.error = getString(R.string.error_password)
        } else {
            et_password_registration.error = null
        }
    }

    private fun takeToLoginScreen() {
        val registrationDirection: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }

    private fun handleEditTextOperation() {

        et_full_name.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isUsernameOrPasswordValid(et_full_name.text, 6)) {
                et_full_name.error = null //Clear the error
            }
            false
        }

        et_email.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isUsernameOrPasswordValid(et_email.text, 6)) {
                //et_full_name.error = null //Clear the error
            }
            false
        }

        et_password_registration.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isUsernameOrPasswordValid(et_password_registration.text, 8)) {
                et_password_registration.error = null //Clear the error
            }
            false
        }
    }
}
