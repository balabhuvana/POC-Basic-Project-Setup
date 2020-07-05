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
import kotlinx.android.synthetic.main.fragment_login.btnRegister
import kotlinx.android.synthetic.main.fragment_registration.*
import room.User
import viewmodels.RegistrationViewModel

/**
 * A simple [Fragment] subclass.
 */
class RegistrationFragment : Fragment() {

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
        btnRegister.setOnClickListener {

            val userName: String = etRegUserName.text.toString()
            val userPassword: String = etRegPassword.text.toString()
            val userConfirmPassword: String = etConfirmPassword.text.toString()

            if (userName.isNotEmpty() && userPassword.isNotEmpty() && userConfirmPassword.isNotEmpty()) {
                val user = User()
                user.isUserLogined = true
                user.userName = userName
                user.password = etRegPassword.text.toString()

                progressBarAction(View.VISIBLE)
                registrationViewModel.insertUserRecord(user)
                registrationViewModel.selectUserRecord(user)

                observeRegistrationViewModelLiveData()

            } else {
                Toast.makeText(context, "Data should not be empty", Toast.LENGTH_LONG).show()
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
}