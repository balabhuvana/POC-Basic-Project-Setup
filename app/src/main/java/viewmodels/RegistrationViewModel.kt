package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import model.RegisterRequestModel
import model.RegisterResponseModel
import repository.RegistrationRepository
import room.RegisterRequestRoomModel
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    application: Application,
    private var registrationRepository: RegistrationRepository
) : AndroidViewModel(application) {

    private var registerResponseModel: MutableLiveData<RegisterResponseModel>? = null
    private var selectUserLiveData: LiveData<RegisterRequestRoomModel>? = null

    fun registerNewUserViewModel(registerRequestModel: RegisterRequestModel) {
        registerResponseModel =
            registrationRepository.registerNewUserRepo(registerRequestModel)
    }

    fun registerResponseViewModelObservable(): MutableLiveData<RegisterResponseModel>? {
        return registerResponseModel
    }

    fun insertUserRecord(registerRequestRoomModel: RegisterRequestRoomModel) {
        registrationRepository.insertUserRecord(registerRequestRoomModel)
    }

    fun selectUserRecord(registerRequestRoomModel: RegisterRequestRoomModel) {
        selectUserLiveData = registrationRepository.getUserRecord(registerRequestRoomModel)
    }

    fun observeUserRecord(): LiveData<RegisterRequestRoomModel>? {
        return selectUserLiveData
    }
}