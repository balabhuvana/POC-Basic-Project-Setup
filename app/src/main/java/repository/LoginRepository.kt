package repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import model.LoginRequestModel
import model.LoginResponseModelRoot
import network.LoginApiWebService
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private var loginApiWebService: LoginApiWebService) {

    fun loginUserRepo(loginRequestModel: LoginRequestModel): MutableLiveData<LoginResponseModelRoot> {

        val myJsonData: String = Gson().toJson(loginRequestModel)
        Log.i("----> ", "Login Repo: $myJsonData")

        val loginResponseViewModel = MutableLiveData<LoginResponseModelRoot>()

        loginApiWebService.loginUser(loginRequestModel)
            .enqueue(object : Callback<LoginResponseModelRoot> {
                override fun onFailure(call: Call<LoginResponseModelRoot>, t: Throwable) {
                    Log.i("----> ", "onFailure")
                    getNetworkInfoDataSuccessResponse(false, call.request())
                    val loginResponseModelRoot: LoginResponseModelRoot? =
                        LoginResponseModelRoot()
                    loginResponseModelRoot?.success = false
                    loginResponseViewModel.postValue(loginResponseModelRoot)
                }

                override fun onResponse(
                    call: Call<LoginResponseModelRoot>,
                    response: Response<LoginResponseModelRoot>
                ) {
                    val loginResponseModelRoot: LoginResponseModelRoot? = response.body()
                    loginResponseViewModel.postValue(loginResponseModelRoot)
                    Log.i("----> ", "onResponse - ${loginResponseModelRoot?.success}")
                    val myJdsonData = Gson().toJson(response.body())
                    Log.i("----> ", "Json data: $myJdsonData")
                }
            })
        return loginResponseViewModel;
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