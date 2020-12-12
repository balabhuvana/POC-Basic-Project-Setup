package repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import model.LoginResponseModelRoot
import model.RegisterRequestModel
import model.RegisterResponseModel
import network.LoginApiWebService
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private var loginApiWebService: LoginApiWebService) {

    fun registerNewUserRepo(registerRequestModel: RegisterRequestModel): MutableLiveData<RegisterResponseModel> {

        val registerResponseModelLiveData = MutableLiveData<RegisterResponseModel>()

        loginApiWebService.registerUser(registerRequestModel)
            .enqueue(object : Callback<RegisterResponseModel> {
                override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                    Log.i("----> ", "Reg: onFailure")
                    getNetworkInfoDataSuccessResponse(false, call.request())
                    val registerResponseModel = RegisterResponseModel()
                    registerResponseModel.success = false
                    registerResponseModelLiveData.postValue(registerResponseModel)
                }

                override fun onResponse(
                    call: Call<RegisterResponseModel>,
                    response: Response<RegisterResponseModel>
                ) {
                    val registerResponseModel: RegisterResponseModel? = response.body()
                    registerResponseModelLiveData.postValue(registerResponseModel)
                }
            })

        return registerResponseModelLiveData

    }

    fun getNetworkInfoDataSuccessResponse(
        isSuccess: Boolean,
        request: Request,
        response: Response<LoginResponseModelRoot>? = null
    ) {
        Log.i("----> ", "URL: " + request.url())
        Log.i("----> ", "Method: " + request.method())
        Log.i("----> ", "isHttps: " + request.isHttps)

        if (isSuccess) {
            val myJsonData = Gson().toJson(response?.body())

            Log.i("----> ", "Json data: $myJsonData")
            Log.i("----> ", "Response Code: " + response?.code())
            Log.i("----> ", "raw: " + response?.raw())
        }
    }
}