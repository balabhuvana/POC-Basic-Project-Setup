package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import repository.UserRepository
import room.User
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    application: Application,
    var userRepository: UserRepository
) : AndroidViewModel(application) {

    private var selectUserLiveData: LiveData<User>? = null

    fun insertUserRecord(user: User) {
        userRepository.insertUserRecord(user)
    }

    fun selectUserRecord(user: User) {
        selectUserLiveData = userRepository.selectUserRecord(user)
    }

    fun observeUserRecord(): LiveData<User>? {
        return selectUserLiveData
    }
}