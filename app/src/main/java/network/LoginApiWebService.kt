package network

import model.LoginOrRegistrationRequestModel
import model.LoginOrRegistrationResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiWebService {

    @POST("/api/register")
    fun registerUser(@Body registrationRequestModel: LoginOrRegistrationRequestModel): Call<LoginOrRegistrationResponseModel>

    @POST("/api/login")
    fun loginUser(@Body loginRequestModel: LoginOrRegistrationRequestModel): Call<LoginOrRegistrationResponseModel>

}