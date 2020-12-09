package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import model.LoginRequestModelMaria
import repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    private var loginRepository: LoginRepository
) : AndroidViewModel(application) {

    private var loginResponseModel: LiveData<LoginOrRegistrationResponseModel>? = null

    fun loginNewUser(loginRequestModel: LoginOrRegistrationRequestModel) {
        loginResponseModel =
            loginRepository.loginUser(loginRequestModel)
    }

    fun loginViewModelObservable(): LiveData<LoginOrRegistrationResponseModel>? {
        return loginResponseModel
    }

    fun loginNewUserMariaServer(loginRequestModel: LoginRequestModelMaria) {
        loginRepository.loginUserMariaServer(loginRequestModel)
    }

}