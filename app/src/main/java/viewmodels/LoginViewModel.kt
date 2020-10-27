package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import repository.UserRepository
import room.User
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    var userRepository: UserRepository
) : AndroidViewModel(application) {

    private var userLiveData: LiveData<User>? = null

    fun selectSpecificUser(userName: Int, userPassword: String) {
        userLiveData = userRepository.selectUserRecordWithPassword(userName, userPassword)
    }

    fun observeSpecificUser(): LiveData<User>? {
        return userLiveData
    }

}