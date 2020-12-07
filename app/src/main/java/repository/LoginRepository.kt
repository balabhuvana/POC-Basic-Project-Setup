package repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import network.LoginApiWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(private var loginApiWebService: LoginApiWebService) {

    fun registerNewUser(loginOrRegistrationRequestModel: LoginOrRegistrationRequestModel): LiveData<LoginOrRegistrationResponseModel> {

        val modelForPostResponse = MutableLiveData<LoginOrRegistrationResponseModel>()
        loginApiWebService.registerUser(loginOrRegistrationRequestModel)
            .enqueue(object : Callback<LoginOrRegistrationResponseModel> {
                override fun onFailure(call: Call<LoginOrRegistrationResponseModel>, t: Throwable) {
                    Log.i("----> ", "onFailure")
                }

                override fun onResponse(
                    call: Call<LoginOrRegistrationResponseModel>,
                    response: Response<LoginOrRegistrationResponseModel>
                ) {

                    if (response.code() == 200) {
                        Log.i("---> ", " onResponse ")
                        modelForPostResponse.postValue(response.body())
                    } else if (response.code() == 400) {
                        val registrationResponseModel: LoginOrRegistrationResponseModel? =
                            response.body()
                        Log.i("---> ", " onResponse " + registrationResponseModel?.error)
                    }
                }
            })
        return modelForPostResponse
    }
}