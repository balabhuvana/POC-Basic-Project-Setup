package repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import model.LoginRequestModelMaria
import model.LoginResponseModelMaria
import network.LoginApiWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(private var loginApiWebService: LoginApiWebService) {

    fun loginUser(loginOrRegistrationRequestModel: LoginOrRegistrationRequestModel): LiveData<LoginOrRegistrationResponseModel> {
        val loginResponseViewModel = MutableLiveData<LoginOrRegistrationResponseModel>()

        loginApiWebService.loginUser(loginOrRegistrationRequestModel)
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
                        loginResponseViewModel.postValue(response.body())
                    } else if (response.code() == 400) {
                        val registrationResponseModel: LoginOrRegistrationResponseModel? =
                            response.body()
                        Log.i("---> ", " onResponse " + registrationResponseModel?.error)
                    }
                }
            })
        return loginResponseViewModel
    }

    fun registerNewUser(loginOrRegistrationRequestModel: LoginOrRegistrationRequestModel): LiveData<LoginOrRegistrationResponseModel> {

        val registrationResponseViewModel = MutableLiveData<LoginOrRegistrationResponseModel>()
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
                        registrationResponseViewModel.postValue(response.body())
                    } else if (response.code() == 400) {
                        val registrationResponseModel: LoginOrRegistrationResponseModel? =
                            response.body()
                        Log.i("---> ", " onResponse " + registrationResponseModel?.error)
                    }
                }
            })
        return registrationResponseViewModel
    }

    fun loginUserMariaServer(loginRequestModelMaria: LoginRequestModelMaria) {
        loginApiWebService.loginUserMariaServer(loginRequestModelMaria)
            .enqueue(object : Callback<LoginResponseModelMaria> {
                override fun onFailure(call: Call<LoginResponseModelMaria>, t: Throwable) {
                    Log.i("----> ", "onFailure")
                }

                override fun onResponse(
                    call: Call<LoginResponseModelMaria>,
                    response: Response<LoginResponseModelMaria>
                ) {
                    val loginResponseModelMaria: LoginResponseModelMaria? = response.body()
                    Log.i("----> ", "onResponse : $loginResponseModelMaria")
                }
            })
    }
}