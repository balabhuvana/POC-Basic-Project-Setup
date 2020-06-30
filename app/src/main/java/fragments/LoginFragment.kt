package fragments


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
            loginViewModel.selectSpecificUser(etUserName.text.toString().toInt())
            observeSpecificUser(loginViewModel)
        }

        btnRegister.setOnClickListener {
            launchRegistrationScreen()
        }
    }

    private fun observeSpecificUser(loginViewModel: LoginViewModel) {
        loginViewModel.observeSpecificUser()
            ?.observe(viewLifecycleOwner,
                Observer<User> { user ->
                    if (user != null) {
                        Log.i("----> ", "User is not null")
                    } else {
                        Toast.makeText(activity, "Invalid User", Toast.LENGTH_LONG).show()
                    }
                })
    }

    private fun launchRegistrationScreen() {
        val registrationDirection: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }
}
