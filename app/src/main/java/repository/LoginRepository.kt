package repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import model.LoginRequestModelMaria
import model.LoginResponseModelRootMaria
import network.LoginApiWebService
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private var loginApiWebService: LoginApiWebService) {

    fun loginPostUserMariaServer(loginRequestModelMaria: LoginRequestModelMaria): MutableLiveData<LoginResponseModelRootMaria> {

        val loginResponseViewModel = MutableLiveData<LoginResponseModelRootMaria>()

        loginApiWebService.loginUserMariaServer(loginRequestModelMaria)
            .enqueue(object : Callback<LoginResponseModelRootMaria> {
                override fun onFailure(call: Call<LoginResponseModelRootMaria>, t: Throwable) {
                    Log.i("----> ", "onFailure")
                    getNetworkInfoDataSuccessResponse(false, call.request())
                    val loginResponseModelRootMaria: LoginResponseModelRootMaria? =
                        LoginResponseModelRootMaria()
                    loginResponseModelRootMaria?.success = false
                    loginResponseViewModel.postValue(loginResponseModelRootMaria)
                }

                override fun onResponse(
                    call: Call<LoginResponseModelRootMaria>,
                    response: Response<LoginResponseModelRootMaria>
                ) {
                    Log.i("----> ", "onSuccess")
                    val loginResponseModelRootMaria: LoginResponseModelRootMaria? = response.body()
                    loginResponseViewModel.postValue(loginResponseModelRootMaria)
                }
            })
        return loginResponseViewModel;
    }

    fun getNetworkInfoDataSuccessResponse(
        isSuccess: Boolean,
        request: Request,
        response: Response<LoginResponseModelRootMaria>? = null
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