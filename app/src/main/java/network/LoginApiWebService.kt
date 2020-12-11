package network

import model.LoginRequestModelMaria
import model.LoginResponseModelRootMaria
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiWebService {

    @POST("Login")
    fun loginUserMariaServer(@Body loginRequestModelMaria: LoginRequestModelMaria): Call<LoginResponseModelRootMaria>

}