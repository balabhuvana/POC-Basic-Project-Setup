package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import dagger.LoginNetworkModule
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import network.LoginApiWebService
import repository.LoginRepository

class RegistrationViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var loginRepository: LoginRepository
    private var loginOrRegistrationResponseModel: LiveData<LoginOrRegistrationResponseModel>? = null

    init {
        val loginApiWebService: LoginApiWebService? = LoginNetworkModule().getAPIService();
        loginRepository = LoginRepository(loginApiWebService!!)
    }

    fun registerNewUser(loginOrRegistrationRequestModel: LoginOrRegistrationRequestModel) {
        loginOrRegistrationResponseModel =
            loginRepository.registerNewUser(loginOrRegistrationRequestModel)
    }

    fun fetchRegisterNewUserViewModelObservable(): LiveData<LoginOrRegistrationResponseModel>? {
        return loginOrRegistrationResponseModel
    }
}