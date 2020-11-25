package fragments


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arunv.poc_basic_project_setup.R
import dagger.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_registration.*
import dagger.AppModule
import dagger.RoomModule
import room.User
import util.PermissionUtil
import viewmodels.RegistrationViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class RegistrationFragment : Fragment() {

    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerAppComponent.builder()
            .appModule(AppModule(activity!!.application))
            .roomModule(RoomModule(activity!!.application))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener {

            PermissionUtil.handleMultipleRunTimePermission(this)

            if ((requireActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED)
                && (requireActivity().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
            ) {

                val userName: String = etUserName.text.toString()

                if (validateUserInput(etUserName, etPassword, etConfirmPassword)) {
                    if (validateMatchPasswordAndConfirmPassword(
                            etPassword.text.toString(),
                            etConfirmPassword.text.toString()
                        )
                    ) {
                        val user = User()
                        user.isUserLogined = true
                        user.userName = userName
                        user.password = etPassword.text.toString()

                        progressBarAction(View.VISIBLE)
                        registrationViewModel.insertUserRecord(user)
                        registrationViewModel.selectUserRecord(user)

                        observeRegistrationViewModelLiveData()
                    } else {
                        showToastMessage("Password should not mismatch")
                    }

                } else {
                    showToastMessage("Data should not be empty")
                }
            } else {
                PermissionUtil.requestMultiplePermission()
            }

        }
    }

    private fun observeRegistrationViewModelLiveData() {
        registrationViewModel.observeUserRecord()?.observe(viewLifecycleOwner, Observer { user ->
            Thread.sleep(5000)
            progressBarAction(View.GONE)
            if (user != null) {
                showToastMessage("User record inserted successfully")
                launchHomeFragment(user)
            } else {
                showToastMessage("User record is not inserted successfully")
            }
        })
    }

    private fun launchHomeFragment(user: User) {
        val homeNavDirections: NavDirections =
            RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment(userNameArgs = user.userName.toInt())
        val navController: NavController = findNavController()
        navController.navigate(homeNavDirections)
    }

    private fun progressBarAction(type: Int) {
        registrationProgressBar.visibility = type
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(activity, "" + message, Toast.LENGTH_LONG).show()
    }

    private fun validateUserInput(
        editTextUserName: EditText,
        editTextPassword: EditText,
        editTextConfirmPassword: EditText
    ): Boolean {
        return validateUserName(editTextUserName) && validatePassword(editTextPassword) && validateConfirmPassword(
            editTextConfirmPassword
        )
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

    private fun validateConfirmPassword(editTextConfirmPassword: EditText): Boolean {
        return editTextConfirmPassword.text.isNotEmpty() && validateConfirmPasswordLength(
            editTextConfirmPassword.text.toString()
        )
    }

    fun validateConfirmPasswordLength(password: String): Boolean {
        return password.length > 3
    }

    fun validateMatchPasswordAndConfirmPassword(
        password: String,
        confirmPassword: String
    ): Boolean {
        return password == confirmPassword
    }
}
