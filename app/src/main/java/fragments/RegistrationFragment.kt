package fragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_registration.*
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import util.CommonUtils
import util.PermissionUtil
import viewmodels.RegistrationViewModel


class RegistrationFragment : Fragment() {

    private var isValidInput: Boolean = false
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

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
        if (isValidInput) {
            handleRegisterViewModel()
        } else {
            Toast.makeText(context, "Please enter valid user data", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleUserNameValidation() {
        if (!CommonUtils.isUsernameOrPasswordValid(et_full_name.text, 6)) {
            et_full_name.error = getString(R.string.error_username)
            isValidInput = false
        } else {
            et_full_name.error = null
            isValidInput = true
        }
    }

    private fun handleEmailValidation() {
        if (CommonUtils
                .isValidEmailAddress(et_email.text)
        ) {
            et_email.error = null
            isValidInput = true
        } else {
            et_email.error = getString(R.string.error_email)
            isValidInput = false
        }
    }

    private fun handlePasswordValidation() {
        if (!CommonUtils.isUsernameOrPasswordValid(et_password_registration.text, 4)) {
            et_password_registration.error = getString(R.string.error_password)
            isValidInput = false
        } else {
            et_password_registration.error = null
            isValidInput = true
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
                et_email.error = null //Clear the error
            }
            false
        }

        et_password_registration.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isUsernameOrPasswordValid(et_password_registration.text, 6)) {
                et_password_registration.error = null //Clear the error
            }
            false
        }
    }

    private fun handleRegisterViewModel() {
        val registrationRequestModel = LoginOrRegistrationRequestModel()
        registrationRequestModel.email = et_email.text.toString().trim()
        registrationRequestModel.password = et_password_registration.text.toString().trim()
        registrationViewModel.registerNewUser(registrationRequestModel)
        observeUserModelPostResponseFromNetwork(registrationViewModel)
    }

    private fun observeUserModelPostResponseFromNetwork(registrationViewModel: RegistrationViewModel) {
        registrationViewModel.fetchRegisterNewUserViewModelObservable()?.observe(viewLifecycleOwner,
            Observer<LoginOrRegistrationResponseModel> {
                Log.i("----> ", "RF : ${it.id}")
            })
    }
}
