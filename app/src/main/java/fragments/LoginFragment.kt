package fragments


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telecom.TelecomManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_login.*
import room.User
import util.PermissionUtil
import util.PermissionUtil.Companion.requestMultiplePermission
import viewmodels.LoginViewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        PermissionUtil.handleMultipleRunTimePermission(this)

        checkDefaultDialer()

        btnLogin.setOnClickListener {
            if ((requireActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
            ) {
                if (validateUserInput(etUserName, etPassword)) {
                    showOrHideProgressBar(View.VISIBLE)
                    loginViewModel.selectSpecificUser(
                        etUserName.text.toString().toInt(),
                        etPassword.text.toString().trim()
                    )

                    observerLoginViewModel()
                } else {
                    showToastMessage("Data should not be empty")
                }
            } else {
                requestMultiplePermission()
            }
        }

        btnRegister.setOnClickListener {
            launchRegistrationScreen()
        }
    }

    private fun launchHomeFragment(user: User) {
        val homeNavDirections: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(userNameArgs = user.userName.toInt())
        val navController: NavController = findNavController()
        navController.navigate(homeNavDirections)
    }


    private fun launchRegistrationScreen() {
        val registrationDirection: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }

    private fun observerLoginViewModel() {
        loginViewModel.observeSpecificUser()
            ?.observe(viewLifecycleOwner,
                Observer<User> { user ->
                    Thread.sleep(2000)
                    showOrHideProgressBar(View.GONE)
                    if (user != null) {
                        showToastMessage("User is login successfully")
                        launchHomeFragment(user)
                    } else {
                        showToastMessage("Invalid User")
                    }
                })
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(activity, "" + message, Toast.LENGTH_LONG).show()
    }

    private fun showOrHideProgressBar(type: Int) {
        loginProgressBar.visibility = type
    }

    private fun validateUserInput(editTextUserName: EditText, editTextPassword: EditText): Boolean {
        return validateUserName(editTextUserName) && validatePassword(editTextPassword)
    }

    private fun validateUserName(editTextUserName: EditText): Boolean {
        return editTextUserName.text.isNotEmpty() && validateUserNameLength(editTextUserName.text.toString())
    }

    fun validateUserNameLength(userName: String): Boolean {
        return userName.length > 3
    }

    private fun validatePassword(editTextPassword: EditText): Boolean {
        return editTextPassword.text.isNotEmpty() && validatePasswordLength(editTextPassword.text.toString())
    }

    fun validatePasswordLength(password: String): Boolean {
        return password.length > 3
    }

    private fun checkDefaultDialer() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

        val telecomManager =
            activity!!.getSystemService(AppCompatActivity.TELECOM_SERVICE) as TelecomManager
        val isAlreadyDefaultDialer = activity!!.packageName == telecomManager.defaultDialerPackage
        if (isAlreadyDefaultDialer) return

        val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
            .putExtra(
                TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
                activity!!.packageName
            )
        startActivityForResult(intent, REQUEST_CODE_SET_DEFAULT_DIALER)
    }

    override fun onStart() {
        super.onStart()
        checkDefaultDialer()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_SET_DEFAULT_DIALER -> checkSetDefaultDialerResult(resultCode)
        }
    }

    private fun checkSetDefaultDialerResult(resultCode: Int) {
        val message = when (resultCode) {
            AppCompatActivity.RESULT_OK -> "User accepted request to become default dialer"
            AppCompatActivity.RESULT_CANCELED -> "User declined request to become default dialer"
            else -> "Unexpected result code $resultCode"
        }

        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUEST_CODE_SET_DEFAULT_DIALER = 123
    }
}
