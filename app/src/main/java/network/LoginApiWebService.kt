package network

import model.LoginRequestModel
import model.LoginResponseModelRoot
import model.RegisterRequestModel
import model.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiWebService {

    @POST("Login")
    fun loginUser(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModelRoot>

    @POST("Register")
    fun registerUser(@Body registerRequestModel: RegisterRequestModel): Call<RegisterResponseModel>

}