package network

import model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiWebService {

    @POST("/api/register")
    fun registerUser(@Body registrationRequestModel: LoginOrRegistrationRequestModel): Call<LoginOrRegistrationResponseModel>

    @POST("/api/login")
    fun loginUser(@Body loginRequestModel: LoginOrRegistrationRequestModel): Call<LoginOrRegistrationResponseModel>

    @POST("/CustomerLogin/Login")
    fun loginUserMariaServer(@Body loginRequestModelMaria: LoginRequestModelMaria): Call<LoginResponseModelRootMaria>

}