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
import kotlinx.android.synthetic.main.fragment_login.*
import model.LoginRequestModelMaria
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
            )
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
//                tapOnNext()
//                loginGetRequestMariaServer()
                handleLoginViewModel()
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

        et_username_login.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isUsernameOrPasswordValid(et_username_login.text, 6)) {
                et_username_login.error = null //Clear the error
            }
            false
        }

        et_password_login.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isUsernameOrPasswordValid(et_password_login.text, 8)) {
                et_password_login.error = null //Clear the error
            }
            false
        }

    }


    private fun handleUserNameValidation() {
        if (!CommonUtils.isUsernameOrPasswordValid(et_username_login.text, 6)) {
            et_username_login.error = getString(R.string.error_password)
            isValidInput = false
        } else {
            isValidInput = true
            et_username_login.error = null
        }
    }

    private fun handlePasswordValidation() {
        if (!CommonUtils.isUsernameOrPasswordValid(et_password_login.text, 6)) {
            et_password_login.error = getString(R.string.error_password)
            isValidInput = false
        } else {
            isValidInput = true
            et_password_login.error = null
        }
    }

    private fun takeToRegistrationScreen() {
        val registrationDirection: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }

    private fun takeToHomeScreen() {
        val registrationDirection: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToHomeScreen()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }

    private fun handleLoginViewModel() {
        val loginRequestModelMaria = LoginRequestModelMaria()
        loginRequestModelMaria.userName = "bala"
        loginRequestModelMaria.userPassword = "lkjh"
        loginViewModel.loginNewUserMariaServer(loginRequestModelMaria)
        observeLoginResponseViewModelMariaServerObservable(loginViewModel)
    }

    private fun observeLoginResponseViewModelMariaServerObservable(loginViewModel: LoginViewModel) {
        loginViewModel.loginResponseViewModelMariaServerObservable()?.observe(viewLifecycleOwner,
            Observer {
                if (it.success!!) {
                    CommonUtils.showToastMessage(
                        this.context!!,
                        it.message
                    )
                    takeToHomeScreen()
                } else {
                    CommonUtils.showToastMessage(
                        this.context!!,
                        getString(R.string.error_please_try_again)
                    )
                }
            })
    }

}
