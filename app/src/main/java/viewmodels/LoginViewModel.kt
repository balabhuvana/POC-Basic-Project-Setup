package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import dagger.LoginNetworkModule
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import model.LoginRequestModelMaria
import network.LoginApiWebService
import repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private var loginRepository: LoginRepository
    private var loginResponseModel: LiveData<LoginOrRegistrationResponseModel>? = null

    init {
        val loginApiWebService: LoginApiWebService? = LoginNetworkModule().getAPIService();
        loginRepository = LoginRepository(loginApiWebService!!)
    }

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