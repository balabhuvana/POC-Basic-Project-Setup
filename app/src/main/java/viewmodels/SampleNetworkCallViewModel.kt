package viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import repository.UserRepository
import javax.inject.Inject

class SampleNetworkCallViewModel @Inject constructor(
    application: Application,
    private var userRepository: UserRepository
) : AndroidViewModel(application) {

    fun fetchUserFromNetwork() {
        userRepository.fetchUserListFromNetwork()
    }

}