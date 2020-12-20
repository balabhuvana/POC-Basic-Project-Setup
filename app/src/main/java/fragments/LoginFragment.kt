package fragments


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arunv.poc_basic_project_setup.R
import dagger.AppModule
import dagger.DaggerAppComponent
import dagger.NetworkModule
import dagger.RoomModule
import kotlinx.android.synthetic.main.fragment_login.*
import model.LoginRequestModel
import util.CommonUtils
import util.PermissionUtil
import viewmodels.LoginViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var isValidInput: Boolean = false

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerAppComponent.builder()
            .appModule(AppModule(activity!!.application))
            .networkModule(
                NetworkModule()
            ).roomModule(RoomModule(activity!!.application))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure the run time permission handle setting
        PermissionUtil.handleMultipleRunTimePermission(this)

        handleEditTextOperation()

        next_button.setOnClickListener {
            if ((requireActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
            ) {
                tapOnNext()
            } else {
                PermissionUtil.requestMultiplePermission()
            }
        }

        tv_new_to_app.setOnClickListener {
            takeToRegistrationScreen()
        }
    }

    private fun tapOnNext() {
        handleUserNameValidation()
        handlePasswordValidation()
        if (isValidInput) {
            handleLoginViewModel()
        } else {
            Toast.makeText(context, "Please enter valid user data", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleEditTextOperation() {

        et_username.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isValidUserName(et_username.text.toString(), 6)) {
                et_username.error = null //Clear the error
            }
            false
        }

        et_password.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isValidPasswordValid(et_password.text.toString(), 8)) {
                et_password.error = null //Clear the error
            }
            false
        }

    }


    private fun handleUserNameValidation() {
        if (!CommonUtils.isValidUserName(et_username.text.toString(), 6)) {
            et_username.error = getString(R.string.error_password)
            isValidInput = false
        } else {
            isValidInput = true
            et_username.error = null
        }
    }

    private fun handlePasswordValidation() {
        if (!CommonUtils.isValidPasswordValid(et_password.text.toString(), 6)) {
            et_password.error = getString(R.string.error_password)
            isValidInput = false
        } else {
            isValidInput = true
            et_password.error = null
        }
    }

    private fun takeToRegistrationScreen() {
        val registrationDirection: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }

    private fun takeToHomeScreen(username: String) {
        val loginFragmentDirections: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToHomeScreen(userNameArgs = username)

        val navigationController: NavController = findNavController()
        navigationController.navigate(loginFragmentDirections)
    }

    private fun handleLoginViewModel() {
        val loginRequestModelMaria = LoginRequestModel()
        loginRequestModelMaria.userName = et_username.text.toString().trim()
        loginRequestModelMaria.userPassword = et_password.text.toString().trim()
        CommonUtils.showHideView(progressBar, true)
        loginViewModel.loginUserViewModel(loginRequestModelMaria)
        observeLoginResponse(loginViewModel, loginRequestModelMaria.userName!!)
    }

    private fun observeLoginResponse(loginViewModel: LoginViewModel, username: String) {
        loginViewModel.loginResponseViewModelObservable()?.observe(viewLifecycleOwner,
            Observer {
                CommonUtils.showHideView(progressBar, false)
                if (it.success!!) {
                    CommonUtils.showToastMessage(
                        this.context!!,
                        it.message
                    )
                    takeToHomeScreen(username)
                } else {
                    CommonUtils.showToastMessage(
                        this.context!!,
                        getString(R.string.error_please_try_again)
                    )
                }
            })
    }

}
