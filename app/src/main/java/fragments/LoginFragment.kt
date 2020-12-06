package fragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arunv.poc_basic_project_setup.R
import dagger.LoginNetworkModule
import kotlinx.android.synthetic.main.fragment_login.*
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import network.LoginApiWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.CommonUtils
import util.PermissionUtil

class LoginFragment : Fragment() {

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
        } else {
            et_username_login.error = null
        }
    }

    private fun handlePasswordValidation() {
        if (!CommonUtils.isUsernameOrPasswordValid(et_password_login.text, 8)) {
            et_password_login.error = getString(R.string.error_password)
        } else {
            et_password_login.error = null
        }
    }

    private fun takeToRegistrationScreen() {
        val registrationDirection: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }

    private fun loginNetworkCall() {

        val registrationRequestModel = LoginOrRegistrationRequestModel()
        registrationRequestModel.email = "eve.holt@reqres.in"
        registrationRequestModel.password = "pistol"

        val loginApiWebService: LoginApiWebService? = LoginNetworkModule().getAPIService();

        val callUserModelForPostRequest =
            loginApiWebService?.loginUser(registrationRequestModel)

        callUserModelForPostRequest?.enqueue(object : Callback<LoginOrRegistrationResponseModel> {
            override fun onFailure(call: Call<LoginOrRegistrationResponseModel>, t: Throwable) {
                Log.i("---> ", " onFailure " + t.localizedMessage)
            }

            override fun onResponse(
                call: Call<LoginOrRegistrationResponseModel>,
                response: Response<LoginOrRegistrationResponseModel>
            ) {
                if (response.code() == 200) {
                    val registrationResponseModel: LoginOrRegistrationResponseModel? =
                        response.body()
                    Log.i("---> ", " onResponse " + registrationResponseModel?.token)
                } else if (response.code() == 400) {
                    val registrationResponseModel: LoginOrRegistrationResponseModel? =
                        response.body()
                    Log.i("---> ", " onResponse " + registrationResponseModel?.error)
                }
            }
        })

    }

}
