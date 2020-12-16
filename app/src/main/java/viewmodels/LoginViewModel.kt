package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import model.LoginRequestModel
import model.LoginResponseModelRoot
import repository.LoginRepository
import room.RegisterRequestRoomModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    private var loginRepository: LoginRepository
) : AndroidViewModel(application) {

    private var loginResponseViewModel: MutableLiveData<LoginResponseModelRoot>? = null
    private var selectUserLiveData: LiveData<RegisterRequestRoomModel>? = null

    fun loginUserViewModel(loginRequestModel: LoginRequestModel) {
        loginResponseViewModel = loginRepository.loginUserRepo(loginRequestModel)
    }

    fun loginResponseViewModelObservable(): MutableLiveData<LoginResponseModelRoot>? {
        return loginResponseViewModel
    }

    fun selectUserRecordViaViewModel(userName: String, password: String) {
        selectUserLiveData =
            loginRepository.selectUserRecordDatabase(userName, password)
    }

    fun observeUserRecordViaViewModel(): LiveData<RegisterRequestRoomModel>? {
        return selectUserLiveData
    }
}