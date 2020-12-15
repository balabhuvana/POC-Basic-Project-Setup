package repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.RegisterRequestModel
import model.RegisterResponseModel
import network.LoginApiWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import room.RegisterRequestRoomModel
import room.UserDao
import util.CommonUtils
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
    private var userDao: UserDao,
    private var loginApiWebService: LoginApiWebService
) {

    private var userLiveData: LiveData<RegisterRequestRoomModel>? = null

    fun insertUserRecord(registerRequestRoomModel: RegisterRequestRoomModel) {
        GlobalScope.launch {
            userDao.insertUserMariaData(registerRequestRoomModel)
        }
    }

    fun getUserRecord(registerRequestRoomModel: RegisterRequestRoomModel): LiveData<RegisterRequestRoomModel> {
        return selectUserRecord(registerRequestRoomModel.userName.toString())
    }

    private fun selectUserRecord(userName: String): LiveData<RegisterRequestRoomModel> {
        userLiveData = userDao.getUserRecordMaria(userName)
        return userLiveData as LiveData<RegisterRequestRoomModel>
    }

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
//                    CommonUtils.logNetworkInfo(registerResponseModel!!, "onResponse")
                }
            })

        return registerResponseModelLiveData

    }
}