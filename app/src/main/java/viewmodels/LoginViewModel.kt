package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import model.LoginRequestModel
import model.LoginResponseModelRoot
import repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    private var loginRepository: LoginRepository
) : AndroidViewModel(application) {

    private var loginResponseViewModel: MutableLiveData<LoginResponseModelRoot>? = null

    fun loginUserViewModel(loginRequestModel: LoginRequestModel) {
        loginResponseViewModel = loginRepository.loginUserRepo(loginRequestModel)
    }

    fun loginResponseViewModelObservable(): MutableLiveData<LoginResponseModelRoot>? {
        return loginResponseViewModel
    }

}