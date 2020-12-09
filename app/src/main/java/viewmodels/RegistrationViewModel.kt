package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import repository.LoginRepository
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    application: Application,
    private var loginRepository: LoginRepository
) : AndroidViewModel(application) {

    private var loginOrRegistrationResponseModel: LiveData<LoginOrRegistrationResponseModel>? = null

    fun registerNewUser(loginOrRegistrationRequestModel: LoginOrRegistrationRequestModel) {
        loginOrRegistrationResponseModel =
            loginRepository.registerNewUser(loginOrRegistrationRequestModel)
    }

    fun fetchRegisterNewUserViewModelObservable(): LiveData<LoginOrRegistrationResponseModel>? {
        return loginOrRegistrationResponseModel
    }
}