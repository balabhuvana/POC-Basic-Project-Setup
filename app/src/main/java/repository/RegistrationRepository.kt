package repository

import androidx.lifecycle.MutableLiveData
import model.RegisterRequestModel
import model.RegisterResponseModel
import network.LoginApiWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.CommonUtils
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private var loginApiWebService: LoginApiWebService) {

    fun registerNewUserRepo(registerRequestModel: RegisterRequestModel): MutableLiveData<RegisterResponseModel> {

        val registerResponseModelLiveData = MutableLiveData<RegisterResponseModel>()

        loginApiWebService.registerUser(registerRequestModel)
            .enqueue(object : Callback<RegisterResponseModel> {
                override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                    val registerResponseModel = RegisterResponseModel()
                    registerResponseModel.success = false
                    registerResponseModelLiveData.postValue(registerResponseModel)
                    CommonUtils.logNetworkInfo(registerResponseModel, "onFailure")
                }

                override fun onResponse(
                    call: Call<RegisterResponseModel>,
                    response: Response<RegisterResponseModel>
                ) {
                    val registerResponseModel: RegisterResponseModel? = response.body()
                    registerResponseModelLiveData.postValue(registerResponseModel)
                    CommonUtils.logNetworkInfo(registerResponseModel!!, "onResponse")
                }
            })

        return registerResponseModelLiveData

    }
}