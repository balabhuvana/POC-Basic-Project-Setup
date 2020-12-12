package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import model.RegisterRequestModel
import model.RegisterResponseModel
import repository.RegistrationRepository
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    application: Application,
    private var registrationRepository: RegistrationRepository
) : AndroidViewModel(application) {

    private var registerResponseModel: MutableLiveData<RegisterResponseModel>? = null

    fun registerNewUserViewModel(registerRequestModel: RegisterRequestModel) {
        registerResponseModel =
            registrationRepository.registerNewUserRepo(registerRequestModel)
    }

    fun registerResponseViewModelObservable(): MutableLiveData<RegisterResponseModel>? {
        return registerResponseModel
    }
}