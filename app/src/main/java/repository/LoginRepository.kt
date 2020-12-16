package repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import model.LoginRequestModel
import model.LoginResponseModelRoot
import network.LoginApiWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room.RegisterRequestRoomModel
import room.UserDao
import util.CommonUtils
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private var userDao: UserDao,
    private var loginApiWebService: LoginApiWebService
) {

    private var userLiveData: LiveData<RegisterRequestRoomModel>? = null

    fun loginUserRepo(loginRequestModel: LoginRequestModel): MutableLiveData<LoginResponseModelRoot> {

        val myJsonData: String = Gson().toJson(loginRequestModel)
        Log.i("----> ", "Login Repo: $myJsonData")

        val loginResponseViewModel = MutableLiveData<LoginResponseModelRoot>()

        loginApiWebService.loginUser(loginRequestModel)
            .enqueue(object : Callback<LoginResponseModelRoot> {
                override fun onFailure(call: Call<LoginResponseModelRoot>, t: Throwable) {
                    val loginResponseModelRoot: LoginResponseModelRoot? =
                        LoginResponseModelRoot()
                    loginResponseModelRoot?.success = false
                    loginResponseViewModel.postValue(loginResponseModelRoot)
                    CommonUtils.logNetworkInfo(loginRequestModel, "onFailure")
                }

                override fun onResponse(
                    call: Call<LoginResponseModelRoot>,
                    response: Response<LoginResponseModelRoot>
                ) {
                    val loginResponseModelRoot: LoginResponseModelRoot? = response.body()
                    loginResponseViewModel.postValue(loginResponseModelRoot)
                    CommonUtils.logNetworkInfo(loginResponseModelRoot!!, "onResponse")
                }
            })
        return loginResponseViewModel
    }

    fun selectUserRecordDatabase(userName: String, password: String): LiveData<RegisterRequestRoomModel> {
        userLiveData = userDao.getUserRecordMariaWithPassword(userName, password)
        return userLiveData as LiveData<RegisterRequestRoomModel>
    }
}