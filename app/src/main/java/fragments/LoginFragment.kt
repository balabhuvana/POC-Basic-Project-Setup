package fragments


import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_login.*
import room.User
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

        btnLogin.setOnClickListener {
            if (etUserName.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
                showOrHideProgressBar(View.VISIBLE)
                loginViewModel.selectSpecificUser(
                    etUserName.text.toString().toInt(),
                    etPassword.text.toString().trim()
                )

                observerLoginViewModel()
            } else {
                showToastMessage("Data should not be empty")
            }
        }

        btnRegister.setOnClickListener {
            launchRegistrationScreen()
        }
    }

    private fun launchHomeFragment() {
        val homeNavDirections: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
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
                        launchHomeFragment()
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
}
