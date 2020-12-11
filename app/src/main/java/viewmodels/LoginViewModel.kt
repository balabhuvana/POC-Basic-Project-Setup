package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import model.LoginRequestModelMaria
import model.LoginResponseModelRootMaria
import repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    private var loginRepository: LoginRepository
) : AndroidViewModel(application) {

    var loginResponseViewModel: MutableLiveData<LoginResponseModelRootMaria>? = null

    fun loginNewUserMariaServer(loginRequestModel: LoginRequestModelMaria) {
        loginResponseViewModel = loginRepository.loginPostUserMariaServer(loginRequestModel)
    }

    fun loginResponseViewModelMariaServerObservable(): MutableLiveData<LoginResponseModelRootMaria>? {
        return loginResponseViewModel
    }
}