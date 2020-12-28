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
import com.arunv.poc_basic_project_setup.BaseApplication
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_registration.*
import model.RegisterRequestModel
import room.RegisterRequestRoomModel
import util.CommonUtils
import util.CommonUtils.Companion.showToastMessage
import util.PermissionUtil
import viewmodels.RegistrationViewModel
import javax.inject.Inject


class RegistrationFragment : Fragment() {

    private var isValidInput: Boolean = false

    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as BaseApplication).appComponent
            ?.inject(this)

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


        // Configure the run time permission handle setting
        PermissionUtil.handleMultipleRunTimePermission(this)

        this.handleEditTextOperation()

        tv_all_ready_registered.setOnClickListener {
            takeToLoginScreen()
        }

        btn_next.setOnClickListener {
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
        handlePasswordValidation()
        handlePhoneNumberValidation()
        if (isValidInput) {
            handleRegisterViewModel()
        } else {
            Toast.makeText(context, "Please enter valid user data", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleUserNameValidation() {
        if (!CommonUtils.isValidUserName(et_username.text.toString(), 6)) {
            et_username.error = getString(R.string.error_username)
            isValidInput = false
        } else {
            et_username.error = null
            isValidInput = true
        }
    }

    private fun handlePasswordValidation() {
        if (!CommonUtils.isValidPasswordValid(et_password.text.toString(), 4)) {
            et_password.error = getString(R.string.error_password)
            isValidInput = false
        } else {
            et_password.error = null
            isValidInput = true
        }
    }

    private fun handlePhoneNumberValidation() {
        if (!CommonUtils.isValidPhoneNumber(et_phone_number.text.toString(), 10)) {
            et_phone_number.error = getString(R.string.error_phone_number)
            isValidInput = false
        } else {
            et_phone_number.error = null
            isValidInput = true
        }
    }

    private fun takeToLoginScreen() {
        val registrationDirection: NavDirections =
            RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()

        val navigationController: NavController = findNavController()
        navigationController.navigate(registrationDirection)
    }

    private fun handleEditTextOperation() {

        et_username.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isValidUserName(et_username.text.toString(), 6)) {
                et_username.error = null //Clear the error
            }
            false
        }

        et_password.setOnKeyListener { _, _, _ ->
            if (CommonUtils.isValidPasswordValid(et_password.text.toString(), 6)) {
                et_password.error = null //Clear the error
            }
            false
        }
    }

    private fun handleRegisterViewModel() {
        val registrationRequestModel = RegisterRequestModel()

        registrationRequestModel.userName = et_username.text.toString().trim()
        registrationRequestModel.password = et_password.text.toString().trim()
        registrationRequestModel.phoneNumber = et_phone_number.text.toString()
        registrationRequestModel.firstName = "apple"
        registrationRequestModel.lastName = "apple"
        registrationRequestModel.countryCode = "342"
        CommonUtils.showHideView(progressBar, true)
        registrationViewModel.registerNewUserViewModel(registrationRequestModel)

        observeRegisterResponse(registrationViewModel)
    }

    private fun observeRegisterResponse(registrationViewModel: RegistrationViewModel) {
        registrationViewModel.registerResponseViewModelObservable()
            ?.observe(viewLifecycleOwner, Observer {
                CommonUtils.showHideView(progressBar, false)
                if (it.success!!) {
                    insertUserRecord()
                } else {
                    showToastMessage(
                        this.context!!,
                        it.message
                    )
                }
            })
    }

    private fun insertUserRecord() {
        val registerRequestRoomModel = RegisterRequestRoomModel()
        registerRequestRoomModel.userName = et_username.text.toString().trim()
        registerRequestRoomModel.password = et_password.text.toString().trim()
        registerRequestRoomModel.phoneNumber = et_phone_number.text.toString()
        registerRequestRoomModel.firstName = "apple"
        registerRequestRoomModel.lastName = "apple"
        registerRequestRoomModel.countryCode = "342"

        registrationViewModel.insertUserRecord(registerRequestRoomModel)
        registrationViewModel.selectUserRecord(registerRequestRoomModel)
        observeRegistrationViewModelLiveData()
    }

    private fun observeRegistrationViewModelLiveData() {
        registrationViewModel.observeUserRecord()?.observe(viewLifecycleOwner, Observer { user ->
            Thread.sleep(2000)
            if (user != null) {
                showToastMessage(
                    this.context!!
                    , "User record inserted successfully"
                )
                takeToHomeScreen(user.userName!!)
                CommonUtils.showHideView(progressBar, false)
            } else {
                showToastMessage(this.context!!, "User record is not inserted successfully")
            }
        })
    }

    private fun takeToHomeScreen(userName: String) {
        val homeScreenDirection: NavDirections =
            RegistrationFragmentDirections.actionRegistrationFragmentToHomeScreen(userNameArgs = userName)
        val navigationController: NavController = findNavController()
        navigationController.navigate(homeScreenDirection)
    }
}
