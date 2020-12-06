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
import kotlinx.android.synthetic.main.fragment_login.*
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

        passwordEditTextOperation()

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

    private fun passwordEditTextOperation() {
        et_password_registration.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isUsernameOrPasswordValid(et_password_registration.text, 8)) {
                et_password_login.error = null //Clear the error
            }
            false
        }
    }
}
