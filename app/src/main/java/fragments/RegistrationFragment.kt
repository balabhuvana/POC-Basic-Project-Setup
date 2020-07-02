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
                registrationViewModel.insertUserRecord(user)
            } else {
                Toast.makeText(context, "Data should not be empty", Toast.LENGTH_LONG).show()
            }

        }

        btnSample.setOnClickListener {
            registrationViewModel.selectUserList()

            registrationViewModel.userObserverLiveData()
                ?.observe(viewLifecycleOwner,
                    Observer<List<User>> { userList ->
                        if (userList != null) {
                            Log.i("-----> ", "User list size : " + userList.size)
                            for (user in userList) {
                                Log.i("-----> ", "User ID : " + user.userId)
                                Log.i("----->", "User Name : " + user.userName)
                            }
                        } else {
                            Toast.makeText(activity, "Invalid User", Toast.LENGTH_LONG).show()
                        }
                    })
        }
    }

}
